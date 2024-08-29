package com.example.pokequiz.core

sealed class TypeGame(val typeGame: String) {
    data object League : TypeGame("League")
    data object Training : TypeGame("Training")
}