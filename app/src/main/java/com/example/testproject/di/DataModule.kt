package com.example.testproject.di

import com.example.testproject.data.datasource.local.LocalDataSource
import com.example.testproject.data.db.dao.RecipesDao
import com.example.testproject.data.db.dao.RecipesDetailDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideChannelsLocalDataSource(recipesDao: RecipesDao, recipesDetailDao: RecipesDetailDao): LocalDataSource {
        return LocalDataSource(recipesDao, recipesDetailDao)
    }
}