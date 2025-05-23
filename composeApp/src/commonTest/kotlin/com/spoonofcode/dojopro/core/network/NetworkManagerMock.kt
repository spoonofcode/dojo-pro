package com.spoonofcode.dojopro.core.network

import dev.mokkery.answering.returns
import dev.mokkery.every
import dev.mokkery.mock
import kotlinx.coroutines.flow.MutableSharedFlow

internal fun networkManagerMock() = mock<NetworkManager>()
    .apply {
        every { observeNetworkState() } returns MutableSharedFlow(1)
    }