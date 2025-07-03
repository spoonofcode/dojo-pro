package com.spoonofcode.dojopro.feature.chat

import androidx.lifecycle.viewModelScope
import com.spoonofcode.dojopro.core.domain.SendMessageFCMUseCase
import com.spoonofcode.dojopro.core.model.MessageFCM
import com.spoonofcode.dojopro.core.model.NotificationBody
import com.spoonofcode.dojopro.core.ui.BaseViewModel
import com.spoonofcode.dojopro.core.ui.utils.FirebasePushService
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

class ChatViewModel(
    private val sendMessageFCMUseCase: SendMessageFCMUseCase,
    private val firebasePushService: FirebasePushService,
) : BaseViewModel<ChatViewState>(ChatViewState()) {

    init {
        viewModelScope.launch {
            firebasePushService.subscribeToTopic("chat")
        }
    }

    fun onRemoteTokenChange(newToken: String) {
        updateState {
            copy(
                remoteToken = newToken,
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

    fun getFirebaseMessageToken() {
        viewModelScope.launch {
            val token = firebasePushService.getMessageToken()

            updateState {
                copy(
                    firebaseMessageToken = token,
                )
            }
        }
    }

    fun onMessageSend(isBroadcast: Boolean) {
        viewModelScope.launch {
            val currentState = currentState()
            val messageDto = MessageFCM(
                to = if (isBroadcast) null else currentState.remoteToken,
                notification = NotificationBody(
                    title = currentState.messageTitle,
                    body = currentState.messageText,
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

    fun clickOnTopicToSend(clickedTopic: String) {
        val currentSelectedTopicsToSend = currentState().selectedTopicsToSend.toMutableList()
        if (clickedTopic in currentSelectedTopicsToSend) {
            currentSelectedTopicsToSend.remove(clickedTopic)
        } else {
            currentSelectedTopicsToSend.add(clickedTopic)
        }
        updateState {
            copy(
                selectedTopicsToSend = currentSelectedTopicsToSend.toList()
            )
        }
    }

    fun clickOnTopicToSubscribe(clickedTopic: String) {
        val currentSelectedTopicsToSubscribe = currentState().selectedTopicsToSubscribe.toMutableList()
        if (clickedTopic in currentSelectedTopicsToSubscribe) {
            currentSelectedTopicsToSubscribe.remove(clickedTopic)
        } else {
            currentSelectedTopicsToSubscribe.add(clickedTopic)
        }
        updateState {
            copy(
                selectedTopicsToSubscribe = currentSelectedTopicsToSubscribe.toList()
            )
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