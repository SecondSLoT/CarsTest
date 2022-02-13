package com.secondslot.carstest.di

import android.content.Context
import androidx.room.Room
import com.secondslot.carstest.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideAppDatabase(context: Context) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        AppDatabase.DATABASE_NAME
    )
        .createFromAsset("database/cars.db")
        .build()
}
