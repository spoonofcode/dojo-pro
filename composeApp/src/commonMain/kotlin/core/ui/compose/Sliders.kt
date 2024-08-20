package core.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.jetbrains.compose.ui.tooling.preview.Preview

object Sliders {

    @Preview
    @Composable
    fun RangeSlider(
        label: String,
        minValue: Float,
        maxValue: Float,
        steps: Int,
    ) {
        var sliderPosition by remember { mutableStateOf(minValue..maxValue) }
        Column {
            Text(text = label + " ${sliderPosition.start.toInt()} - ${sliderPosition.endInclusive.toInt()}")

            RangeSlider(
                value = sliderPosition,
                steps = steps,
                onValueChange = { sliderPosition = it },
                valueRange = minValue..maxValue
            )
        }
    }
}