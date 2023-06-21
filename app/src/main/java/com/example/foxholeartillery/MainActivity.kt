package com.example.foxholeartillery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.Modifier
import com.example.foxholeartillery.ui.Calculator
import com.example.foxholeartillery.ui.theme.FoxholeArtilleryTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<CalculatorViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoxholeArtilleryTheme {
                Calculator(
                    modifier = Modifier,
                    viewModel = viewModel
                )
            }
        }
    }
}