package com.example.otwsuitmedia.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.otwsuitmedia.screens.FirstScreen
import com.example.otwsuitmedia.screens.SecondScreen
import com.example.otwsuitmedia.screens.ThirdScreen

@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()
    NavHost(navController = navController, startDestination = "firstScreen") {
        // Rute ke layar pertama
        composable("firstScreen") {
            FirstScreen(navController)
        }
        // Rute ke layar kedua
        composable("secondScreen/{name}") { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            SecondScreen(navController, name)
        }
        // Rute ke layar ketiga
        composable("thirdScreen") {
            ThirdScreen(navController)
        }
    }
}
