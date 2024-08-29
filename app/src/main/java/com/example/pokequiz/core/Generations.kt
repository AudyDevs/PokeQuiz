package com.example.pokequiz.core

import com.example.pokequiz.R

enum class Generations(
    val id: Int,
    val nameRegion: String,
    val image: Int,
    val numberMinPokemon: Int,
    val numberMaxPokemon: Int
) {
    Generation0(0, "Todas las regiones", R.drawable.ic_all_generations, 1, 1025),
    Generation1(1, "Región de Kanto", R.drawable.ic_generation_1, 1, 151),
    Generation2(2, "Región de Johto", R.drawable.ic_generation_2, 152, 251),
    Generation3(3, "Región de Hoenn", R.drawable.ic_generation_3, 252, 386),
    Generation4(4, "Región de Sinnoh", R.drawable.ic_generation_4, 387, 493),
    Generation5(5, "Región de Teselia", R.drawable.ic_generation_5, 494, 649),
    Generation6(6, "Región de Kalos", R.drawable.ic_generation_6, 650, 721),
    Generation7(7, "Región de Alola", R.drawable.ic_generation_7, 722, 809),
    Generation8(8, "Región de Galar", R.drawable.ic_generation_8, 810, 905),
    Generation9(9, "Región de Paldea", R.drawable.ic_generation_9, 906, 1025)
}