package com.hsilva.myshoppinglist

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hsilva.myshoppinglist.ui.home.HomeScreen

sealed class NavRoute(val route: String) {
    object Home : NavRoute("home_route")
}

@Composable
fun ShoppingNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoute.Home.route,
    ) {
        composable(NavRoute.Home.route) {
            HomeScreen()
        }
    }
}