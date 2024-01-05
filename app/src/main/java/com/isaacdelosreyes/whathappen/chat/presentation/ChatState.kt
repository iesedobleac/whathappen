package com.isaacdelosreyes.whathappen.chat.presentation

import com.isaacdelosreyes.whathappen.core.data.model.message.Message
import com.isaacdelosreyes.whathappen.core.data.model.user.User

data class ChatState(
    val message: String = "",
    val messages: List<Message> = emptyList(),
    val users: List<User> = emptyList()
)
