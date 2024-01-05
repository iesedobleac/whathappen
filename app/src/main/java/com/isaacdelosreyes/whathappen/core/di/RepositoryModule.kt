package com.isaacdelosreyes.whathappen.core.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.isaacdelosreyes.whathappen.core.data.repository.CommonRepositoryImpl
import com.isaacdelosreyes.whathappen.core.domain.repository.CommonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun commonRepositoryProvider(
        firebaseAuth: FirebaseAuth,
        firestore: FirebaseFirestore
    ): CommonRepository =
        CommonRepositoryImpl(firebaseAuth, firestore)
}