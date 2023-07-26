package com.example.simplecalculator.ui.screen.note_screen.viewmodel

data class NotesUIState(
    val name: String = "",
    val nameList: List<String> = emptyList(),
    val originalList: List<String> = emptyList(),
    val isFiltered: Boolean = false,
)