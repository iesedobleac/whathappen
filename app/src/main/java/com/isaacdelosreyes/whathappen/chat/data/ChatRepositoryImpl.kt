package com.isaacdelosreyes.whathappen.chat.data

import com.google.firebase.firestore.FirebaseFirestore
import com.isaacdelosreyes.whathappen.chat.domain.repository.ChatRepository
import com.isaacdelosreyes.whathappen.core.data.model.Response
import com.isaacdelosreyes.whathappen.core.data.model.conversation.Conversation
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.conflate
import java.util.UUID
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : ChatRepository {

    companion object {
        private const val COLLECTION_PATH = "conversations"
    }

    override fun getSelectedChatFromFirestore(chatId: String) = callbackFlow {

        val snapshotListener = firestore.collection(COLLECTION_PATH).document(chatId)
            .addSnapshotListener { value, error ->
                value?.let { document ->

                    val conversation = document.toObject(Conversation::class.java)
                        ?: Conversation(
                            id = UUID.randomUUID().toString(),
                            users = emptyList(),
                            messages = emptyList()
                        )

                    trySend(
                        Response.Success(
                            conversation
                        )
                    )

                } ?: run {
                    trySend(Response.Failure(error))
                }
            }

        awaitClose {
            println("El canal se ha cerrado")
            snapshotListener.remove()
        }
    }.conflate()
}