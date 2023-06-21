package com.example.foxholeartillery.ui

import android.telephony.TelephonyCallback.CallDisconnectCauseListener
import com.example.foxholeartillery.CalculatorAction
import com.example.foxholeartillery.CalculatorViewModel
import com.example.foxholeartillery.ui.states.ArtillerySelectorState
import com.example.foxholeartillery.ui.states.CalculatorErrorState
import com.example.foxholeartillery.ui.states.CalculatorInputState
import com.example.foxholeartillery.ui.states.CalculatorOutputState
import kotlinx.coroutines.flow.StateFlow

class PresenterInput(
    private val viewModel: CalculatorViewModel
) {
    val state: StateFlow<CalculatorInputState> = viewModel.inputState
    fun onInput(calculatorAction: CalculatorAction) {
        viewModel.onAction(calculatorAction)
    }
}

class PresenterOutput(
    viewModel: CalculatorViewModel
) {
    val state: StateFlow<CalculatorOutputState> = viewModel.outputState
}

class PresenterTypeSelector(
    private val viewModel: CalculatorViewModel
) {
    val state: StateFlow<ArtillerySelectorState> = viewModel.selectorState
    fun onSelect(calculatorAction: CalculatorAction) {
        viewModel.onAction(calculatorAction)
    }
}

class PresenterError(
    viewModel: CalculatorViewModel
) {
    val state: StateFlow<CalculatorErrorState> = viewModel.errorState
}