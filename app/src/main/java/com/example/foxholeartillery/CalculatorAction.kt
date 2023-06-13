package com.example.foxholeartillery

sealed class CalculatorAction {
    data class ChangeArtilleryType(val type: ArtilleryType): CalculatorAction()
    data class FriendlyDistanceInput(val input: Double): CalculatorAction()
    data class FriendlyAzimuthInput(val input: Double): CalculatorAction()
    data class EnemyDistanceInput(val input: Double): CalculatorAction()
    data class EnemyAzimuthInput(val input: Double): CalculatorAction()
}