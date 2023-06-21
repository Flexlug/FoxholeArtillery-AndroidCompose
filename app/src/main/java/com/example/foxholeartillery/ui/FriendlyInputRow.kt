import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.foxholeartillery.CalculatorAction
import com.example.foxholeartillery.R
import com.example.foxholeartillery.ui.NumberInput
import com.example.foxholeartillery.ui.PresenterInput

@Composable
fun FriendlyInputRow(
    modifier: Modifier = Modifier,
    presenter: PresenterInput
) {
    val screenState = presenter.state.collectAsState().value

    Column(modifier) {
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
            NumberInput(
                modifier = Modifier.weight(1f),
                label = stringResource(id = R.string.distance),
                value = screenState.friendlyDistanceInput,
                onNumberChange = {
                    presenter.onInput(CalculatorAction.FriendlyDistanceInput(it))
                }
            )

            NumberInput(
                modifier = Modifier.weight(1f),
                label = stringResource(id = R.string.azimuth),
                value = screenState.friendlyAzimuthInput,
                onNumberChange = {
                    presenter.onInput(CalculatorAction.FriendlyAzimuthInput(it))
                }
            )
        }
    }
}