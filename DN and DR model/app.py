import numpy as np
import tensorflow as tf
from tensorflow.keras.models import load_model
import cv2
import pandas as pd
import xgboost as xgb
import json
import warnings
import gradio as gr
import firebase_admin
from firebase_admin import credentials, storage, firestore

warnings.filterwarnings('ignore')

# --- FIREBASE CONFIG ---
cred = credentials.Certificate("firebase_service_account.json")
firebase_admin.initialize_app(cred, {
    "storageBucket": "<your-project-id>.appspot.com"
})
db = firestore.client()
bucket = storage.bucket()

# --- LOAD MODELS ---
dr_model = load_model("my_working_model.keras")
dn_model = xgb.XGBClassifier()
dn_model.load_model("dn_prediction_model.json")
print("models loaded successfully")

# --- HARDCODED FEATURE MEDIANS ---
feature_medians = {
    'bp': 80.0, 'sg': 1.020, 'al': 0.0, 'su': 0.0, 'rbc': 'normal',
    'bu': 15.0, 'sc': 1.0, 'sod': 140.0, 'pot': 4.0, 'hemo': 15.0,
    'wbcc': 7500.0, 'rbcc': 5.0, 'htn': 'no'
}

class_names = ['No DR', 'Mild', 'Moderate', 'Severe', 'Proliferative']
dn_class_names = ['Not DN', 'DN']

# --- HELPERS ---
def preprocess_image_bytes(image_bytes, img_size=380):
    np_arr = np.frombuffer(image_bytes, np.uint8)
    img_bgr = cv2.imdecode(np_arr, cv2.IMREAD_COLOR)
    img_rgb = cv2.cvtColor(img_bgr, cv2.COLOR_BGR2RGB)
    img_resized = cv2.resize(img_rgb, (img_size, img_size))
    img_tensor = tf.convert_to_tensor(img_resized, dtype=tf.float32)
    return tf.keras.applications.efficientnet.preprocess_input(img_tensor)

def predict_dn_risk(patient_data, medians):
    df = pd.DataFrame([patient_data])

    numerical_cols = ['bp', 'bu', 'sc', 'sod', 'pot', 'hemo', 'wbcc', 'rbcc']
    categorical_cols = ['sg', 'al', 'su', 'rbc', 'htn']

    for col in numerical_cols:
        df[col] = pd.to_numeric(df.get(col), errors="coerce").fillna(medians.get(col, 0))

    for col in categorical_cols:
        if col in df.columns:
            df[col] = df[col].astype(str).str.strip().str.lower().replace({
                "0.0": "0", "1.0": "1", "2.0": "2"
            })
        else:
            df[col] = medians.get(col, "")

    df = pd.get_dummies(df, columns=[col for col in categorical_cols if col in df.columns])
    training_columns = dn_model.get_booster().feature_names
    final_input = pd.DataFrame([np.zeros(len(training_columns))], columns=training_columns)

    for col in df.columns:
        if col in final_input.columns:
            final_input[col] = df[col].values

    prediction = dn_model.predict(final_input)[0]
    probability = dn_model.predict_proba(final_input)[0]
    return prediction, probability

def get_dr_risk_value(pred_class, conf):
    return (pred_class / 4.0) * conf

def calculate_overall_risk(dr_class, dr_conf, dn_prob, weight_dr=0.6, weight_dn=0.4):
    dr_risk = get_dr_risk_value(dr_class, dr_conf)
    dn_risk = float(dn_prob)
    return (dr_risk * weight_dr + dn_risk * weight_dn) * 100

# --- MAIN PIPELINE ---
def analyze(patient_id: str, image_path: str):
    # Get fundus image from Firebase Storage
    blob = bucket.blob(image_path)
    image_bytes = blob.download_as_bytes()
    processed_image = preprocess_image_bytes(image_bytes)
    input_tensor = np.expand_dims(processed_image, axis=0)

    preds = dr_model.predict(input_tensor)
    dr_class = int(np.argmax(preds, axis=1)[0])
    dr_conf = float(preds[0][dr_class])
    dr_label = f"{class_names[dr_class]} ({dr_conf:.2f})"

    # Get patient data from Firestore
    doc = db.collection("patients").document(patient_id).get()
    if not doc.exists:
        return {"error": "Patient not found in Firebase"}
    patient_data = doc.to_dict()

    dn_pred, dn_prob = predict_dn_risk(patient_data, feature_medians)
    dn_label = f"{dn_class_names[dn_pred]} ({dn_prob[1]:.2f})"

    overall = calculate_overall_risk(dr_class, dr_conf, dn_prob[1])

    return {
        "DR Result": dr_label,
        "DN Result": dn_label,
        "Overall Microvascular Risk": f"{overall:.2f}%"
    }

# --- GRADIO UI ---
demo = gr.Interface(
    fn=analyze,
    inputs=[
        gr.Textbox(label="Patient ID"),
        gr.Textbox(label="Fundus Image Path in Firebase Storage")
    ],
    outputs="json",
    title="Microvascular Risk Analyzer (DR + DN)"
)

if __name__ == "__main__":
    demo.launch()
