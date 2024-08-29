package com.example.pokequiz.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokequiz.core.Constants.MAX_POKEMON_GAME
import com.example.pokequiz.core.Constants.MAX_WAITING_TIME
import com.example.pokequiz.core.DispatcherProvider
import com.example.pokequiz.core.Generations
import com.example.pokequiz.core.extensions.formatNamePokemon
import com.example.pokequiz.domain.model.PokemonModel
import com.example.pokequiz.domain.state.PokemonState
import com.example.pokequiz.domain.usecase.GetPokemonListUseCase
import com.example.pokequiz.domain.usecase.SavePokemonListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val getPokemonListUseCase: GetPokemonListUseCase,
    private val savePokemonListUseCase: SavePokemonListUseCase,
) : ViewModel() {

    private val _pokemonListState = MutableStateFlow<PokemonState>(PokemonState.Loading)
    val pokemonListState: StateFlow<PokemonState> = _pokemonListState

    private val _isRunningGame = MutableStateFlow(true)
    val isRunningGame: StateFlow<Boolean> = _isRunningGame

    private val _selectedAnswer = MutableStateFlow(0)
    val selectedAnswer: StateFlow<Int> = _selectedAnswer

    private val _progress = MutableStateFlow(0f)
    val progress: StateFlow<Float> = _progress

    private val _waitingTime = MutableStateFlow(0f)
    val waitingTime: StateFlow<Float> = _waitingTime

    private val _positionGame = MutableStateFlow(0)
    val positionGame: StateFlow<Int> = _positionGame

    private val _pointsStep = MutableStateFlow(0f)

    private val _totalPoints = MutableStateFlow(0f)
    val totalPoints: StateFlow<Float> = _totalPoints

    private val _showTotalPoints = MutableStateFlow(false)
    val showTotalPoints: StateFlow<Boolean> = _showTotalPoints

    fun savePokemonList() {
        viewModelScope.launch {
            _pokemonListState.value = withContext(dispatcherProvider.io) {
                savePokemonListUseCase.invoke()
            }
        }
    }

    fun getPokemonList() {
        viewModelScope.launch {
            _pokemonListState.value = withContext(dispatcherProvider.io) {
                getPokemonListUseCase.invoke()
            }
        }
    }

    fun getRandomPokemonForGame(
        listPokemon: List<PokemonModel>,
        idGeneration: Int
    ): List<PokemonModel> {
        val numberMinPokemon =
            Generations.entries.find { it.id == idGeneration }?.numberMinPokemon!!
        val numberMaxPokemon =
            Generations.entries.find { it.id == idGeneration }?.numberMaxPokemon!!
        val filteredGenerationsListPokemon =
            listPokemon.filter { it.id in numberMinPokemon..numberMaxPokemon }
        val idPokemonSelected =
            filteredGenerationsListPokemon.shuffled().take(MAX_POKEMON_GAME).map { it.id }
        val pokemonSelected = listPokemon.filter { it.id in idPokemonSelected }
        pokemonSelected.forEach { pokemon ->
            val listPokemonWithoutCurrent = listPokemon.filter { it.id != pokemon.id }
            val nameOtherPokemon =
                listPokemonWithoutCurrent.shuffled().take(3).map { it.name.formatNamePokemon() }
                    .toMutableList()
            nameOtherPokemon.add(pokemon.name.formatNamePokemon())
            nameOtherPokemon.shuffle()
            pokemon.optionsPokemon = nameOtherPokemon
        }
        val shuffledPokemonList = pokemonSelected.toMutableList()
        shuffledPokemonList.shuffle()
        return shuffledPokemonList
    }

    fun stopGameTimer(running: Boolean, selectedAnswer: Int, correctAnswer: Boolean) {
        _isRunningGame.value = running
        _selectedAnswer.value = selectedAnswer
        if (correctAnswer) {
            saveTotalPoints(
                String.format("%.2f", _pointsStep.value).toFloat()
            )
        }
        nextStepPokemon()
    }

    fun saveProgressStep(progress: Float) {
        _progress.value = progress
    }

    fun saveWaitingTime(waitingTime: Float) {
        _waitingTime.value = waitingTime
    }

    fun savePointsStep(points: Float) {
        _pointsStep.value = points
    }

    private fun saveTotalPoints(points: Float) {
        _totalPoints.value += points
    }

    private fun nextStepPokemon() {
        viewModelScope.launch {
            delay(MAX_WAITING_TIME)
            if (_positionGame.value < MAX_POKEMON_GAME - 1) {
                _showTotalPoints.value = false
                //Reset values
                _isRunningGame.value = true
                _pointsStep.value = 0f
                _progress.value = 0f
                _waitingTime.value = 0f

                //Next step
                _positionGame.value += 1
            } else {
                _showTotalPoints.value = true
            }
        }
    }
}
