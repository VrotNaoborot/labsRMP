package com.example.data.db

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.db.dao.ProductDao
import com.example.data.db.entities.ProductEntity

@Database(entities = [ProductEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao

    companion object {
        lateinit var db: AppDatabase

        fun initDb(applicationContext: Context) {
            try {
                db = Room
                    .databaseBuilder(applicationContext, AppDatabase::class.java, "product_db")
                    .fallbackToDestructiveMigration() // Добавьте этот вызов, чтобы сбросить старую базу данных при изменении версии
                    .build()
            } catch (e: Exception) {
                Log.d("MyLog", "initDb", e)
            }
        }
    }
}
