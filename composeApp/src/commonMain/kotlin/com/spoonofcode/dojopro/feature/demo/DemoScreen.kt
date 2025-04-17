package com.spoonofcode.dojopro.feature.demo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
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
            // Przykładowe dane
            val pendingPlayers = listOf(
                "Piotr Nowak",
                "Łukasz Wróbel",
                "Aleksander Miodek"
            )
            val confirmedPlayers = listOf(
                "Piotr Nowak (GK M)",
                "Paweł Kotoniak",
                "Rafał Stawinoga"
            )

// Nagłówek gry
            Text(
                text = "Gierka orlikowa",
                style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Sekcja z detalami (data, adres, liczba graczy, poziom, cena, płatność)
            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Timer,
                            contentDescription = "Data i godzina",
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Czwartek, 18.11.2023 | 18:30 - 20:00")
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "Adres",
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Boisko Wanda (Bulwarowa)")
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.People,
                            contentDescription = "Liczba graczy",
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Liczba graczy: 12 | Poziom: Normalny / Średni")
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row {
                        Text("Cena: 13 zł")
                        Spacer(modifier = Modifier.width(16.dp))
                        Text("Płatność: Gotówka / BLIK")
                    }
                }
            }

            // Szczegóły gierki
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Szczegóły gierki",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                style = MaterialTheme.typography.bodyMedium
            )

            // Lista oczekujących
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Oczekujący na akceptację",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Column {
                pendingPlayers.forEach { playerName ->
                    PendingPlayerRow(name = playerName)
                }
            }

            // Lista graczy
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Lista graczy",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Column {
                confirmedPlayers.forEach { playerName ->
                    PlayerRow(name = playerName)
                }
            }
        }
    }


    @Composable
    fun PendingPlayerRow(name: String) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Avatar
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray)
            )
            Spacer(modifier = Modifier.width(8.dp))
            // Nazwa gracza
            Text(
                text = name,
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium),
                modifier = Modifier.weight(1f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            // Ikony akcji (np. accept / reject)
            IconButton(onClick = { /* obsługa usunięcia */ }) {
                Text("Usuń")
            }
            IconButton(onClick = { /* obsługa akceptacji */ }) {
                Text("+")
            }
        }
    }

    @Composable
    fun PlayerRow(name: String) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Avatar
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray)
            )
            Spacer(modifier = Modifier.width(8.dp))
            // Nazwa gracza
            Text(
                text = name,
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium),
                modifier = Modifier.weight(1f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            // Ewentualne dodatkowe ikony (np. statystyki gracza)
            IconButton(onClick = { /* np. statystyki */ }) {
                Text("S")
            }
        }
    }

    @Preview
    @Composable
    private fun DemoScreenPreview() {
        DemoScreen()
    }
}