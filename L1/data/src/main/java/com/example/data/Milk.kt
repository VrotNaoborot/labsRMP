package com.example.data

import com.example.domain.Product

data class Milk(
    override val name: String = "Milk",
    override val count: Int
) : Product