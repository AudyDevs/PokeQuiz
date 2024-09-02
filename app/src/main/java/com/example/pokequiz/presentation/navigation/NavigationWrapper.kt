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
                navigateToGame = { typeGame, idGeneration, nameTrainer ->
                    navHostController.navigate(NavScreen.Game.route + "/${typeGame}/${idGeneration}/${nameTrainer}")
                },
                navigateToScore = { navHostController.navigate(NavScreen.Score.route) }
            )
        }
        composable(
            route = NavScreen.Game.route + "/{typeGame}/{idGeneration}/{nameTrainer}",
            arguments = listOf(
                navArgument("typeGame") {
                    type = NavType.StringType
                },
                navArgument("idGeneration") {
                    type = NavType.IntType
                },
                navArgument("nameTrainer") {
                    type = NavType.StringType
                }
            )
        ) { navBackStackEntry ->
            val typeGame = navBackStackEntry.arguments?.getString("typeGame") ?: ""
            val idGeneration = navBackStackEntry.arguments?.getInt("idGeneration") ?: 0
            val nameTrainer = navBackStackEntry.arguments?.getString("nameTrainer") ?: ""
            GameScreen(
                typeGame = typeGame,
                idGeneration = idGeneration,
                nameTrainer = nameTrainer,
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