package com.isaacdelosreyes.whathappen.register.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.isaacdelosreyes.whathappen.core.data.model.Response
import com.isaacdelosreyes.whathappen.core.data.model.user.User
import com.isaacdelosreyes.whathappen.register.domain.repository.RegisterRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : RegisterRepository {

    override suspend fun registerNewUserToFirebaseAuthentication(
        email: String,
        password: String
    ): Response<FirebaseUser?> {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(
                email,
                password
            ).await()

            Response.Success(result.user)

        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override suspend fun loginWithUserCredentials(
        email: String,
        password: String
    ): Response<Boolean> {
        return try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Response.Success(true)

        } catch (e: Exception) {
            Response.Failure(e)
        }


    }

    override suspend fun insertUserToFirestoreDatabase(user: User) {
        val hashMap = hashMapOf(
            "name" to user.name,
            "email" to user.email
        )

        firestore.collection("users")
            .document(hashMap["email"].toString())
            .set(hashMap)
    }
}