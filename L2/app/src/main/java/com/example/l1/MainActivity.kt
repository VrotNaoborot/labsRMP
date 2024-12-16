package com.example.l1

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.RepositoryImpl
import com.example.domain.Repository
import com.example.l1.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val repository: Repository = RepositoryImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = repository.getData()
        val adapter = ProductAdapter(data)

        binding.recyclerViewProducts.adapter = adapter
        binding.recyclerViewProducts.layoutManager = LinearLayoutManager(this)

    }

}