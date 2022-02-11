package com.startup.core.localdatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.startup.core.model.LocalModel
import com.startup.core.service.NewssterService

@Database(entities = [LocalModel::class], version = 0, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getNews(): NewssterService
}