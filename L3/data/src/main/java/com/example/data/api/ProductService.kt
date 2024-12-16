package com.example.data.api

import com.example.data.api.model.ProductApi
import retrofit2.http.GET

interface ProductService {
    @GET("/products")
    suspend fun fetchProductsList(): List<ProductApi>
}