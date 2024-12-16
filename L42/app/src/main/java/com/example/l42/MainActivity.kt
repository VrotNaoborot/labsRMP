package com.example.l42

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.RepositoryImpl
import com.example.data.db.preferences.ProductSharedPreferences
import com.example.domain.Repository
import com.example.l42.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ProductAdapter
    private lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        repository = RepositoryImpl(ProductSharedPreferences(applicationContext))
        adapter = ProductAdapter(emptyList())
        binding.recyclerViewProducts.adapter = adapter
        binding.recyclerViewProducts.layoutManager = LinearLayoutManager(this)

        fetchProductsAndUpdateUI()
    }

    private fun fetchProductsAndUpdateUI() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                Log.d("MyLog", "Загрузка данных")
                val products = repository.getProducts()
                Log.d("MyLog", products.toString())
                withContext(Dispatchers.Main) {
                    adapter.updateData(products)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity, "Ошибка загрузки данных", Toast.LENGTH_SHORT).show()
                    Log.e("MyLog", "Ошибка", e)
                }
            }
        }
    }
}