package com.example.testproject.di

import android.content.Context
import androidx.room.Room
import com.example.testproject.data.db.ChatDatabase
import com.example.testproject.data.db.dao.RecipesDao
import com.example.testproject.data.db.dao.RecipesDetailDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDb(context: Context): ChatDatabase =  Room.databaseBuilder(
        context,
        ChatDatabase::class.java,
        ChatDatabase.DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideStreamsDao(db: ChatDatabase): RecipesDao {
        return db.recipesDao()
    }

    @Singleton
    @Provides
    fun provideMessagesDao(db: ChatDatabase): RecipesDetailDao {
        return db.recipesDetailDao()
    }

}