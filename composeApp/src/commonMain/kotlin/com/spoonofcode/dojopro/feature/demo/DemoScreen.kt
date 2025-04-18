package com.spoonofcode.dojopro.feature.demo

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.spoonofcode.dojopro.core.ext.formatedLocalDate
import com.spoonofcode.dojopro.core.ext.formatedLocalDateTime
import com.spoonofcode.dojopro.core.ext.minus
import com.spoonofcode.dojopro.core.ui.BaseScreen
import com.spoonofcode.dojopro.core.ui.ext.koinViewModel
import com.spoonofcode.dojopro.core.ui.utils.LocalDateTimeUtils
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import org.jetbrains.compose.ui.tooling.preview.Preview

internal class DemoScreen(
    override val backNavigationEnable: Boolean = false,
    override val verticalScrollEnable: Boolean = false,
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

        /* ---------- sample data ---------- */
        val sampleNotes = listOf(
            Note(
                id = 1,
                text = "Buy milk 🥛",
                createdAt = LocalDateTimeUtils.now()
            ),
            Note(
                id = 2,
                text = "Send weekly report 📄",
                createdAt = LocalDateTimeUtils.now().minus(5)
            ),
            Note(
                id = 3,
                text = "Book summer flights ✈️",
                createdAt = LocalDateTimeUtils.now().minus(22)
            ),
            Note(
                id = 4,
                text = "Read Compose tutorial 📚",
                createdAt = LocalDateTimeUtils.now().minus(45)
            ),
            Note(
                id = 5,
                text = "Check crypto prices ₿",
                createdAt = LocalDateTimeUtils.now().minus(44)
            )
        )
        
        return {
            NotesScreen(sampleNotes)
        }
    }

    /* ---------- model ---------- */
    data class Note(
        val id: Long,
        val text: String,
        val createdAt: LocalDateTime            // or java.time.Instant etc.
    )

    /* we’ll render one of these two in the LazyColumn */
    sealed interface ListEntry {
        data class DateHeader(val date: LocalDate) : ListEntry
        data class ContentItem(val note: Note) : ListEntry
    }

    /* ---------- helper to flatten the list ---------- */
    fun buildEntries(notes: List<Note>): List<ListEntry> =
        notes
            .sortedByDescending { it.createdAt }                  // newest first
            .groupBy { it.createdAt.date }               // bucket per calendar day
            .flatMap { (date, dayNotes) ->
                listOf(ListEntry.DateHeader(date)) +              // header first
                        dayNotes.map { ListEntry.ContentItem(it) }        // then that day’s items
            }

    /* ---------- Composables ---------- */
    @Composable
    fun NotesScreen(allNotes: List<Note>) {
        val list = remember(allNotes) { buildEntries(allNotes) }

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            /* we can rely on sealed‑class type‑safety */
            list.forEach { entry ->
                when (entry) {
                    is ListEntry.DateHeader -> {
                        stickyHeader {
                            DateSeparator(date = entry.date)
                        }
                    }

                    is ListEntry.ContentItem -> {
                        item(key = entry.note.id) {
                            NoteRow(note = entry.note)
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun DateSeparator(date: LocalDate) {
        val today = remember { LocalDateTimeUtils.now() }
        val label = when (date) {
            today.date -> "Today"
            today.minus(24) -> "Yesterday"
            else -> date.formatedLocalDate()
        }
        Surface(
            Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.error
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp, horizontal = 16.dp)
            )
        }
    }

    @Composable
    private fun NoteRow(note: Note) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = note.text,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = note.createdAt.formatedLocalDateTime(),
                style = MaterialTheme.typography.labelSmall,
                textAlign = TextAlign.End
            )
        }
    }

    @Preview
    @Composable
    private fun DemoScreenPreview() {
        DemoScreen()
    }
}