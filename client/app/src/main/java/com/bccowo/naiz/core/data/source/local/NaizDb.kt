package com.bccowo.naiz.core.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bccowo.naiz.core.data.source.local.entities.CandiEntity
import com.bccowo.naiz.core.data.source.local.entities.ReliefTypeConverter

@Database(entities = [CandiEntity::class], version = 1, exportSchema = false)
@TypeConverters(ReliefTypeConverter::class)
abstract class NaizDb : RoomDatabase() {
    abstract fun naizDao() : NaizDao
}