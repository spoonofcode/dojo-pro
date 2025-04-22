package com.spoonofcode.dojopro.core.ui.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.spoonofcode.dojopro.core.ui.Paddings
import com.spoonofcode.dojopro.resources.Res
import com.spoonofcode.dojopro.resources.dojo_pro

@Composable
fun LoadingView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Paddings.screenPadding)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            PulsatingIcon(iconRes = Res.drawable.dojo_pro)
        }
    }
}
