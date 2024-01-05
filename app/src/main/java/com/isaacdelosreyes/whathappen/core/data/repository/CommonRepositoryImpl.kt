package com.isaacdelosreyes.whathappen.core.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.isaacdelosreyes.whathappen.core.domain.repository.CommonRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CommonRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
) : CommonRepository {

    override suspend fun getCurrentUserFromFirestore(): Boolean {

        return if (firebaseAuth.currentUser != null) {

            val email = firebaseAuth.currentUser?.email.toString()
            val user = firestore.collection("users")
                .document(email)
                .get().await()

            user.get("email") == email

        } else {
            false
        }
    }
}