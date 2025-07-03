package com.spoonofcode.dojopro.feature.chat

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.spoonofcode.dojopro.core.ui.BaseScreen
import com.spoonofcode.dojopro.core.ui.compose.Buttons
import com.spoonofcode.dojopro.core.ui.compose.Chips
import com.spoonofcode.dojopro.core.ui.compose.Spacers
import com.spoonofcode.dojopro.core.ui.compose.TextFields
import com.spoonofcode.dojopro.core.ui.compose.Texts
import com.spoonofcode.dojopro.resources.Res
import com.spoonofcode.dojopro.resources.message_text
import com.spoonofcode.dojopro.resources.message_title
import com.spoonofcode.dojopro.resources.remote_user_token
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

internal class ChatScreen(
    override val backNavigationEnable: Boolean = false,
    override val verticalScrollEnable: Boolean = false,
) : BaseScreen<ChatViewModel, ChatViewState>() {

    @Composable
    override fun provideViewModel() = koinViewModel<ChatViewModel>()

    @Composable
    override fun provideContentView(
        viewModel: ChatViewModel,
        viewState: ChatViewState,
    ): @Composable ColumnScope.() -> Unit {

        LaunchedEffect(Unit) {
//            viewModel.initView()
        }

        return ContentView(
            viewState = viewState,
            onMessageChange = { viewModel.onMessageChange(it) },
            onMessageSend = { viewModel.onMessageSend(isBroadcast = false) },
            onMessageBroadcast = { viewModel.onMessageSend(isBroadcast = true) },
            onRemoteTokenChange = { viewModel.onRemoteTokenChange(it) },
            getFirebaseMessageToken = { viewModel.getFirebaseMessageToken() },
        )
    }

    @Composable
    internal fun ContentView(
        viewState: ChatViewState,
        onMessageChange: (String) -> Unit,
        onMessageSend: () -> Unit,
        onMessageBroadcast: () -> Unit,
        onRemoteTokenChange: (String) -> Unit,
        getFirebaseMessageToken: () -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            Buttons.PrimaryButton(
                text = "Get user token",
                onClick = getFirebaseMessageToken
            )

            Texts.HSB("Firebase user message token: ${viewState.firebaseMessageToken}")

            Spacers.VerticalBetweenFields()

            TextFields.Outlined(
                value = viewState.remoteToken,
                onValueChange = onRemoteTokenChange,
                label = stringResource(resource = Res.string.remote_user_token),
            )

            Spacers.VerticalBetweenFields()

            TextFields.Outlined(
                value = viewState.messageTitle,
                onValueChange = onMessageChange,
                label = stringResource(resource = Res.string.message_title),
            )

            Spacers.VerticalBetweenFields()

            TextFields.Outlined(
                value = viewState.messageText,
                onValueChange = onMessageChange,
                label = stringResource(resource = Res.string.message_text),
            )

            Spacers.VerticalBetweenFields()

            Buttons.PrimaryButton(
                text = "Send to user",
                onClick = onMessageSend
            )
            Spacers.VerticalBetweenFields()

            Buttons.PrimaryButton(
                text = "Send to topics",
                onClick = onMessageBroadcast
            )

            Chips.SelectableChipsGroup()
        }
    }

//    @Preview
//    @Composable
//    private fun ChatScreenPreview() {
//        ContentView(
//            snackbarHostState = remember { SnackbarHostState() },
//            content = ContentView(
//                viewState = ChatViewState(),
//            )
//        )
//    }
}