package com.example.domain

interface Repository {
    suspend fun getProducts(): List<Product>
}