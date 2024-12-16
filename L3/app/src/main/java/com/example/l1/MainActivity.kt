package com.example.l1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.RepositoryImpl
import com.example.domain.Repository
import com.example.l1.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val repository: Repository = RepositoryImpl()
    private lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ProductAdapter(emptyList())
        binding.recyclerViewProducts.adapter = adapter
        binding.recyclerViewProducts.layoutManager = LinearLayoutManager(this)

        CoroutineScope(Dispatchers.IO).launch {
            val data = repository.getProducts()

            // Обновляем UI в главном потоке
            withContext(Dispatchers.Main) {
                adapter.updateData(data)
            }
        }
    }
}
