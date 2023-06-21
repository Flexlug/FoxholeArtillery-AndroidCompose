package com.example.foxholeartillery

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.foxholeartillery.ui.ArtilleryRange
import com.example.foxholeartillery.ui.states.ArtillerySelectorState
import com.example.foxholeartillery.ui.states.CalculatorErrorState
import com.example.foxholeartillery.ui.states.CalculatorInputState
import com.example.foxholeartillery.ui.states.CalculatorOutputState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sqrt

class CalculatorViewModel : ViewModel() {
    var selectorState = MutableStateFlow(ArtillerySelectorState())
        private set

    var errorState = MutableStateFlow(CalculatorErrorState())
        private set

    var inputState = MutableStateFlow(CalculatorInputState())
        private set

    var outputState = MutableStateFlow(CalculatorOutputState())
        private set

    fun onAction(calculatorAction: CalculatorAction) {
        when (calculatorAction) {
            is CalculatorAction.ChangeArtilleryType -> selectorState.update { selectorState.value.copy(selectedArtilleryType = calculatorAction.type) }
            is CalculatorAction.EnemyAzimuthInput -> inputState.update { inputState.value.copy(enemyAzimuthInput = calculatorAction.input) }
            is CalculatorAction.EnemyDistanceInput -> inputState.update { inputState.value.copy(enemyDistanceInput = calculatorAction.input) }
            is CalculatorAction.FriendlyAzimuthInput -> inputState.update { inputState.value.copy(friendlyAzimuthInput = calculatorAction.input) }
            is CalculatorAction.FriendlyDistanceInput -> inputState.update { inputState.value.copy(friendlyDistanceInput = calculatorAction.input) }
        }

        if (
            inputState.value.friendlyDistanceInput != 0.0 &&
            !inputState.value.friendlyAzimuthInput.isNaN() &&
            inputState.value.enemyDistanceInput != 0.0 &&
            !inputState.value.enemyDistanceInput.isNaN()) {
            try {
                val result = calculate(inputState.value)
                val resultInRange = checkRange(result.first, selectorState.value.selectedArtilleryType)

                outputState.update {
                    outputState.value.copy(
                        calculatedEnemyDistance = result.first,
                        calculatedEnemyAzimuth = result.second,
                    )
                }

                errorState.update {
                    errorState.value.copy(
                        calculationError = false,
                        rangeError = !resultInRange
                    )
                }
            } catch (ex: Exception) {
                Log.e("ViewModel", "Calculation error", ex)
                outputState.update {
                    outputState.value.copy(
                        calculatedEnemyDistance = 0.0,
                        calculatedEnemyAzimuth = 0.0,
                    )
                }

                errorState.update {
                    errorState.value.copy(
                        calculationError = true,
                        rangeError = false
                    )
                }
            }
        }
    }

    private fun convertAngle(angle: Double): Double {
        return if (angle > 360) angle - 360 else angle
    }

    private fun checkRange(distance: Double, artilleryType: ArtilleryType): Boolean {
        return when (artilleryType) {
            ArtilleryType.FieldArtillery -> distance <= ArtilleryRange.FieldArtillery.max && distance >= ArtilleryRange.FieldArtillery.min
            ArtilleryType.Gunboat -> distance <= ArtilleryRange.Gunboat.max && distance >= ArtilleryRange.Gunboat.min
            ArtilleryType.Howitzer -> distance <= ArtilleryRange.Howitzer.max && distance >= ArtilleryRange.Howitzer.min
            ArtilleryType.Mortar -> distance <= ArtilleryRange.Mortar.max && distance >= ArtilleryRange.Mortar.min
        }
    }

    private fun calculate(state: CalculatorInputState): Pair<Double, Double> {
        val eDist = state.enemyDistanceInput
        val eAzi = state.enemyAzimuthInput
        val fDist = state.friendlyDistanceInput
        val fAzi = state.friendlyAzimuthInput

        val aDelt = if (eAzi > fAzi) Math.toRadians(eAzi - fAzi) else Math.toRadians(fAzi - eAzi);

        val rDist = sqrt(eDist * eDist + fDist * fDist - 2 * eDist * fDist * cos(aDelt));

        val aStep =
            Math.toDegrees(acos((-(eDist * eDist) + fDist * fDist + rDist * rDist) / (2 * fDist * rDist)))

        var rAzi = 0.0
        if (convertAngle(Math.toDegrees(aDelt)) > 180) {
            rAzi = if (eAzi > fAzi) (fAzi + 180 + aStep) else (fAzi + 180 - aStep)
        } else {
            if (eAzi > fAzi) {
                rAzi = fAzi + 180.0 - aStep
            } else {
                rAzi = fAzi + 180.0 + aStep
            }
        }
        rAzi = convertAngle(rAzi)

        Log.d("ViewModel", "Calculation result: $aDelt, $rDist, $aStep, $rAzi")
        return Pair(rDist, rAzi)
    }
}