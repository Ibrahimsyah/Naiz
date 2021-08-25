package com.bccowo.naiz.core.data.source.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "candi_bookmarks")
data class CandiEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val address: String,
    val image: String,
    val rating: Double,
    val ratingCount: Int,
    val longitude: Double,
    val latitude: Double,
    val reliefs: List<Relief>,
    val totalRelief: Int,
)

data class Relief(
    val id: Int,
    val name: String,
    val description: String,
    val image: String,
    val type: String
)