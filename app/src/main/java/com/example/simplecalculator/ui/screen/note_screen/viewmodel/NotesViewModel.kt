package com.example.simplecalculator.ui.screen.note_screen.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(NotesUIState())
    val state = _state.asStateFlow()


    fun onTextChange(text: String) {
        _state.update {
            it.copy(
                name = text,
                isAddButtonEnabled = text.isNotBlank() && !it.nameList.contains(text)
            )
        }
    }

    fun onClickAdd() {
        _state.update {
            it.copy(
                nameList = it.nameList + it.name,
                name = ""
            )
        }
    }


    fun onClickFilter() {
        _state.update {
            if (!_state.value.isFiltered && _state.value.name.isNotBlank()) {
                it.copy(
                    originalList = it.nameList,
                    nameList = it.nameList.filter { filter -> filter.contains(it.name) },
                    isFiltered = true
                )
            } else {
                it.copy(nameList = it.originalList, isFiltered = false)
            }
        }
    }


    fun onClickName(name: String) {
        _state.update {
            it.copy(
                nameList = it.nameList - name,
                originalList = it.originalList - name
            )
        }
    }

    fun onClickDeleteAll() {
        _state.update {
            it.copy(
                nameList = emptyList(),
                originalList = emptyList()
            )
        }
    }
}