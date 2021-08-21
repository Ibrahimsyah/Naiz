package com.bccowo.naiz.core.data.source.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "candi_bookmarks")
data class CandiEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val address: String,
    val image: String,
    val rating: Double
)