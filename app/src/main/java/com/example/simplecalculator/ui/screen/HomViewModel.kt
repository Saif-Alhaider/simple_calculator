package com.example.simplecalculator.ui.screen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import kotlin.math.abs

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
        val pattern = Regex("""\d+(\.\d+)?|[-+*÷]|π""")
        val equationList = pattern.findAll(state.value.equation).map { it.value }.toList()
        try {
            var result = if (equationList[0] == "π") 3.14 else equationList[0].toDouble()
            for (i in 1 until equationList.size step 2) {
                val operator = equationList[i]
                val operand =
                    if (equationList[i + 1] == "π") 3.14 else equationList[i + 1].toDouble()
                when (operator) {
                    "+" -> result += operand
                    "-" -> result -= operand
                    "X" -> result *= operand
                    "÷" -> result /= operand
                    "%" -> result %= operand
                    else -> throw IllegalArgumentException("Unsupported operator: $operator")
                }
            }
            _state.update {
                it.copy(
                    equation = roundResult(result)

                )
            }
        } catch (e: Exception) {
            _state.update { it.copy(equation = "NaN") }
        }
    }

    private fun roundResult(result: Double): String {
        return if (result.isDecimal()) {
            result.toInt().toString()
        } else {
            String.format("%.2f", result)
        }
    }

    private fun Double.isDecimal() = abs(this) % 1.0 < 1e-10
}