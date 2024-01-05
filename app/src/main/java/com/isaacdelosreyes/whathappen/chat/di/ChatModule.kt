package com.isaacdelosreyes.whathappen.chat.di

import com.google.firebase.firestore.FirebaseFirestore
import com.isaacdelosreyes.whathappen.chat.data.ChatRepositoryImpl
import com.isaacdelosreyes.whathappen.chat.domain.repository.ChatRepository
import com.isaacdelosreyes.whathappen.register.domain.repository.RegisterRepository
import com.isaacdelosreyes.whathappen.register.domain.usecase.RegisterUserInFirebaseUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ChatModule {

    @Singleton
    @Provides
    fun getAllInfoFromFirestoreUseCaseProvider(chatRepository: RegisterRepository) =
        RegisterUserInFirebaseUseCase(chatRepository)

    @Singleton
    @Provides
    fun chatRepositoryProvider(firestore: FirebaseFirestore): ChatRepository =
        ChatRepositoryImpl(firestore)
}