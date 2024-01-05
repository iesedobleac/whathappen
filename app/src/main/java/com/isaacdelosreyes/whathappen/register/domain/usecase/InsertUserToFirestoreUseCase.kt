package com.isaacdelosreyes.whathappen.register.domain.usecase

import com.isaacdelosreyes.whathappen.core.data.model.user.User
import com.isaacdelosreyes.whathappen.register.domain.repository.RegisterRepository
import javax.inject.Inject

class InsertUserToFirestoreUseCase @Inject constructor(
    private val repository: RegisterRepository
) {

    suspend operator fun invoke(user: User) {
        repository.insertUserToFirestoreDatabase(user)
    }
}