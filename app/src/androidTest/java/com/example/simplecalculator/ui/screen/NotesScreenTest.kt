package com.example.simplecalculator.ui.screen

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.example.simplecalculator.ui.screen.note_screen.NotesScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NotesScreenTest {
    @get:Rule
    val rule = createComposeRule()
    private val nameTextField = rule.onNodeWithContentDescription("Name text Field")
    private val addButton = rule.onNodeWithContentDescription("Add button")
    private val filterButton = rule.onNodeWithContentDescription("Filter button")
    private val deleteAllButton = rule.onNodeWithContentDescription("Delete all button")
    private val lazyColumn = rule.onNodeWithContentDescription("lazy column")
    private val item = rule.onNodeWithContentDescription("item")

    @Before
    fun setUp() {
        rule.setContent { NotesScreen() }
    }

    private fun addTextToList(text: String) {
        nameTextField.performTextInput(text)
        addButton.performClick()
    }

    @Test
    fun should_addTextToList_when_ClickAdd() {
        addTextToList("test")
        item.assertTextEquals("test")
    }

    @Test
    fun should_Not_AddTextToList_when_ClickAddWhileTextIsEmpty() {
        nameTextField.performTextInput("")
        addButton.assertIsNotEnabled()
    }

    @Test
    fun should_Not_AddTextToList_when_ClickAddWhileTextIsInList() {
        addTextToList("text")
        nameTextField.performTextInput("text")
        addButton.assertIsNotEnabled()
        lazyColumn.onChildren().assertCountEquals(1)
    }

    @Test
    fun should_AddTextsToList_when_ClickAdd() {
        addTextToList("test")
        addTextToList("tes")
        addTextToList("te")
        addTextToList("t")
        lazyColumn.onChildren().assertCountEquals(4)
    }

    @Test
    fun should_deleteNameFromList_when_ClickName() {
        addTextToList("test")
        item.performClick()
        lazyColumn.onChildren().assertCountEquals(0)
    }

    @Test
    fun should_filterNamesFromList_when_ClickFilter() {
        addTextToList("test")
        addTextToList("tes")
        addTextToList("te")
        addTextToList("t")
        nameTextField.performTextInput("te")
        filterButton.performClick()
        lazyColumn.onChildren().assertCountEquals(3)
    }

    @Test
    fun should_clearFilterNamesFromList_when_ClickFilter() {
        addTextToList("test")
        addTextToList("tes")
        addTextToList("te")
        addTextToList("t")
        nameTextField.performTextInput("te")
        filterButton.performClick()
        filterButton.assertTextEquals("Clear")
        filterButton.performClick()
        lazyColumn.onChildren().assertCountEquals(4)
    }

    @Test
    fun should_DeleteAllNamesFromList_when_ClickDeleteAll() {
        addTextToList("test")
        addTextToList("tes")
        addTextToList("te")
        addTextToList("t")
        deleteAllButton.performClick()
        lazyColumn.onChildren().assertCountEquals(0)
    }

    @Test
    fun should_TextBeSelected_when_ClickAdd() {
        addTextToList("test")
        nameTextField.assertIsFocused()
    }
}