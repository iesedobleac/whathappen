package com.isaacdelosreyes.whathappen.chat.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.isaacdelosreyes.whathappen.chat.domain.usecase.GetAllInfoFromFirestoreUseCase
import com.isaacdelosreyes.whathappen.core.data.model.Response
import com.isaacdelosreyes.whathappen.core.data.model.message.Message
import com.isaacdelosreyes.whathappen.core.data.model.user.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getAllInfoFromFirestoreUseCase: GetAllInfoFromFirestoreUseCase,
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth,
) : ViewModel() {

    var state by mutableStateOf(ChatState())
        private set

    private val chatId: String? = savedStateHandle["chatId"]

    init {
        getChatMessages()
    }

    fun onChangeMessageValue(message: String) {
        state = state.copy(
            message = message
        )
    }

    private fun getChatMessages() {
        viewModelScope.launch {
            getAllInfoFromFirestoreUseCase(chatId ?: "").collectLatest {
                when (it) {
                    is Response.Success -> {
                        state = state.copy(
                            messages = it.data.messages,
                            users = it.data.users
                        )
                    }

                    is Response.Failure -> {

                    }

                    Response.Loading -> {

                    }
                }
            }
        }
    }

    fun isOwnMessage(message: Message): Boolean {
        return auth.currentUser?.email == message.sendBy
    }

    fun updateMessageList() {
        val message = Message(
            message = state.message,
            date = System.currentTimeMillis(),
            sendBy = auth.currentUser?.email ?: ""
        )
        val currentMessages: MutableList<Message> = state.messages.toMutableList()
        currentMessages.add(message)
        state = state.copy(
            messages = currentMessages
        )
        state = state.copy(
            message = ""
        )

        val hashMap = mapOf(
            "messages" to state.messages
        )

        firestore.collection("conversations")
            .document(chatId ?: "").update(hashMap)
    }

    fun getReceivingUserChat(): User? {
        return state.users.firstOrNull {
            auth.currentUser?.email != it.email
        }
    }
}