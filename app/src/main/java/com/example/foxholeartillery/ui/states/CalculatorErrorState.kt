package com.example.foxholeartillery.ui.states

data class CalculatorErrorState(
    val rangeError: Boolean = false,
    val calculationError: Boolean = false
)