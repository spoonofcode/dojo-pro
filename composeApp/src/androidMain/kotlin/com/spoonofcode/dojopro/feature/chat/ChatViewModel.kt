package com.spoonofcode.dojopro.feature.chat

import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.messaging.messaging
import com.spoonofcode.dojopro.core.domain.SendMessageFCMUseCase
import com.spoonofcode.dojopro.core.model.MessageFCM
import com.spoonofcode.dojopro.core.model.NotificationBody
import com.spoonofcode.dojopro.core.ui.BaseViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.cancellation.CancellationException

class ChatViewModel(
    private val sendMessageFCMUseCase: SendMessageFCMUseCase,
) : BaseViewModel<ChatViewState>(ChatViewState()) {

    init {
        viewModelScope.launch {
            Firebase.messaging.subscribeToTopic("chat").await()
        }
    }

    fun onRemoteTokenChange(newToken: String) {
        updateState {
            copy(
                remoteToken = newToken,
            )
        }
    }

    fun onSubmitRemoteToken() {
        updateState {
            copy(
                isEnteringToken = false
            )
        }
    }

    fun onMessageChange(message: String) {
        updateState {
            copy(
                messageText = message,
            )
        }
    }

    fun onMessageSend(isBroadcast: Boolean) {
        viewModelScope.launch {
            val messageDto = MessageFCM(
                to = if (isBroadcast) null else currentState().remoteToken,
                notification = NotificationBody(
                    title = "New message!",
                    body = currentState().messageText,
                )
            )

            try {
                sendMessageFCMUseCase(
                    messageDto = messageDto,
                )

                updateState {
                    copy(
                        messageText = "",
                    )
                }

            } catch (ce: CancellationException) {
                throw ce
            } catch (e: Exception) {
                showErrorView()
            }
        }
    }

    private fun showErrorView() {
        updateState {
            copy(
                isLoadingView = false,
                isErrorView = true,
            )
        }
    }
}