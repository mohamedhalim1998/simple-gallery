package com.mohamed.halim.essa.simplegallery.di

import android.content.Context
import androidx.room.Room
import com.mohamed.halim.essa.simplegallery.data.ImagesDao
import com.mohamed.halim.essa.simplegallery.data.ImagesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RoomDatabaseModule {
    @Singleton
    @Provides
    fun provideImagesDatabase(@ApplicationContext context: Context): ImagesDatabase {
        return Room.databaseBuilder(
            context,
            ImagesDatabase::class.java,
            "CardsDB"
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideImagesDao(database: ImagesDatabase): ImagesDao {
        return database.imagesDao
    }
}