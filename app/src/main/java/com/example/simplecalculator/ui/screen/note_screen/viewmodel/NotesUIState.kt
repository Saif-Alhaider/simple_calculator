package com.example.simplecalculator.ui.screen.note_screen.viewmodel

data class NotesUIState(
    val text: String = "",
    val notesList: List<Note> = emptyList(),
    val originalList: List<Note> = emptyList(),
    val isFiltered: Boolean = false,
    val isAddButtonEnabled: Boolean = false,
    val currentId: Int = 0
)

data class Note(
    val id: Int = 1,
    val text: String = "",
)