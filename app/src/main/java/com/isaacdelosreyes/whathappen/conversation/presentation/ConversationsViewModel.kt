package com.isaacdelosreyes.whathappen.conversation.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.isaacdelosreyes.whathappen.conversation.domain.usecase.GetAllConversationsUseCase
import com.isaacdelosreyes.whathappen.core.data.model.Response
import com.isaacdelosreyes.whathappen.core.data.model.message.Message
import com.isaacdelosreyes.whathappen.core.data.model.user.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ConversationsViewModel @Inject constructor(
    private val getAllConversationsUseCase: GetAllConversationsUseCase
) : ViewModel() {

    var state by mutableStateOf(ConversationsState())
        private set

    init {
        getAllConversationsFromFirestore()
    }

    private fun getAllConversationsFromFirestore() {
        viewModelScope.launch {
            getAllConversationsUseCase().collectLatest {
                when (it) {
                    is Response.Failure -> {}
                    Response.Loading -> {}
                    is Response.Success -> {
                        println(it)
                        println()
                        state = state.copy(
                            conversations = it.data
                        )
                    }
                }
            }
        }
    }
}