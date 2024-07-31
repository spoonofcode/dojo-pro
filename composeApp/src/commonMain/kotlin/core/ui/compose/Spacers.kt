package core.ui.compose

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import core.ui.Dimens

object Spacers {

    @Composable
    fun BottomSpace(): Unit = Spacer(modifier = Modifier.height(Dimens.bottomSpace))

    @Composable
    fun BetweenFields(): Unit = Spacer(modifier = Modifier.height(Dimens.fieldsPadding))

    @Composable
    fun Weight1(rowScope: RowScope): Unit =
        with(rowScope) { Spacer(modifier = Modifier.weight(1f)) }

    @Composable
    fun Weight1(columnScope: ColumnScope): Unit =
        with(columnScope) { Spacer(modifier = Modifier.weight(1f)) }
}