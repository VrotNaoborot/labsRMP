package com.example.data

import com.example.domain.Product

data class Bread(
    override val name: String = "Хлеб",
    override val count: Int
) : Product