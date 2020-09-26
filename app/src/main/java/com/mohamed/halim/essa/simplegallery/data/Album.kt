package com.mohamed.halim.essa.simplegallery.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "album")
data class Album(
    @PrimaryKey
    val id: Long,
    val albumName: String,
    var imagesCount: Int,
    var lastImageId: Long = -1
)