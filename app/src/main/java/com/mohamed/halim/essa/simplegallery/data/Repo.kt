package com.mohamed.halim.essa.simplegallery.data

import android.content.Context
import android.provider.MediaStore
import kotlinx.coroutines.flow.Flow

class Repo constructor(private val database: ImagesDao, private val context: Context) {

    suspend fun updateImages(): Int {
        val cursor = context.applicationContext.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            null, null, null, null
        )
        val images = mutableListOf<Image>()
        if (cursor != null && cursor.moveToFirst()) {
            do {
                val id = cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media._ID))
                val name =
                    cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME))
                val albumId =
                    cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media.BUCKET_ID))
                val album =
                    cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.BUCKET_DISPLAY_NAME))
                val dateModified =
                    cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media.DATE_MODIFIED))
                images.add(Image(id, name, albumId, album, dateModified))
            } while (cursor.moveToNext())
            cursor.close()
        }
        database.deleteAllImages()
        database.insertImages(images)
        return images.size
    }

    fun getAllImages(): Flow<List<Image>> {
        return database.getAllImages()
    }
}