package com.example.stockprofitcalculator

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Stock :: class] , version = 3)
    abstract class AppDatabase: RoomDatabase() {
    abstract fun stockDao():StockDao

    companion object{

        val migration_1_2 = object : Migration(1,2) {
            override fun migrate(database: SupportSQLiteDatabase) {

                database.execSQL("ALTER TABLE stock_table ADD new_stock_qty INTEGER default 0 NOT NULL")
                database.execSQL("ALTER TABLE stock_table ADD new_avg_price DOUBLE default 0 NOT NULL")
                database.execSQL("ALTER TABLE stock_table ADD new_sell_price DOUBLE default 0 NOT NULL")
                database.execSQL("ALTER TABLE stock_table ADD profit DOUBLE default 0 NOT NULL")

            }
        }

        val migration_2_3 = object : Migration(2,3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE stock_table ADD profit DOUBLE default 0 NOT NULL")
            }
        }

        val migration_3_4 = object : Migration(3,4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE stock_table ADD invested DOUBLE default 0 NOT NULL")
            }
        }
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
                ).addMigrations(migration_1_2).addMigrations(migration_2_3).addMigrations(
                    migration_3_4).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}