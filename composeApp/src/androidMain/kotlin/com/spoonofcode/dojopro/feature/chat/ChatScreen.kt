package com.spoonofcode.dojopro.feature.chat

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.google.firebase.Firebase
import com.google.firebase.messaging.messaging
import com.spoonofcode.dojopro.core.ui.BaseScreen
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
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
            onSubmitRemoteToken = { viewModel.onSubmitRemoteToken() },
        )
    }

    @Composable
    fun LocalChatScreen(
        messageText: String,
        onMessageChange: (String) -> Unit,
        onMessageSend: () -> Unit,
        onMessageBroadcast: () -> Unit,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            OutlinedTextField(
                value = messageText,
                onValueChange = onMessageChange,
                placeholder = {
                    Text("Enter a message")
                },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            )
            Spacer(Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                IconButton(
                    onClick = onMessageSend,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Icon(
                        imageVector = Icons.Default.Send,
                        contentDescription = "Send"
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                IconButton(
                    onClick = onMessageBroadcast,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Broadcast"
                    )
                }
            }
        }
    }

    @Composable
    internal fun ContentView(
        viewState: ChatViewState,
        onMessageChange: (String) -> Unit,
        onMessageSend: () -> Unit,
        onMessageBroadcast: () -> Unit,
        onRemoteTokenChange: (String) -> Unit,
        onSubmitRemoteToken: () -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {

            if (viewState.isEnteringToken) {
                EnterTokenDialog(
                    token = viewState.remoteToken,
                    onTokenChange = { onRemoteTokenChange(it) },
                    onSubmit = { onSubmitRemoteToken() }
                )
            } else {
                LocalChatScreen(
                    messageText = viewState.messageText,
                    onMessageSend = { onMessageSend() },
                    onMessageBroadcast = { onMessageBroadcast() },
                    onMessageChange = { onMessageChange(it) }
                )
            }
        }
    }


    @Composable
    fun EnterTokenDialog(
        token: String,
        onTokenChange: (String) -> Unit,
        onSubmit: () -> Unit,
    ) {
        val clipboardManager = LocalClipboardManager.current
        val context = LocalContext.current
        val scope = rememberCoroutineScope()

        Dialog(
            onDismissRequest = {},
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false,
            )
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(5.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(16.dp)
            ) {
                OutlinedTextField(
                    value = token,
                    onValueChange = onTokenChange,
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text("Remote user token")
                    },
                    maxLines = 1
                )
                Spacer(Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                ) {
                    OutlinedButton(
                        onClick = {
                            scope.launch {
                                val token = Firebase.messaging.token.await()
                                clipboardManager.setText(AnnotatedString(token))

                                Toast.makeText(
                                    context, "Copied local token!", Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    ) {
                        Text("Copy token")
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedButton(
                    onClick = onSubmit,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text("Submit")
                }
            }
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