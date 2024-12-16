package com.example.data

import android.content.Context
import android.util.Log
import com.example.data.api.model.ProductApi
import com.example.data.db.AppDatabase
import com.example.data.db.entities.ProductEntity
import com.example.data.db.preferences.ProductSharedPreferences
import com.example.domain.Product
import com.example.domain.Repository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class RepositoryImpl(
    private val productSharedPreferences: ProductSharedPreferences
) : Repository {

    private val db = AppDatabase.db

    override suspend fun getProducts(): List<Product> {
        val savedDate = productSharedPreferences.getLastDate()
        Log.d("MyLog", "Сохраненная дата: $savedDate")
        val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        Log.d("MyLog", "Текущая дата: $currentDate")

        if (savedDate == currentDate) {
            Log.d("MyLog", "Дата найдена. Загрузка данных из БД")
            return db.productDao().getAll()
        } else {
            Log.d("MyLog", "Дата не найдена. Загрузка данных из БД")
            val productsApi = ProductClient.client.fetchProductsList()
            Log.d("MyLog", "Получены данные с API: ${productsApi.size} продуктов")

            val productsEntity = productsApi.map { it.toEntity() }
            db.productDao().insertAll(productsEntity)
            productSharedPreferences.saveDate(currentDate)
            return productsApi
        }
    }

    private fun ProductApi.toEntity(): ProductEntity {
        return ProductEntity(
            id = this.id,
            title = this.title,
            price = this.price
        )
    }
}
