package com.example.pokequiz.core.type

import com.example.pokequiz.R

sealed class ErrorType(val errorMessage: Int) {
    data object GetPoints : ErrorType(R.string.errorGetPoints)
    data object SavePoints : ErrorType(R.string.errorSavePoints)
}