package com.example.l42

import android.app.Application
import android.util.Log
import com.example.data.db.AppDatabase

class ProductApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d("MyLog", "Инициализация базы данных")
        AppDatabase.initDb(applicationContext)
        Log.d("MyLog", "База данных инициализирована")
    }
}