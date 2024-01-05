package com.isaacdelosreyes.whathappen.conversation.presentation

import com.isaacdelosreyes.whathappen.core.data.model.conversation.Conversation
import com.isaacdelosreyes.whathappen.core.data.model.user.User

data class ConversationsState(
    val conversations: List<Conversation> = emptyList(),
    val users: List<User> = emptyList(),
)
