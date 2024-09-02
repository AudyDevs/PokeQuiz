package com.example.pokequiz.di

import com.example.pokequiz.data.FireStoreRepositoryImpl
import com.example.pokequiz.domain.FireStoreRepository
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CloudModule {

    @Singleton
    @Provides
    fun provideDataBaseFireStore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    fun provideFireStoreRepository(
        firebaseFireStore: FirebaseFirestore
    ): FireStoreRepository =
        FireStoreRepositoryImpl(firebaseFireStore)
}