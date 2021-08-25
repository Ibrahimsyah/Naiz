package com.bccowo.naiz.core.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bccowo.naiz.core.data.source.local.entities.CandiEntity

@Database(entities = [CandiEntity::class], version = 1, exportSchema = false)
abstract class NaizDb : RoomDatabase() {
    abstract fun naizDao() : NaizDao
}