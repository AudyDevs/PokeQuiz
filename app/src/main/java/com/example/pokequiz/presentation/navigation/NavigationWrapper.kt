package com.example.pokequiz.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.pokequiz.presentation.screen.GameScreen
import com.example.pokequiz.presentation.screen.HomeScreen
import com.example.pokequiz.presentation.screen.ScoreScreen

@Composable
fun NavigationWrapper(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = NavScreen.Home.route) {
        composable(NavScreen.Home.route) {
            HomeScreen(
                navigateToGame = { typeGame, idGeneration ->
                    navHostController.navigate(NavScreen.Game.route + "/${typeGame}/${idGeneration}")
                },
                navigateToScore = { navHostController.navigate(NavScreen.Score.route) }
            )
        }
        composable(
            route = NavScreen.Game.route + "/{typeGame}/{idGeneration}",
            arguments = listOf(
                navArgument("typeGame") {
                    type = NavType.StringType
                },
                navArgument("idGeneration") {
                    type = NavType.IntType
                }
            )
        ) { navBackStackEntry ->
            val typeGame = navBackStackEntry.arguments?.getString("typeGame") ?: ""
            val idGeneration = navBackStackEntry.arguments?.getInt("idGeneration") ?: 0
            GameScreen(
                typeGame = typeGame,
                idGeneration = idGeneration,
                navigateToScore = {
                    navHostController.navigate(NavScreen.Score.route) {
                        popUpTo(navHostController.graph.startDestinationId) {
                            inclusive = false
                        }
                    }
                }
            )
        }
        composable(NavScreen.Score.route) {
            ScoreScreen(
                navigateToHome = {
                    navHostController.navigate(NavScreen.Home.route)
                }
            )
        }
    }
}