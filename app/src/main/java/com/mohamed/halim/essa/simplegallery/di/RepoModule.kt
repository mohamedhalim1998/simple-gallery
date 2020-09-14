package com.mohamed.halim.essa.simplegallery.di

import android.content.Context
import com.mohamed.halim.essa.simplegallery.data.ImagesDao
import com.mohamed.halim.essa.simplegallery.data.Repo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RepoModule {
    @Singleton
    @Provides
    fun provideImagesRepo(imagesDao: ImagesDao, @ApplicationContext context: Context): Repo {
        return Repo(imagesDao, context)
    }
}