package com.spoonofcode.dojopro.core.data.repository

import com.spoonofcode.dojopro.core.data.mockdata.TypeMockData.TYPES
import com.spoonofcode.dojopro.core.data.mockdata.TypeMockData.TYPE_1
import com.spoonofcode.dojopro.core.model.Type
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock

internal fun typeRepositoryMock() = mock<TypeRepository> {
    everySuspend { create(any<Type>()) } returns TYPE_1
    everySuspend { read(any()) } returns TYPE_1
    everySuspend { readAll() } returns TYPES
}