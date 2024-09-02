package com.example.pokequiz.core.type

import com.example.pokequiz.R

enum class Generations(
    val id: Int,
    val nameRegion: Int,
    val image: Int,
    val numberMinPokemon: Int,
    val numberMaxPokemon: Int
) {
    Generation0(0, 0, R.drawable.ic_all_generations, 1, 1025),
    Generation1(1, R.string.region1, R.drawable.ic_generation_1, 1, 151),
    Generation2(2, R.string.generation2, R.drawable.ic_generation_2, 152, 251),
    Generation3(3, R.string.generation3, R.drawable.ic_generation_3, 252, 386),
    Generation4(4, R.string.generation4, R.drawable.ic_generation_4, 387, 493),
    Generation5(5, R.string.generation5, R.drawable.ic_generation_5, 494, 649),
    Generation6(6, R.string.generation6, R.drawable.ic_generation_6, 650, 721),
    Generation7(7, R.string.generation7, R.drawable.ic_generation_7, 722, 809),
    Generation8(8, R.string.generation8, R.drawable.ic_generation_8, 810, 905),
    Generation9(9, R.string.generation9, R.drawable.ic_generation_9, 906, 1025)
}