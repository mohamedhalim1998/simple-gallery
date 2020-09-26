package com.mohamed.halim.essa.simplegallery.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "images"
)
data class Image(
    @PrimaryKey
    var id: Long,
    var displayName: String,
    var albumId: Long,
    var albumName: String,
    var dateModified: Long

)