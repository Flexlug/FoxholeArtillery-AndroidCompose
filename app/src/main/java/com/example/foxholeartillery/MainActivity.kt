package com.example.foxholeartillery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.foxholeartillery.ui.theme.FoxholeArtilleryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoxholeArtilleryTheme {
                val viewModel = viewModel<CalculatorViewModel>()

                Calculator(
                    modifier = Modifier,
                    state = viewModel.state,
                    onAction = viewModel::onAction
                )
            }
        }
    }
}