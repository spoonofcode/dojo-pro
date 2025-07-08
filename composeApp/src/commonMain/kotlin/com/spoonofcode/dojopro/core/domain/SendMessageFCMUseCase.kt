package com.spoonofcode.dojopro.core.domain

import com.spoonofcode.dojopro.core.data.repository.MessageFCMRepository
import com.spoonofcode.dojopro.core.model.MessageFCM

class SendMessageFCMUseCase(
    private val messageFCMRepository: MessageFCMRepository,
) {
    suspend operator fun invoke(
        messageDto: MessageFCM,
    ) {
        messageFCMRepository.sendMessage(messageDto)
    }
}