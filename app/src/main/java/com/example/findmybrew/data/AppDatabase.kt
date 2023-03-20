package com.example.findmybrew.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SingleBrewery::class], version = 2)
abstract class AppDatabase: RoomDatabase() {
    abstract fun breweryDao(): BreweryDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "favoritedBreweries.db"
            ).fallbackToDestructiveMigration().build()

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }
        }
    }
}