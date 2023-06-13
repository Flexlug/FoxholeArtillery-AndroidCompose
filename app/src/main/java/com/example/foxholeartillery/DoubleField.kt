package com.example.foxholeartillery

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun DoubleField(
    modifier: Modifier = Modifier,
    label: String = "Distance",
    value: Double = 0.0,
    onNumberChange: (Double) -> Unit,
) {
    val number = remember { mutableStateOf(value) }
    val textValue = remember(value != number.value) {
        number.value = value
        mutableStateOf(value.let {
            if (it == 0.0) {
                ""
            } else if (it % 1.0 == 0.0) {
                it.toInt().toString()
            } else {
                it.toString()
            }
        })
    }

    val numberRegex = remember { "[-]?[\\d]*[.]?[\\d]*".toRegex() }

    TextField(
        modifier = Modifier
            .then(modifier),
        value = textValue.value,
        label = { Text(label) },
        placeholder = { Text("0.0") },
        onValueChange = {
            if (numberRegex.matches(it)) {
                textValue.value = it
                number.value = it.toDoubleOrNull() ?: return@TextField
                onNumberChange(number.value)
            }
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}