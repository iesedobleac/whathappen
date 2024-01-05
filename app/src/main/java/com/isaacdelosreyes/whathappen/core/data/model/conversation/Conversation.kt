package com.isaacdelosreyes.whathappen.core.data.model.conversation

import com.isaacdelosreyes.whathappen.core.data.model.message.Message
import com.isaacdelosreyes.whathappen.core.data.model.user.User

data class Conversation(
    val id: String = "",
    val users: List<User> = emptyList(),
    val messages: List<Message> = emptyList(),
)
