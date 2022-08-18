package com.example.stockprofitcalculator

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Stock :: class] , version = 1)
    abstract class AppDatabase: RoomDatabase() {
    abstract fun stockDao():StockDao

    companion object{

        @Volatile
        var INSTANCE: AppDatabase? = null
        fun getDataBase(context: Context): AppDatabase{
            val tempInstance = INSTANCE
            if (tempInstance!=null)
                return tempInstance

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "stock_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}