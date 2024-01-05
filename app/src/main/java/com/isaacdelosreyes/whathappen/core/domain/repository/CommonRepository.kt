package com.isaacdelosreyes.whathappen.core.domain.repository

interface CommonRepository {

    suspend fun getCurrentUserFromFirestore(): Boolean
}