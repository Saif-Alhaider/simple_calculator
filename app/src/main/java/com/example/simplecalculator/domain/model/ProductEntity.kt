package com.example.simplecalculator.domain.model

data class ProductEntity(
    val title:String,
    val price:Double,
    val imageUrl:String,
    val type:ProductType
)
