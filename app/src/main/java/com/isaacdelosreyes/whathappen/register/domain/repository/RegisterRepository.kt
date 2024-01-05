package com.isaacdelosreyes.whathappen.register.domain.repository

import com.google.firebase.auth.FirebaseUser
import com.isaacdelosreyes.whathappen.core.data.model.Response
import com.isaacdelosreyes.whathappen.core.data.model.user.User

interface RegisterRepository {

    suspend fun registerNewUserToFirebaseAuthentication(
        email: String,
        password: String
    ): Response<FirebaseUser?>

    suspend fun loginWithUserCredentials(email: String, password: String): Response<Boolean>

    suspend fun insertUserToFirestoreDatabase(user: User)
}