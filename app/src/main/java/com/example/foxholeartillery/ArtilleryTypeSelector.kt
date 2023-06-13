package com.example.foxholeartillery

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foxholeartillery.ui.theme.Gray200
import com.example.foxholeartillery.ui.theme.YellowSelected

@Preview
@Composable
fun ArtilleryTypeSelector(
    modifier: Modifier = Modifier,
    state: ArtilleryType = ArtilleryType.FieldArtillery,
    onChange: (ArtilleryType) -> Unit = {  }
) {
    val artilleryTypes = remember {
        mapOf(
            ArtilleryType.FieldArtillery to ArtilleryTypeData(
                ArtilleryType.FieldArtillery,
                R.drawable.fieldartillery_logo,
                R.string.field_artillery,
                R.string.field_artillery_description
            ),
            ArtilleryType.Gunboat to ArtilleryTypeData(
                ArtilleryType.Gunboat,
                R.drawable.gunship_logo,
                R.string.gunship,
                R.string.gunship_img_description
            ),
            ArtilleryType.Howitzer to ArtilleryTypeData(
                ArtilleryType.Howitzer,
                R.drawable.howitzerkit_logo,
                R.string.howitzer,
                R.string.howitzerkit_img_description
            ),
            ArtilleryType.Mortar to ArtilleryTypeData(
                ArtilleryType.Mortar,
                R.drawable.mortar_logo,
                R.string.mortar,
                R.string.mortar_img_description
            ),
        )
    }

    var (selectedArtillery, onArtillerySelectionChange) = remember {
        mutableStateOf(artilleryTypes[state]!!)
    }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.select_artillery_type),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterStart)
            )

            Text(
                text = stringResource(selectedArtillery.nameResource),
                modifier = Modifier.align(Alignment.CenterEnd)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .selectableGroup(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            artilleryTypes.forEach { (_, artilleryData) ->
                val selectedCurrent = selectedArtillery.type.javaClass == artilleryData.type.javaClass
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .aspectRatio(1f)
                        .weight(1f)
                        .selectable(
                            selected = selectedCurrent,
                            onClick = {  },
                            role = Role.RadioButton
                        )
                )
                {
                    CustomToggleButton(
                        modifier = Modifier
                            .background(if (selectedCurrent) YellowSelected else Gray200),
                        onCheckedChange = {
                            onArtillerySelectionChange(artilleryData)
                            onChange(artilleryData.type)
                        },
                        checked = selectedCurrent
                    ) {
                        Image(
                            painter = painterResource(id = artilleryData.imageResouce),
                            contentDescription = stringResource(id = artilleryData.descriptionResource),
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp)
                        )
                    }
                }
            }
        }
    }
}

data class ArtilleryTypeData (
    val type: ArtilleryType,
    val imageResouce: Int,
    val nameResource: Int,
    val descriptionResource: Int
)