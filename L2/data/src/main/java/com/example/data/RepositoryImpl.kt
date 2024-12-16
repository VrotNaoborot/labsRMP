package com.example.data

import com.example.domain.Product
import com.example.domain.Repository

class RepositoryImpl: Repository {
    override fun getData(): List<Product> {
        return listOf(
            Milk(count = 5),
            Bread(count = 10)
        )
    }
}