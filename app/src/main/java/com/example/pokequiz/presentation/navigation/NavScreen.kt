package com.example.pokequiz.presentation.navigation

sealed class NavScreen(val route: String) {
    data object Home: NavScreen("home")
    data object Game: NavScreen("game")
    data object Score: NavScreen("score")
}