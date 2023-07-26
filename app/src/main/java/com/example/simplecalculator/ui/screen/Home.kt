package com.example.simplecalculator.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreen(viewModel: HomViewModel = hiltViewModel()) {
    val state = viewModel.state.collectAsState().value
    HomeContent(
        onClickAction = {
            viewModel.updateValue(it)
        },
        onDelete = viewModel::delete,
        onClear = viewModel::clear,
        onEqual = viewModel::performAction,
        state = state
    )
}

@Composable
fun HomeContent(
    onClickAction: (String) -> Unit,
    onDelete: () -> Unit,
    onClear: () -> Unit,
    onEqual: () -> Unit,
    state: HomeUiState
) {
    Column {
        Box(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(.4f)
                .background(Color(0xFFd9e0e9))
                .padding(vertical = 24.dp, horizontal = 16.dp)
        ) {
            Column(Modifier.align(Alignment.BottomEnd)) {
                Text(
                    state.equation,
                    color = Color.Black.copy(alpha = .87f),
                    fontSize = 48.sp,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .semantics { contentDescription = "result" }
                        .fillMaxWidth()
                        .pointerInput(Unit) {
                            detectHorizontalDragGestures { change, dragAmount ->
                                if (change.isConsumed && dragAmount > 0) {
                                    onDelete()
                                }
                            }
                        }
                )
            }
        }
        Column(
            Modifier
                .fillMaxSize()
                .background(Color(0xFFf8f9fb))
                .padding(vertical = 16.dp)
        ) {
            Row {
                CalculatingButton(
                    text = "C",
                    textColor = Color(0xFFca5659),
                    modifier = Modifier
                        .weight(1f)
                        .semantics { contentDescription = "clear" },
                    onClick = onClear
                )
                CalculatingButton(
                    text = "π",
                    textColor = Color(0xFF32a6a3),
                    modifier = Modifier.weight(1f),
                    onClick = { onClickAction("π") }
                )
                CalculatingButton(
                    text = "%",
                    textColor = Color(0xFF32a6a3),
                    modifier = Modifier.weight(1f),
                    onClick = { onClickAction("%") }
                )
                CalculatingButton(
                    text = "÷",
                    textColor = Color(0xFF32a6a3),
                    modifier = Modifier.weight(1f),
                    onClick = { onClickAction("÷") }
                )
            }
            Box(Modifier.weight(1f)) {
                Row(Modifier.fillMaxWidth()) {
                    Box(modifier = Modifier.weight(3f)) {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(3),
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            items(9) {
                                CalculatingButton(
                                    text = (it + 1).toString(),
                                    modifier = Modifier,
                                    onClick = { onClickAction((it + 1).toString()) }
                                )
                            }
                        }
                    }
                    Box(modifier = Modifier.weight(1f)) {
                        Column(
                            verticalArrangement = Arrangement.SpaceEvenly,

                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            CalculatingButton(
                                text = "X",
                                modifier = Modifier.fillMaxWidth(),
                                onClick = { onClickAction("X") })
                            CalculatingButton(
                                text = "+",
                                modifier = Modifier.fillMaxWidth(),
                                onClick = { onClickAction("+") })
                            CalculatingButton(
                                text = "-",
                                modifier = Modifier.fillMaxWidth(),
                                onClick = { onClickAction("-") })
                        }
                    }
                }
            }
            Row {
                CalculatingButton(
                    text = ".",
                    textColor = Color(0xFF32a6a3),
                    modifier = Modifier.weight(1f),
                    onClick = { onClickAction(".") }
                )
                CalculatingButton(
                    text = "0",
                    textColor = Color(0xFF32a6a3),
                    modifier = Modifier.weight(1f),
                    onClick = { onClickAction("0") }
                )
                CalculatingButton(
                    text = "000",
                    textColor = Color(0xFF32a6a3),
                    modifier = Modifier.weight(1f),
                    onClick = { onClickAction("000") }
                )
                CalculatingButton(
                    text = "=",
                    textColor = Color(0xFF32a6a3),
                    modifier = Modifier.weight(1f),
                    onClick = onEqual
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomePreview() {
    HomeContent(
        onClickAction = {},
        onDelete = {},
        onClear = {},
        onEqual = {},
        state = HomeUiState()
    )
}