package com.example.pokequiz.core.type

sealed class TypeGame(val typeGame: String) {
    data object League : TypeGame("League")
    data object Training : TypeGame("Training")
}