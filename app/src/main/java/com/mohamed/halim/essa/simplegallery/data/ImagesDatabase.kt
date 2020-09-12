package com.mohamed.halim.essa.simplegallery.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Image::class], exportSchema = false, version = 1)
abstract class ImagesDatabase : RoomDatabase() {
    abstract val imagesDao: ImagesDao
}