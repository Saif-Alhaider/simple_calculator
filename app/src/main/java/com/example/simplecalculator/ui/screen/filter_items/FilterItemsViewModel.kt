package com.example.simplecalculator.ui.screen.filter_items

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplecalculator.domain.data.EcommerceDataSource
import com.example.simplecalculator.domain.model.ProductEntity
import com.example.simplecalculator.domain.model.ProductType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilterItemsViewModel @Inject constructor(
) : ViewModel() {
    val products = listOf(
        ProductEntity(
            title = "Men Allover Print Polo Shirt",
            price = 11.49,
            imageUrl = "https://img.ltwebstatic.com/images3_pi/2023/06/02/16856973920df892a1d61d595df068028140a4ceda_thumbnail_600x.jpg",
            type = ProductType.CLOTHES
        ),
        ProductEntity(
            title = "Men Seagull & Coconut Tree Graphic Contrast Trim Polo Shirt",
            price = 11.99,
            imageUrl = "https://img.ltwebstatic.com/images3_pi/2023/06/19/16871398659d26131e043e9a24152a5d9ea7e94ce3_thumbnail_600x.jpg",
            type = ProductType.CLOTHES
        ),
        ProductEntity(
            title = "Manfinity Chillmode Men Floral Print Shirt",
            price = 12.99,
            imageUrl = "https://img.ltwebstatic.com/images3_pi/2023/07/17/16895819614499299fcf6e0e6b60f7da1de7d9d910_thumbnail_600x.jpg",
            type = ProductType.CLOTHES
        ),
        ProductEntity(
            title = "Manfinity RSRT Men Ombre Flamingo Print Polo Shirt",
            price = 12.99,
            imageUrl = "https://img.ltwebstatic.com/images3_pi/2023/05/26/168507780455d9df7d1fd6718de8c7ac5c78ba6092_thumbnail_600x.jpg",
            type = ProductType.CLOTHES
        ),
        ProductEntity(
            title = "Manfinity Mode Men Two Tone Letter Graphic Polo Shirt",
            price = 12.99,
            imageUrl = "https://img.ltwebstatic.com/images3_pi/2023/05/18/16843821317684eeb00358d733ed19b9b4539d794b_thumbnail_600x.jpg",
            type = ProductType.CLOTHES
        ),
        ProductEntity(
            title = "Huawei Matebook D16 i7",
            price = 985.5,
            imageUrl = "https://www.elryan.com/img/600/620/resize/catalog/product/2/4/2480e4e8287a443a38c641a64c44402a83c2de32_s200770465_1.jpg",
            type = ProductType.ELECTRONICS
        ),
        ProductEntity(
            title = "Iphone 14 pro max",
            price = 985.5,
            imageUrl = "https://assets.swappie.com/cdn-cgi/image/width=600,height=600,fit=contain,format=auto/swappie-iphone-14-pro-max-gold-back.png?v=35",
            type = ProductType.ELECTRONICS
        ),

        )

    private val _state = MutableStateFlow(FilterItemsUiState())
    val state = _state.asStateFlow()
    init {
        getProducts()
    }
    fun onFilterButtonClick(type: ProductType) {
        viewModelScope.launch {
            val newFilterButtons = _state.value.filterButtons.map {
                it.copy(isActive = type == it.type)
            }
            _state.update { it.copy(filterButtons = newFilterButtons) }
            _state.update { it.copy(isProductsActive = false) }
            delay(500)
            _state.update { it.copy(products = products.mapToUi()
                .filter { product -> product.type == type }) }

            _state.update { it.copy(isProductsActive = true) }
        }
    }

    private fun getProducts() {
        _state.update {
            it.copy(
                products = products.mapToUi()
                    .filter { product -> product.type == ProductType.CLOTHES })
        }
    }
}