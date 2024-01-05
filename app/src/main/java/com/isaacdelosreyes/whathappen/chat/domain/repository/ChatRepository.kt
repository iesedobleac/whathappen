package com.isaacdelosreyes.whathappen.chat.domain.repository

import com.isaacdelosreyes.whathappen.core.data.model.Response
import com.isaacdelosreyes.whathappen.core.data.model.conversation.Conversation
import kotlinx.coroutines.flow.Flow

interface ChatRepository {

    fun getSelectedChatFromFirestore(chatId: String): Flow<Response<Conversation>>
}