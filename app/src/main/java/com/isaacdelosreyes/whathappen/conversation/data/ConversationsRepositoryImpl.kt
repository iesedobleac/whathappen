package com.isaacdelosreyes.whathappen.conversation.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.isaacdelosreyes.whathappen.conversation.domain.repository.ConversationsRepository
import com.isaacdelosreyes.whathappen.core.data.model.Response
import com.isaacdelosreyes.whathappen.core.data.model.conversation.Conversation
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class ConversationsRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth
) : ConversationsRepository {

    override fun getAllConversations() = callbackFlow {
        val conversations = firestore.collection("conversations")
            .addSnapshotListener { value, error ->
                val listOfConversation = mutableListOf<Conversation>()
                value?.documents?.let {
                    it.forEach { document ->
                        var conversation = document.toObject(Conversation::class.java)
                            ?: Conversation()
                        conversation = conversation.copy(
                            id = document.id
                        )
                        conversation.takeIf {
                            conversation.users.any { user ->
                                user.email == firebaseAuth.currentUser?.email
                            }
                        }?.also {
                            listOfConversation.add(conversation)
                        }
                        println(listOfConversation)
                        println()
                        trySend(Response.Success(listOfConversation.toList()))
                    }
                } ?: kotlin.run {
                    trySend(Response.Failure(error))
                }
            }

        awaitClose {
            conversations.remove()
        }
    }
}