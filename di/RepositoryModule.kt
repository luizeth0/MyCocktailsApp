package com.example.mycocktailsapp.di

import com.example.mycocktailsapp.rest.CocktailsRepository
import com.example.mycocktailsapp.rest.CocktailsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun providesMusicRepositoryImpl(
        cocktailsRepositoryImpl: CocktailsRepositoryImpl
    ): CocktailsRepository
}