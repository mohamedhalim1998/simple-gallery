package com.mohamed.halim.essa.simplegallery.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ImagesDao {

    @Query("SELECT * FROM images ORDER BY dateModified DESC")
    fun getAllImages(): Flow<List<Image>>

    @Insert
    suspend fun insertImages(images: List<Image>)

    @Query("DELETE FROM images")
    suspend fun deleteAllImages()

    @Delete
    suspend fun deleteImage(image: Image)

}