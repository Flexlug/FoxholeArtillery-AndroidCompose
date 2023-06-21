package com.example.foxholeartillery.ui

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role

// Custom ToggleButton
// Default IconToggleButton ripples are clipped by 24.dp

@Composable
fun ArtilleryToggleButton(
    modifier: Modifier,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .toggleable(
                value = checked,
                onValueChange = onCheckedChange,
                role = Role.Checkbox,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true)
            ),
        contentAlignment = Alignment.Center
    ) {
        CompositionLocalProvider(content = content)
    }
}