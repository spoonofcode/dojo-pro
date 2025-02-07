package com.spoonofcode.dojopro.core.base.ui.compose

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.spoonofcode.dojopro.core.base.ui.Dimens

object Spacers {

    @Composable
    fun BottomSpace(): Unit = Spacer(modifier = Modifier.height(Dimens.bottomSpace))

    @Composable
    fun VerticalBetweenFields(): Unit = Spacer(modifier = Modifier.height(Dimens.fieldsPadding))

    @Composable
    fun HorizontalBetweenFields(): Unit = Spacer(modifier = Modifier.width(Dimens.fieldsPadding))

    @Composable
    fun Weight1(rowScope: RowScope): Unit =
        with(rowScope) { Spacer(modifier = Modifier.weight(1f)) }

    @Composable
    fun Weight1(columnScope: ColumnScope): Unit =
        with(columnScope) { Spacer(modifier = Modifier.weight(1f)) }
}