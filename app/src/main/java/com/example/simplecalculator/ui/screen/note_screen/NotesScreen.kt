package com.example.simplecalculator.ui.screen.note_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.simplecalculator.R
import com.example.simplecalculator.ui.screen.note_screen.viewmodel.NotesUIState
import com.example.simplecalculator.ui.screen.note_screen.viewmodel.NotesViewModel

@Composable
fun NotesScreen() {
    val viewModel: NotesViewModel = hiltViewModel()
    val state = viewModel.state.collectAsState().value
    NotesContent(
        state = state,
        onTextChange = viewModel::onTextChange,
        onClickAdd = viewModel::onClickAdd,
        onClickName = viewModel::onClickName,
        onClickDeleteAll = viewModel::onClickDeleteAll,
        onClickFilter = viewModel::onClickFilter,
    )
}

@Composable
fun NotesContent(
    state: NotesUIState,
    onTextChange: (String) -> Unit,
    onClickAdd: () -> Unit,
    onClickName: (String) -> Unit,
    onClickDeleteAll: () -> Unit,
    onClickFilter: () -> Unit,
) {
    Column(Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .background(color = Color(0xFFB8ACA0), shape = RoundedCornerShape(16)),
        ) {
            BasicTextField(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .semantics { contentDescription = "Name text Field" },
                value = state.name,
                onValueChange = onTextChange,
                singleLine = true,
            )
        }
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .semantics { contentDescription = "Buttons row" },
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                enabled = state.isAddButtonEnabled,
                onClick = onClickAdd,
                modifier = Modifier.semantics { contentDescription = "Add button" }
            ) {
                Text(text = stringResource(R.string.add))
            }


            Button(
                onClick = onClickFilter,
                modifier = Modifier.semantics { contentDescription = "Filter button" }
            ) {
                if (!state.isFiltered) {
                    Text(text = stringResource(R.string.filter))
                } else {
                    Text(text = stringResource(R.string.clear))
                }
            }
            Button(
                onClick = onClickDeleteAll,
                modifier = Modifier.semantics { contentDescription = "Delete all button" }
            ) {
                Text(text = stringResource(R.string.delete_all))
            }
        }

        LazyColumn(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
                .background(color = Color(0xFFB8ACA0), shape = RoundedCornerShape(4))
                .semantics { contentDescription = "lazy column" },
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.nameList.size) { index ->
                Row(Modifier, horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        text = state.nameList[index],
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .semantics { contentDescription = "item" }
                            .clickable { onClickName(state.nameList[index]) }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeContentPreview() {
    NotesContent(
        state = NotesUIState(name = "name"),
        onTextChange = {},
        onClickAdd = {},
        onClickName = {},
        onClickDeleteAll = {},
        onClickFilter = {},
    )
}