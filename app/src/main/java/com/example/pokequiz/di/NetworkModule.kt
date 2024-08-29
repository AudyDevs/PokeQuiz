package com.example.pokequiz.di

import com.example.pokequiz.data.PokemonRepositoryImpl
import com.example.pokequiz.data.network.PokemonApiService
import com.example.pokequiz.data.room.dao.PokemonDao
import com.example.pokequiz.core.Constants.BASE_URL
import com.example.pokequiz.domain.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun providePokemonApiService(retrofit: Retrofit): PokemonApiService {
        return retrofit.create(PokemonApiService::class.java)
    }

    @Provides
    fun providePokemonRepository(
        pokemonApiService: PokemonApiService,
        pokemonDao: PokemonDao
    ): PokemonRepository {
        return PokemonRepositoryImpl(pokemonApiService, pokemonDao)
    }
}