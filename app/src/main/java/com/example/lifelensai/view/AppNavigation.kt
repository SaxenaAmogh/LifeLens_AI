package com.example.lifelensai.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {

        //start pages
        composable("splash") {
//            SplashScreen(navController)
        }
        composable("home") {
            HomePage(navController)
        }
        composable("select") {
            SelectReportPage(navController)
        }
    }
}