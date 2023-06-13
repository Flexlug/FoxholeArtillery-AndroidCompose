package com.example.foxholeartillery

data class CalculatorState(
    val friendlyDistanceInput: Double = 0.0,
    val friendlyAzimuthInput: Double = 0.0,
    val enemyDistanceInput: Double = 0.0,
    val enemyAzimuthInput: Double = 0.0,
    val selectedArtilleryType: ArtilleryType = ArtilleryType.FieldArtillery,
    val calculatedEnemyDistance: Double = 0.0,
    val calculatedEnemyAzimuth: Double = 0.0,
    val rangeError: Boolean = false,
    val calculationError: Boolean = false
)