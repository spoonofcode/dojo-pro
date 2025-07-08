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
import com.spoonofcode.dojopro.core.ui.utils.LocalClipboardManager
import com.spoonofcode.dojopro.resources.Res
import com.spoonofcode.dojopro.resources.notification_body
import com.spoonofcode.dojopro.resources.notification_title
import com.spoonofcode.dojopro.resources.remote_user_token
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

internal class ChatScreen : BaseScreen<ChatViewModel, ChatViewState>() {

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
            onMessageTextChange = { viewModel.onMessageTextChange(it) },
            onMessageTitleChange = { viewModel.onMessageTitleChange(it) },
            sendToUser = { viewModel.sendToUser() },
            sendToTopics = { viewModel.sendToTopics() },
            onRemoteTokenChange = { viewModel.onRemoteTokenChange(it) },
            getFirebaseMessageToken = { viewModel.getFirebaseMessageToken() },
            clickOnTopicToSend = { viewModel.clickOnTopicToSend(it) },
            clickOnTopicToSubscribe = { viewModel.clickOnTopicToSubscribe(it) },
        )
    }

    @Composable
    internal fun ContentView(
        viewState: ChatViewState,
        onMessageTextChange: (String) -> Unit,
        onMessageTitleChange: (String) -> Unit,
        sendToUser: () -> Unit,
        sendToTopics: () -> Unit,
        onRemoteTokenChange: (String) -> Unit,
        getFirebaseMessageToken: () -> Unit,
        clickOnTopicToSend: (String) -> Unit,
        clickOnTopicToSubscribe: (String) -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            Buttons.PrimaryButton(
                text = "Get user token",
                onClick = getFirebaseMessageToken
            )

            Texts.HSB("Firebase user message token: ${viewState.fcmUserToken}")

            val clipboardManager = LocalClipboardManager.current

            Buttons.PrimaryButton(
                text = "Copy to Clipboard",
                onClick = { clipboardManager?.setText(viewState.fcmUserToken) }
            )

            Spacers.VerticalBetweenFields()

            TextFields.Outlined(
                value = viewState.fcmRemoteUserToken,
                onValueChange = onRemoteTokenChange,
                label = stringResource(resource = Res.string.remote_user_token),
            )

            Spacers.VerticalBetweenFields()

            TextFields.Outlined(
                value = viewState.notificationTitle,
                onValueChange = onMessageTitleChange,
                label = stringResource(resource = Res.string.notification_title),
            )

            Spacers.VerticalBetweenFields()

            TextFields.Outlined(
                value = viewState.notificationBody,
                onValueChange = onMessageTextChange,
                label = stringResource(resource = Res.string.notification_body),
            )

            Spacers.VerticalBetweenFields()

            Buttons.PrimaryButton(
                text = "Send to user",
                onClick = sendToUser
            )
            Spacers.VerticalBetweenFields()

            Buttons.PrimaryButton(
                text = "Send to topics",
                onClick = sendToTopics
            )

            Chips.SelectableChipsGroup(
                label = "Selected topics to send",
                options = viewState.topics,
                selectedOptions = viewState.selectedTopicsToSend,
                onOptionClick = clickOnTopicToSend,
            )

            Chips.SelectableChipsGroup(
                label = "Selected topics to subscribe",
                options = viewState.topics,
                selectedOptions = viewState.selectedTopicsToSubscribe,
                onOptionClick = clickOnTopicToSubscribe,
            )
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