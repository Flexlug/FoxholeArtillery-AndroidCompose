package com.example.foxholeartillery

sealed class ArtilleryType {
    object FieldArtillery: ArtilleryType()
    object Gunboat: ArtilleryType()
    object Howitzer: ArtilleryType()
    object Mortar: ArtilleryType()
}