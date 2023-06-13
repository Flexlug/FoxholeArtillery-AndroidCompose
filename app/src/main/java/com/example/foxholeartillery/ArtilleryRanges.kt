package com.example.foxholeartillery

class ArtilleryRange(
    val min: Double,
    val max: Double
) {
    companion object {
        val FieldArtillery = ArtilleryRange(75.0, 150.0)
        val Gunboat = ArtilleryRange(50.0, 100.0)
        val Howitzer = ArtilleryRange(75.0, 150.0)
        val Mortar = ArtilleryRange(45.0, 65.0)
    }
}