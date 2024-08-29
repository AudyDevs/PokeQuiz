package com.example.pokequiz.di

import android.content.Context
import androidx.room.Room
import com.example.pokequiz.data.room.DataBase
import com.example.pokequiz.core.Constants.ROOM_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, DataBase::class.java, ROOM_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun providePokemonDao(db: DataBase) = db.loadPokemonDao()
}