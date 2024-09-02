package com.example.pokequiz.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokequiz.core.DispatcherProvider
import com.example.pokequiz.domain.model.ScoreModel
import com.example.pokequiz.domain.state.ScoreState
import com.example.pokequiz.domain.usecase.GetScorePointsUseCase
import com.example.pokequiz.domain.usecase.SaveScorePointsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ScoreViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val getScorePointsUseCase: GetScorePointsUseCase
) : ViewModel() {

    private val _pointsState = MutableStateFlow<ScoreState>(ScoreState.Loading)
    val pointsState: StateFlow<ScoreState> = _pointsState

    init {
        getScorePoints()
    }

    private fun getScorePoints() {
        viewModelScope.launch {
            withContext(dispatcherProvider.io) {
                _pointsState.value = ScoreState.Loading
                getScorePointsUseCase.invoke().addOnSuccessListener { pointsState ->
                    _pointsState.value = pointsState
                }.addOnFailureListener { exception ->
                    _pointsState.value = ScoreState.Error(exception.message ?: "")
                }
            }
        }
    }
}
