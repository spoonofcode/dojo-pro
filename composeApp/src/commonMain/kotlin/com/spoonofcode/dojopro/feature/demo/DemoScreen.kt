package com.spoonofcode.dojopro.feature.demo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.spoonofcode.dojopro.core.ui.BaseScreen
import com.spoonofcode.dojopro.core.ui.ext.koinViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

internal class DemoScreen(
    override val backNavigationEnable: Boolean = false,
) : BaseScreen<DemoViewModel, DemoViewState>() {

    @Composable
    override fun provideViewModel() = koinViewModel<DemoViewModel>()

    @Composable
    override fun provideContentView(
        viewModel: DemoViewModel,
        viewState: DemoViewState,
    ): @Composable ColumnScope.() -> Unit {

        LaunchedEffect(Unit) {
            viewModel.initView()
        }

        return ContentView(
            viewState = viewState,
        )
    }

    @Composable
    internal fun ContentView(
        viewState: DemoViewState,
    ): @Composable (ColumnScope.() -> Unit) {

        var textFieldValue by remember { mutableStateOf("") }
        var switchState by remember { mutableStateOf(false) }
        var checkboxState by remember { mutableStateOf(false) }
        var sliderValue by remember { mutableStateOf(0f) }

        return {
            Text(
                text = "Primary Color",
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(16.dp),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text("Primary Button")
            }

            Text(
                text = "Secondary Color",
                color = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.secondary)
                    .padding(16.dp),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary
                )
            ) {
                Text("Secondary Button")
            }

            OutlinedTextField(
                value = textFieldValue,
                onValueChange = { textFieldValue = it },
                label = { Text("Enabled TextField") },
            )

            OutlinedTextField(
                value = textFieldValue,
                onValueChange = { textFieldValue = it },
                label = { Text("Disabled TextField") },
                enabled = false,
            )

            OutlinedTextField(
                value = textFieldValue,
                onValueChange = { textFieldValue = it },
                label = { Text("Error TextField") },
                isError = true,
            )

            Switch(
                checked = switchState,
                onCheckedChange = { switchState = it },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = MaterialTheme.colorScheme.primary,
                    uncheckedThumbColor = MaterialTheme.colorScheme.secondary
                )
            )

            Checkbox(
                checked = checkboxState,
                onCheckedChange = { checkboxState = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = MaterialTheme.colorScheme.primary,
                    uncheckedColor = MaterialTheme.colorScheme.secondary
                )
            )

            Slider(
                value = sliderValue,
                onValueChange = { sliderValue = it },
                colors = SliderDefaults.colors(
                    thumbColor = MaterialTheme.colorScheme.primary,
                    activeTrackColor = MaterialTheme.colorScheme.primary,
                    inactiveTrackColor = MaterialTheme.colorScheme.secondary
                )
            )

            Text(
                text = "Surface Color",
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(16.dp),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Surface(
                modifier = Modifier.padding(8.dp),
                color = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface
            ) {
                Text(
                    text = "Surface Component",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }

    @Preview
    @Composable
    private fun DemoScreenPreview() {
        DemoScreen()
    }
}