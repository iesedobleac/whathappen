package com.isaacdelosreyes.whathappen.register.domain.usecase

import com.google.firebase.auth.FirebaseUser
import com.isaacdelosreyes.whathappen.core.data.model.Response
import com.isaacdelosreyes.whathappen.register.domain.repository.RegisterRepository
import javax.inject.Inject

class RegisterUserInFirebaseUseCase @Inject constructor(
    private val registerRepository: RegisterRepository
) {

    suspend operator fun invoke(email: String, password: String): Response<FirebaseUser?> {
        return registerRepository.registerNewUserToFirebaseAuthentication(email, password)
    }
}