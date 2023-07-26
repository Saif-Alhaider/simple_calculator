package com.example.simplecalculator.ui.screen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(HomeUiState())
    val state = _state.asStateFlow()

    fun updateValue(action: String) {
        _state.update { it.copy(equation = it.equation + action) }
    }

    fun delete() {
        if (state.value.equation.isNotEmpty()) {
            _state.update {
                it.copy(equation = it.equation.substring(0, it.equation.length - 1))
            }
        }
    }

    fun clear(){
        if (state.value.equation.isNotEmpty()) {
            _state.update {
                it.copy(equation = "")
            }
        }
    }
//    fun performAction(action: HomeUiState.CalculatorOperation){
//        when(action){
//            is HomeUiState.CalculatorOperation.Addition -> {
//                _state.update {  }
//            }
//        }
//    }
}