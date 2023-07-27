package com.example.simplecalculator.ui.screen.filter_items

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.simplecalculator.R
import com.example.simplecalculator.domain.model.ProductType

@Composable
fun FilterItemsScreen(viewModel: FilterItemsViewModel = hiltViewModel()) {
    val state = viewModel.state.collectAsState().value
    FilterItemsContent(onClickFilterButton = { viewModel.onFilterButtonClick(it) }, state = state)
}

@Composable
fun FilterItemsContent(
    onClickFilterButton: (ProductType) -> Unit,
    state: FilterItemsUiState
) {
    Column() {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
            modifier = Modifier.semantics { contentDescription = "lazyRow" }
        ) {
            items(state.filterButtons) { product ->
                Button(
                    onClick = { if (!product.isActive) onClickFilterButton(product.type) },
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(
                        width = 1.dp,
                        color = Color((0xFFC4C4C4))
                    ).takeIf { !product.isActive },
                    colors = ButtonDefaults.buttonColors(if (product.isActive) Color.Black else Color.White),
                    modifier = Modifier.semantics {
                        testTag = product.type.name
                        contentDescription = "isActive:${product.isActive}"
                    }
                ) {
                    Text(
                        text = product.type.name,
                        color = if (product.isActive) Color.White.copy(alpha = .87f)
                        else Color.Black.copy(
                            alpha = .87f
                        )
                    )
                }
            }
        }
        AnimatedVisibility(visible = state.isProductsActive,
            enter = slideIn { IntOffset(0, it.height / 2) } + fadeIn(),
            exit = slideOut(tween(3000)) { IntOffset(0, it.height / 1) } + fadeOut()
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                Modifier
                    .fillMaxSize()
                    .background(Color(0xFFFAFAFA)),
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.products) { product ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .shadow(elevation = 4.dp, spotColor = Color.Black.copy(alpha = .38f))
                            .semantics { contentDescription = "lazyItem" }
                    ) {
                        Column(
                            Modifier
                                .fillMaxSize()
                                .padding(vertical = 16.dp, horizontal = 4.dp)
                        ) {
                            AsyncImage(
                                placeholder = painterResource(id = R.drawable.product_placeholder),
                                contentScale = ContentScale.Crop,
                                model = product.imageUrl,
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight(.5f)
                            )
                            Text(
                                text = product.title,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                            Text(
                                text = product.type.name.lowercase(),
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1,
                                fontSize = 16.sp,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                            Text(
                                text = product.price,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1,
                                fontSize = 16.sp,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }
                    }
                }

            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun FilterItemsPreview() {
    FilterItemsContent(onClickFilterButton = {}, state = FilterItemsUiState())
}