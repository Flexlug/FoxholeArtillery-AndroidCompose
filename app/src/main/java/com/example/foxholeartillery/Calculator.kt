package com.example.foxholeartillery

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.icu.text.DecimalFormat
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foxholeartillery.ui.theme.FoxholeArtilleryTheme
import java.util.Locale

@Preview(
    showSystemUi = true,
    uiMode = UI_MODE_NIGHT_YES,
    locale = "ru-rRU"
)
@Composable
fun Calculator(
    modifier: Modifier = Modifier,
    state: CalculatorState = CalculatorState(
        calculationError = true,
        rangeError = true
    ),
    onAction: (CalculatorAction) -> Unit = {  }
) {
    FoxholeArtilleryTheme(
        darkTheme = true
    ) {
        Column(
            modifier = Modifier
                .background(Color.Black)
                .fillMaxHeight()
                .then(modifier)
        ) {
            Image(
                painter = painterResource(id = R.drawable.overlaywallpaper),
                contentDescription = "Overlay wallpaper",
                modifier = Modifier.fillMaxWidth()
            )

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ArtilleryTypeSelector(
                    state = state.selectedArtilleryType,
                    onChange = { onAction(CalculatorAction.ChangeArtilleryType(it)) }
                )

                Divider()

                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = stringResource(R.string.to_friendly_position),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.CenterStart)
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    DoubleField(
                        modifier = Modifier.weight(1f),
                        label = stringResource(id = R.string.distance),
                        value = state.friendlyDistanceInput,
                        onNumberChange = {
                            onAction(CalculatorAction.FriendlyDistanceInput(it))
                        }
                    )

                    DoubleField(
                        modifier = Modifier.weight(1f),
                        label = stringResource(id = R.string.azimuth),
                        value = state.friendlyAzimuthInput,
                        onNumberChange = {
                            onAction(CalculatorAction.FriendlyAzimuthInput(it))
                        }
                    )
                }

                Divider()

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.to_enemy_position),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.CenterStart)
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    DoubleField(
                        modifier = Modifier.weight(1f),
                        label = stringResource(id = R.string.distance),
                        value = state.enemyDistanceInput,
                        onNumberChange = {
                            onAction(CalculatorAction.EnemyDistanceInput(it))
                        }
                    )

                    DoubleField(
                        modifier = Modifier.weight(1f),
                        label = stringResource(id = R.string.azimuth),
                        value = state.enemyAzimuthInput,
                        onNumberChange = {
                            onAction(CalculatorAction.EnemyAzimuthInput(it))
                        }
                    )
                }

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.BottomStart
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Divider()

                        Box(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = stringResource(R.string.enemy_coordinates),
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.align(Alignment.CenterStart)
                            )
                        }

                        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                Text(stringResource(R.string.distance).uppercase(Locale.getDefault()))

                                Text(
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    text = state.calculatedEnemyDistance.let {
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
                                    text = state.calculatedEnemyAzimuth.let {
                                        if (!it.isNaN())
                                        // Leave only 1 digit after comma
                                            (it * 10).toInt() / 10
                                        else it
                                    }.toString()
                                )
                            }

                            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                if (state.calculationError) {
                                    Text(
                                        color = Color.Red,
                                        text = stringResource(R.string.calculation_error)
                                    )
                                }

                                if (state.rangeError) {
                                    Text(
                                        color = Color.Red,
                                        text = stringResource(R.string.out_of_range)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

    }
}
