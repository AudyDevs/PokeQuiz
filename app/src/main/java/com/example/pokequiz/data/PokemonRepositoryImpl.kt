package com.example.pokequiz.data

import com.example.pokequiz.core.Constants.BIG_IMAGE
import com.example.pokequiz.core.Constants.LIMIT_POKEMON_LIST
import com.example.pokequiz.core.Constants.POKEMON_LIMIT
import com.example.pokequiz.core.Constants.POKEMON_OFFSET
import com.example.pokequiz.core.mappers.toDomain
import com.example.pokequiz.core.mappers.toRoom
import com.example.pokequiz.data.network.PokemonApiService
import com.example.pokequiz.data.room.dao.PokemonDao
import com.example.pokequiz.domain.PokemonRepository
import com.example.pokequiz.domain.model.PokemonModel
import com.example.pokequiz.domain.state.PokemonState
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonApiService: PokemonApiService, private val pokemonDao: PokemonDao
) : PokemonRepository {

    override suspend fun savePokemonList(): PokemonState {
        val roomResponse = pokemonDao.getPokemon()
        if (roomResponse.isNotEmpty()) {
            return PokemonState.Success(emptyList())
        }
        val apiPokemonResponse = getAPIPokemonList()
        if (apiPokemonResponse is PokemonState.Success) {
            apiPokemonResponse.pokemon =
                apiPokemonResponse.pokemon.filter { it.id <= LIMIT_POKEMON_LIST }
            //Clear and save again the values.
            pokemonDao.clearPokemon()
            pokemonDao.insertPokemon(apiPokemonResponse.pokemon.map { it.toRoom() })
        }
        return apiPokemonResponse
    }

    override suspend fun getPokemonListFromRoom(): PokemonState {
        val roomResponse = pokemonDao.getPokemon()
        if (roomResponse.isNotEmpty()) {
            return PokemonState.Success(roomResponse.map { it.toDomain() })
        }
        return PokemonState.Error
    }

    private suspend fun getAPIPokemonList(): PokemonState {
        runCatching {
            pokemonApiService.getPokemonList(POKEMON_LIMIT, POKEMON_OFFSET)
        }.onSuccess { pokemonListResponse ->
            val apiPokemonListResponse = pokemonListResponse.results.map { pokemon ->
                val id = extractIdUrl(pokemon.url)
                PokemonModel(
                    id.toInt(),
                    pokemon.name,
                    BIG_IMAGE.replace("[id]", id)
                )
            }
            return PokemonState.Success(apiPokemonListResponse)

        }.onFailure {
            return PokemonState.Error
        }
        return PokemonState.Success(emptyList())
    }

    private fun extractIdUrl(url: String): String {
        return if (url.endsWith("/")) {
            url.dropLast(1).takeLastWhile { it.isDigit() }
        } else {
            url.takeLastWhile { it.isDigit() }
        }
    }
}