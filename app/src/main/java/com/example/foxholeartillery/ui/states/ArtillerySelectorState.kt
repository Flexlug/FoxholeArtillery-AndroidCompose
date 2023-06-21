package com.example.foxholeartillery.ui.states

import com.example.foxholeartillery.ArtilleryType

data class ArtillerySelectorState(
    val selectedArtilleryType: ArtilleryType = ArtilleryType.FieldArtillery,
)