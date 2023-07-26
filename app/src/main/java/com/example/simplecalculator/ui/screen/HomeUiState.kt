package com.example.simplecalculator.ui.screen

data class HomeUiState(
    val equation:String = ""
){
    sealed class CalculatorOperation(value:String){
        object Addition:CalculatorOperation(value = "+")
        object Subtraction:CalculatorOperation(value = "-")
    }
}
