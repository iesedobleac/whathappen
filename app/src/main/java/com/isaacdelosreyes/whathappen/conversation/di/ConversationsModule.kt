package com.isaacdelosreyes.whathappen.conversation.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.isaacdelosreyes.whathappen.conversation.data.ConversationsRepositoryImpl
import com.isaacdelosreyes.whathappen.conversation.domain.repository.ConversationsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ConversationsModule {

    @Singleton
    @Provides
    fun conversationsRepositoryProvider(
        firestore: FirebaseFirestore,
        firebaseAuth: FirebaseAuth
    ): ConversationsRepository =
        ConversationsRepositoryImpl(
            firestore = firestore,
            firebaseAuth = firebaseAuth
        )
}