package com.isaacdelosreyes.whathappen.register.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.isaacdelosreyes.whathappen.register.data.RegisterRepositoryImpl
import com.isaacdelosreyes.whathappen.register.domain.repository.RegisterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RegisterModule {

    @Singleton
    @Provides
    fun registerRepositoryProvider(
        firebaseAuth: FirebaseAuth,
        firestore: FirebaseFirestore
    ): RegisterRepository =
        RegisterRepositoryImpl(firebaseAuth, firestore)
}