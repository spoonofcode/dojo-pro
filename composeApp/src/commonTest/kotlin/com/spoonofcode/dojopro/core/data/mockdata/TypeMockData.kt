package com.spoonofcode.dojopro.core.data.mockdata

import com.spoonofcode.dojopro.core.model.Type

object TypeMockData {
    val TYPE_1 = Type(
        id = 1,
        name = "Type 1",
    )
    val TYPE_2 = Type(
        id = 2,
        name = "Type 2",
    )
    val TYPE_3 = Type(
        id = 3,
        name = "Type 3",
    )
    val TYPES = listOf(TYPE_1, TYPE_2, TYPE_3)
}