package com.example.foxholeartillery.ui

import EnemyInputRow
import FriendlyInputRow
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foxholeartillery.CalculatorAction
import com.example.foxholeartillery.CalculatorViewModel
import com.example.foxholeartillery.R
import java.util.Locale

@Composable
fun Calculator(
    modifier: Modifier = Modifier,
    viewModel: CalculatorViewModel
) {
    Column(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxHeight()
            .then(modifier)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.overlaywallpaper),
                contentDescription = "Background"
            )

            ArtilleryTypeSelector(
                presenter = PresenterTypeSelector(viewModel)
            )

            Divider()

            FriendlyInputRow(presenter = PresenterInput(viewModel))

            Divider()

            EnemyInputRow(presenter = PresenterInput(viewModel))

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
                        ResultOutputRow(presenter = PresenterOutput(viewModel))

                        ErrorOutputRow(presenter = PresenterError(viewModel))
                    }
                }
            }
        }
    }
}