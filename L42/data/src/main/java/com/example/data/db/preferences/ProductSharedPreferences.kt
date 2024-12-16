package com.example.data.db.preferences

import android.content.Context
import android.content.SharedPreferences

class ProductSharedPreferences(context: Context) {
    private val preferences: SharedPreferences = context.getSharedPreferences("Products", Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = preferences.edit()

    private val DATE_KEY = "data"

    fun saveDate(date: String) {
        editor.putString(DATE_KEY, date)
        editor.apply()
    }

    fun getLastDate(): String {
        return preferences.getString(DATE_KEY, "") ?: ""
    }
}