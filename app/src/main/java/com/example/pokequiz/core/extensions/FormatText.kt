package com.example.pokequiz.core.extensions

import java.util.Locale

fun String.formatNamePokemon(): String {
    return this.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(
            Locale.ROOT
        ) else it.toString()
    }
}