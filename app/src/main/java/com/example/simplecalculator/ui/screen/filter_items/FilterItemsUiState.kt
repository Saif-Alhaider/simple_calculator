package com.example.simplecalculator.ui.screen.filter_items

import com.example.simplecalculator.domain.model.ProductEntity
import com.example.simplecalculator.domain.model.ProductType

data class FilterItemsUiState(
    val filterButtons: List<FilterButton> = listOf(
        FilterButton(isActive = true, type = ProductType.CLOTHES),
        FilterButton(type = ProductType.ELECTRONICS),
        FilterButton(type = ProductType.SPORT),
        FilterButton(type = ProductType.FOOD),
    ),
    val products:List<ProductUiState> = emptyList(),
    val isProductsActive:Boolean = true
) {
    data class FilterButton(
        val isActive: Boolean = false,
        val type: ProductType
    )

    data class ProductUiState(
        val title: String = "",
        val price: String = "$0.0",
        val imageUrl: String = "",
        val type: ProductType = ProductType.CLOTHES
    )
}

fun List<ProductEntity>.mapToUi(): List<FilterItemsUiState.ProductUiState> {
    return map {
        FilterItemsUiState.ProductUiState(
            title = it.title,
            price = "$" + String.format("%.2f", it.price),
            imageUrl = it.imageUrl,
            type = it.type
        )
    }
}
