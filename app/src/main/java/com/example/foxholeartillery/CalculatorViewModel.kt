package com.example.foxholeartillery

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sqrt

class CalculatorViewModel: ViewModel() {
    var state by mutableStateOf(CalculatorState())
        private set

    fun onAction(calculatorAction: CalculatorAction) {
        state = when (calculatorAction) {
            is CalculatorAction.ChangeArtilleryType -> state.copy(selectedArtilleryType = calculatorAction.type)
            is CalculatorAction.EnemyAzimuthInput -> state.copy(enemyAzimuthInput = calculatorAction.input)
            is CalculatorAction.EnemyDistanceInput -> state.copy(enemyDistanceInput = calculatorAction.input)
            is CalculatorAction.FriendlyAzimuthInput -> state.copy(friendlyAzimuthInput = calculatorAction.input)
            is CalculatorAction.FriendlyDistanceInput -> state.copy(friendlyDistanceInput = calculatorAction.input)
        }

        if (
            state.friendlyDistanceInput != 0.0 &&
            !state.friendlyAzimuthInput.isNaN() &&
            state.enemyDistanceInput != 0.0 &&
            !state.enemyDistanceInput.isNaN()
        ) {
            try {
                val result = calculate(state)
                val resultInRange = checkRange(result.first, state.selectedArtilleryType)

                state = state.copy(
                    calculatedEnemyDistance = result.first,
                    calculatedEnemyAzimuth = result.second,
                    calculationError = false,
                    rangeError = !resultInRange
                )
            } catch (ex: Exception) {
                Log.e("ViewModel", "Calculation error", ex)

                state = state.copy(
                    calculatedEnemyDistance = 0.0,
                    calculatedEnemyAzimuth = 0.0,
                    calculationError = true,
                    rangeError = false
                )
            }
        }
    }

    fun convertAngle(angle: Double): Double {
        return if (angle > 360) angle - 360 else angle
    }

    fun checkRange(distance: Double, artilleryType: ArtilleryType): Boolean {
        return when (artilleryType) {
            ArtilleryType.FieldArtillery ->
                distance <= ArtilleryRange.FieldArtillery.max && distance >= ArtilleryRange.FieldArtillery.min

            ArtilleryType.Gunboat ->
                distance <= ArtilleryRange.Gunboat.max && distance >= ArtilleryRange.Gunboat.min

            ArtilleryType.Howitzer ->
                distance <= ArtilleryRange.Howitzer.max && distance >= ArtilleryRange.Howitzer.min

            ArtilleryType.Mortar ->
                distance <= ArtilleryRange.Mortar.max && distance >= ArtilleryRange.Mortar.min

            else -> true
        }
    }

    fun calculate(state: CalculatorState): Pair<Double, Double> {
        val eDist = state.enemyDistanceInput
        val eAzi = state.enemyAzimuthInput
        val fDist = state.friendlyDistanceInput
        val fAzi = state.friendlyAzimuthInput

        val aDelt =
            if (eAzi > fAzi) Math.toRadians(eAzi - fAzi) else Math.toRadians(fAzi - eAzi);

        val rDist =
            sqrt(eDist * eDist + fDist * fDist - 2 * eDist * fDist * cos(aDelt));

        val aStep = Math.toDegrees(acos((-(eDist * eDist) + fDist * fDist + rDist * rDist) / (2 * fDist * rDist)))

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