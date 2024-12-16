package com.example.domain

interface Repository {
    fun getData(): List<Product>
}