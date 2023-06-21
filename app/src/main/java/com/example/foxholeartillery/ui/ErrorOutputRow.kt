package com.example.foxholeartillery.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.foxholeartillery.R

@Composable
fun ErrorOutputRow(
    modifier: Modifier = Modifier,
    presenter: PresenterError
) {
    val screenState = presenter.state.collectAsState()

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        if (screenState.value.calculationError) {
            Text(
                color = Color.Red,
                text = stringResource(R.string.calculation_error)
            )
        }

        if (screenState.value.rangeError) {
            Text(
                color = Color.Red,
                text = stringResource(R.string.out_of_range)
            )
        }
    }
}