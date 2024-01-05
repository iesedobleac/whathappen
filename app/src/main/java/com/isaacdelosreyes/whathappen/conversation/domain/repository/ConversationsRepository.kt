package com.isaacdelosreyes.whathappen.conversation.domain.repository

import com.isaacdelosreyes.whathappen.core.data.model.Response
import com.isaacdelosreyes.whathappen.core.data.model.conversation.Conversation
import kotlinx.coroutines.flow.Flow

interface ConversationsRepository {

    fun getAllConversations(): Flow<Response<List<Conversation>>>
}