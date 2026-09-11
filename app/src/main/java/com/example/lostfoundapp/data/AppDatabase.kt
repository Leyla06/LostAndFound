package com.example.lostfoundapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.lostfoundapp.model.LostItem

@Database(
    entities = [LostItem::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(ItemTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun lostItemDao(): LostItemDao
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "lost_found_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}