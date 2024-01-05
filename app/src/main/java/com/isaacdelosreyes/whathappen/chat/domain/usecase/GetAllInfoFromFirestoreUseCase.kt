package com.isaacdelosreyes.whathappen.chat.domain.usecase

import com.isaacdelosreyes.whathappen.chat.domain.repository.ChatRepository
import com.isaacdelosreyes.whathappen.core.data.model.Response
import com.isaacdelosreyes.whathappen.core.data.model.conversation.Conversation
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllInfoFromFirestoreUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) {

    operator fun invoke(chatId: String): Flow<Response<Conversation>> {
        return chatRepository.getSelectedChatFromFirestore(chatId)
    }
}