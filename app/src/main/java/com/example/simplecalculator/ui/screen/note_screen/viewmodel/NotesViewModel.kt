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
                text = text,
                isAddButtonEnabled = text.isNotBlank() && !it.notesList.any { note -> note.text == text }
            )
        }
    }

    fun onClickAdd() {
        _state.update {
            it.copy(
                notesList = it.notesList + Note(id = it.currentId + 1, text = it.text),
                text = "",
                isAddButtonEnabled = false,
                currentId = it.currentId + 1
            )
        }
    }


    fun onClickFilter() {
        _state.update {
            if (!_state.value.isFiltered && _state.value.text.isNotBlank()) {
                it.copy(
                    originalList = it.notesList,
                    notesList = it.notesList.filter { filter -> filter.text.contains(it.text) },
                    isFiltered = true
                )
            } else {
                it.copy(notesList = it.originalList, isFiltered = false)
            }
        }
    }


    fun onClickNote(id: Int, text: String) {
        _state.update {
            it.copy(
                notesList = it.notesList - Note(id = id, text = text),
                originalList = it.originalList - Note(id = id, text = text),
            )
        }
    }

    fun onClickDeleteAll() {
        _state.update {
            it.copy(
                notesList = emptyList(),
                originalList = emptyList()
            )
        }
    }
}