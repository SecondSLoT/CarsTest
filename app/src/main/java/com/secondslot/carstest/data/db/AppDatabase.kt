package com.secondslot.carstest.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.secondslot.carstest.data.db.dao.CarDao
import com.secondslot.carstest.data.db.model.CarEntity

@Database(
    entities = [CarEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract val carDao: CarDao

    companion object {
        const val DATABASE_NAME = "app_database"
    }
}
