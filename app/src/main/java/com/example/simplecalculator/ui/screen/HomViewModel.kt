package com.example.simplecalculator.ui.screen

import android.util.Log
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

    fun clear() {
        if (state.value.equation.isNotEmpty()) {
            _state.update {
                it.copy(equation = "")
            }
        }
    }

    fun performAction() {
        val pattern = Regex("""\d+(\.\d+)?|[-+*/]""")
        val equationList = pattern.findAll(state.value.equation).map { it.value }.toList()
        try {
            var result = equationList[0].toDouble()
            for (i in 1 until equationList.size step 2) {
                val operator = equationList[i]
                val operand = equationList[i + 1].toDouble()

                when (operator) {
                    "+" -> result += operand
                    "-" -> result -= operand
                    "X" -> result *= operand
                    "÷" -> result /= operand
                    else -> throw IllegalArgumentException("Unsupported operator: $operator")
                }
            }
            _state.update { it.copy(equation = result.toString()) }
        } catch (e: Exception) {
            _state.update { it.copy(equation = "NaN") }
            Log.e("gg", "performAction: NAN\n$e")
        }
    }
}