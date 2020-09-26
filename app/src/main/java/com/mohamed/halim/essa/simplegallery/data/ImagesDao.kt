package com.mohamed.halim.essa.simplegallery.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ImagesDao {

    @Query("SELECT * FROM images ORDER BY dateModified DESC")
    fun getAllImages(): Flow<List<Image>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImages(images: List<Image>)

    @Query("DELETE FROM images")
    suspend fun deleteAllImages()

    @Delete
    suspend fun deleteImage(image: Image)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlbum(album: Album)

    @Query("DELETE FROM album")
    suspend fun deleteAllAlbums()

    @Query("SELECT * FROM album ORDER BY albumName")
    fun getAlbums(): Flow<List<Album>>

    @Query("SELECT * FROM images WHERE albumId = :albumId ORDER BY dateModified DESC LIMIT 1")
    suspend fun getLastImageFromAlbum(albumId: Long): Image

    @Query("SELECT * FROM images WHERE albumId = :albumId ORDER BY dateModified DESC")
    fun getImagesFromAlbum(albumId: Long): Flow<List<Image>>
}