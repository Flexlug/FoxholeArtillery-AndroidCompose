package com.example.foxholeartillery.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foxholeartillery.R
import java.util.Locale

@Composable
fun ResultOutputRow(
    modifier: Modifier = Modifier,
    presenter: PresenterOutput
) {
    val screenState = presenter.state.collectAsState().value

    Row(modifier) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(stringResource(R.string.distance).uppercase(Locale.getDefault()))

            Text(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                text = screenState.calculatedEnemyDistance.let {
                    if (!it.isNaN())
                    // Leave only 1 digit after comma
                        (it * 10).toInt() / 10
                    else it
                }.toString()
            )
        }

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(stringResource(R.string.azimuth).uppercase(Locale.getDefault()))

            Text(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                text = screenState.calculatedEnemyAzimuth.let {
                    if (!it.isNaN())
                    // Leave only 1 digit after comma
                        (it * 10).toInt() / 10
                    else it
                }.toString()
            )
        }
    }
}