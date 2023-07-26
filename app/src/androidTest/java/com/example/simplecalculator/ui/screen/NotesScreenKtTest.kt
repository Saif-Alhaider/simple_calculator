package com.example.simplecalculator.ui.screen

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.example.simplecalculator.ui.screen.note_screen.NotesScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NotesScreenKtTest {
    @get:Rule
    val rule = createComposeRule()
    private val nameTextField = rule.onNodeWithContentDescription("Name text Field")
    private val addButton = rule.onNodeWithContentDescription("Add button")
    private val filterButton = rule.onNodeWithContentDescription("Filter button")
    private val deleteAllButton = rule.onNodeWithContentDescription("Delete all button")
    private val namesLazyColumn = rule.onNodeWithContentDescription("Names lazy column")
    private val namesLazyColumnItem = rule.onNodeWithContentDescription("Names lazy column item")

    @Before
    fun setUp() {
        rule.setContent { NotesScreen() }
    }

    private fun ComposeContentTestRule.addTextToList(text: String) {
        nameTextField.performTextInput(text)
        addButton.performClick()
    }

    @Test
    fun should_addTextToList_when_ClickAdd() {
        rule.addTextToList("test")
        namesLazyColumnItem.assertTextEquals("test")
    }

    @Test
    fun should_deleteNameFromList_when_ClickName() {
        rule.addTextToList("test")
        namesLazyColumnItem.performClick()
        namesLazyColumnItem.assertDoesNotExist()
    }

//    @Test
//    fun should_filterNamesFromList_when_ClickName() {
//        rule.addTextToList("test")
//        rule.addTextToList("tes")
//        rule.addTextToList("te")
//        rule.addTextToList("t")
//        nameTextField.performTextInput("te")
//        filterButton.performClick()
//        namesLazyColumn.as()
//    }
}