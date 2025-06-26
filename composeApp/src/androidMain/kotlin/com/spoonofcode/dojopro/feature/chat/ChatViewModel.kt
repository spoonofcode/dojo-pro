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
import kotlinx.io.IOException
import retrofit2.HttpException

class ChatViewModel(
    private val sendMessageFCMUseCase: SendMessageFCMUseCase,
) : BaseViewModel<ChatViewState>(ChatViewState()) {

//    private val api: FcmApi = Retrofit.Builder()
//        .baseUrl("https://10.0.2.2:8443/")
//        .addConverterFactory(MoshiConverterFactory.create())
//        .build()
//        .create()

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

//                if (isBroadcast) {
//                    api.broadcast(messageDto)
//                } else {
//                    api.sendMessage(messageDto)
//                }

                updateState {
                    copy(
                        messageText = "",
                    )
                }

            } catch (e: HttpException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }


        }
    }
}