package com.example.pokequiz.data

import com.example.pokequiz.data.network.PokemonApiService
import com.example.pokequiz.data.room.dao.PokemonDao
import com.example.pokequiz.motherobject.PokeQuizMotherObject.anyListPokemonEntity
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class PokemonRepositoryImplTest {

    @MockK
    private lateinit var pokemonApiService: PokemonApiService

    @MockK
    private lateinit var pokemonDao: PokemonDao

    private lateinit var pokemonRepositoryImpl: PokemonRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        pokemonRepositoryImpl = PokemonRepositoryImpl(
            pokemonApiService,
            pokemonDao
        )
    }

    @Test
    fun `when PokemonRepositoryImpl call savePokemonList successfully, it should call getPokemon from PokemonDao once`() =
        runBlocking {
            //Given
            val anyListPokemonEntity = anyListPokemonEntity
            coEvery { pokemonDao.getPokemon() } returns anyListPokemonEntity

            //When
            pokemonRepositoryImpl.savePokemonList()

            //Then
            coVerify(exactly = 1) { pokemonDao.getPokemon() }
        }

    @Test
    fun `when PokemonRepositoryImpl call getPokemonListFromRoom successfully, it should call getPokemon from PokemonDao once`() =
        runBlocking {
            //Given
            val anyListPokemonEntity = anyListPokemonEntity
            coEvery { pokemonDao.getPokemon() } returns anyListPokemonEntity

            //When
            pokemonRepositoryImpl.getPokemonListFromRoom()

            //Then
            coVerify(exactly = 1) { pokemonDao.getPokemon() }
        }
}