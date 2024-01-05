package com.isaacdelosreyes.whathappen.conversation.domain.usecase

import com.isaacdelosreyes.whathappen.conversation.domain.repository.ConversationsRepository
import com.isaacdelosreyes.whathappen.core.data.model.Response
import com.isaacdelosreyes.whathappen.core.data.model.conversation.Conversation
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllConversationsUseCase @Inject constructor(
    private val repository: ConversationsRepository
) {

    operator fun invoke(): Flow<Response<List<Conversation>>> {
        return repository.getAllConversations()
    }
}