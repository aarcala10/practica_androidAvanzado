package com.example.epic_image.repository.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.epic_image.repository.model.DatesResponseItem

@Database(entities = [DatesResponseItem::class], version = 1, exportSchema = false)
abstract class EpicRoomDatabase : RoomDatabase() {

    abstract fun epicDao(): EpicDao

    companion object {

        private var instance: EpicRoomDatabase? = null

        fun getInstance(context: Context): EpicRoomDatabase {
            if (instance == null) {

                synchronized(EpicRoomDatabase::class) {
                    instance = Room.databaseBuilder(context.applicationContext, EpicRoomDatabase::class.java, "date_db")
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return instance!!

        }
    }
}