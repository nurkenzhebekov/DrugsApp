package com.example.drugsapp.data

import android.content.Context
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.drugsapp.model.Drug

@Database(entities = [Drug::class], version = 1, exportSchema = false)
abstract class DrugsDatabase : RoomDatabase() {
    abstract fun drugsDao(): DrugsDao

    companion object {
        @Volatile
        private var INSTANCE: DrugsDatabase? = null

        fun getDatabase(context: Context): DrugsDatabase {
            return  INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DrugsDatabase::class.java,
                    "drugs_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}