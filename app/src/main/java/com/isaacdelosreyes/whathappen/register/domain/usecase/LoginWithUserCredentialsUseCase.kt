package com.isaacdelosreyes.whathappen.register.domain.usecase

import com.isaacdelosreyes.whathappen.core.data.model.Response
import com.isaacdelosreyes.whathappen.register.domain.repository.RegisterRepository
import javax.inject.Inject

class LoginWithUserCredentialsUseCase @Inject constructor(
    private val registerRepository: RegisterRepository
) {

    suspend operator fun invoke(email: String, password: String): Response<Boolean> {
        return registerRepository.loginWithUserCredentials(email, password)
    }
}