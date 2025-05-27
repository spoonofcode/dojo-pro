package com.spoonofcode.dojopro.core.network

import com.spoonofcode.dojopro.core.test.OpenForMokkery
import dev.jordond.connectivity.Connectivity
import kotlinx.coroutines.flow.SharedFlow

@OpenForMokkery
class NetworkManager(
    private val connectivity: Connectivity
) {

    suspend fun isOnline(): Boolean = connectivity.status() is Connectivity.Status.Connected

    fun observeNetworkState(): SharedFlow<Connectivity.Status> = connectivity.statusUpdates
}