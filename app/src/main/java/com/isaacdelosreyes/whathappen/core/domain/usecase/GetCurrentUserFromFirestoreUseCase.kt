package com.isaacdelosreyes.whathappen.core.domain.usecase

import com.isaacdelosreyes.whathappen.core.domain.repository.CommonRepository
import javax.inject.Inject

class GetCurrentUserFromFirestoreUseCase @Inject constructor(
    private val commonRepository: CommonRepository
) {

    suspend operator fun invoke(): Boolean {
        return commonRepository.getCurrentUserFromFirestore()
    }
}