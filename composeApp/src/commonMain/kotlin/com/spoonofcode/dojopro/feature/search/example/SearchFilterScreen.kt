package com.spoonofcode.dojopro.feature.search.example

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen

class SearchFilterScreen() : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {

        val allProducts = listOf(
            Product(1, "Smartphone", "Electronics"),
            Product(2, "Laptop", "Electronics"),
            Product(3, "Jeans", "Clothing"),
            Product(4, "T-Shirt", "Clothing"),
            Product(5, "Kotlin Programming Book", "Books"),
            Product(6, "The Great Gatsby", "Books")
        )

        // Search text state
        var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
        // Selected filter
        var selectedFilter by remember { mutableStateOf(FilterOption.All) }
        // Whether the filter dropdown is expanded
        var isFilterDropdownExpanded by remember { mutableStateOf(false) }

        // Filter the list based on searchQuery and selectedFilter
        val filteredProducts = remember(searchQuery.text, selectedFilter) {
            allProducts.filter { product ->
                val matchesSearch = product.name.contains(searchQuery.text, ignoreCase = true)
                val matchesFilter = when (selectedFilter) {
                    FilterOption.All -> true
                    FilterOption.Electronics -> product.category == "Electronics"
                    FilterOption.Clothing -> product.category == "Clothing"
                    FilterOption.Books -> product.category == "Books"
                }
                matchesSearch && matchesFilter
            }
        }

        // UI layout
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Search TextField
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { newValue -> searchQuery = newValue },
                label = { Text("Search products...") },
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            FilterDropdown(isFilterDropdownExpanded, selectedFilter)

            Spacer(modifier = Modifier.height(16.dp))

            // Product List
            LazyColumn {
                items(filteredProducts) { product ->
                    ProductListItem(product = product)
                    Divider()
                }
            }
        }
    }

    @Composable
    private fun FilterDropdown(
        isFilterDropdownExpanded: Boolean,
        selectedFilter: FilterOption
    ) {
        var isFilterDropdownExpanded1 = isFilterDropdownExpanded
        var selectedFilter1 = selectedFilter
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedButton(onClick = {
                isFilterDropdownExpanded1 = !isFilterDropdownExpanded1
            }) {
                Text(text = "Filter: ${selectedFilter1.name}")
            }

            DropdownMenu(
                expanded = isFilterDropdownExpanded1,
                onDismissRequest = { isFilterDropdownExpanded1 = false }
            ) {
                DropdownMenuItem(
                    text = { Text("All") },
                    onClick = {
                        selectedFilter1 = FilterOption.All
                        isFilterDropdownExpanded1 = false
                    })
                DropdownMenuItem(
                    text = { Text("Electronics") },
                    onClick = {
                        selectedFilter1 = FilterOption.Electronics
                        isFilterDropdownExpanded1 = false
                    })
                DropdownMenuItem(
                    text = {
                        Text("Clothing")
                    },
                    onClick = {
                        selectedFilter1 = FilterOption.Clothing
                        isFilterDropdownExpanded1 = false
                    })
                DropdownMenuItem(
                    text = {
                        Text("Books")
                    },
                    onClick = {
                        selectedFilter1 = FilterOption.Books
                        isFilterDropdownExpanded1 = false
                    })
            }
        }
    }
}

@Composable
fun ProductListItem(product: Product) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                // handle item click
            }
            .padding(8.dp)
    ) {
        Text(text = product.name, style = MaterialTheme.typography.titleMedium)
        Text(text = product.category, style = MaterialTheme.typography.bodyMedium)
    }
}