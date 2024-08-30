package com.example.pokequiz.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokequiz.core.DispatcherProvider
import com.example.pokequiz.domain.state.PokemonState
import com.example.pokequiz.domain.usecase.ReadNameTrainerUseCase
import com.example.pokequiz.domain.usecase.SaveNameTrainerUseCase
import com.example.pokequiz.domain.usecase.SavePokemonListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val savePokemonListUseCase: SavePokemonListUseCase,
    private val readNameTrainerUseCase: ReadNameTrainerUseCase,
    private val saveNameTrainerUseCase: SaveNameTrainerUseCase
) : ViewModel() {

    private val _pokemonListState = MutableStateFlow<PokemonState>(PokemonState.Loading)
    val pokemonListState: StateFlow<PokemonState> = _pokemonListState

    private val _nameTrainer = MutableStateFlow("default")
    val nameTrainer: StateFlow<String> = _nameTrainer

    fun savePokemonList() {
        viewModelScope.launch {
            _pokemonListState.value = withContext(dispatcherProvider.io) {
                savePokemonListUseCase.invoke()
            }
        }
    }

    fun readNameTrainer() {
        viewModelScope.launch {
            _nameTrainer.value = withContext(dispatcherProvider.io) {
                readNameTrainerUseCase.invoke()
            }
        }
    }

    fun saveNameTrainer(nameTrainer: String) {
        viewModelScope.launch {
            withContext(dispatcherProvider.io) {
                saveNameTrainerUseCase.invoke(nameTrainer)
                _nameTrainer.value = nameTrainer
            }
        }
    }
}
