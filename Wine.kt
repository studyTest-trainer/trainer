package com.example.myapplication
data class Wine(
    val id: Int,
    val name: String,
    val price: Int,
    val imageRes: Int,
    var quantity: Int = 1
)