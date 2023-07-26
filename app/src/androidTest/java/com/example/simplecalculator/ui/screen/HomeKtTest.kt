package com.example.simplecalculator.ui.screen

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeRight
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeKtTest {
    @get:Rule
    val rule = createComposeRule()
    private val result = rule.onNodeWithContentDescription("result")

    @Before
    fun setUp() {
        rule.setContent { HomeScreen() }
    }

    private fun ComposeContentTestRule.performClickOnButton(text: String) {
        onNode(hasText(text) and hasClickAction()).performClick()
    }

    @Test
    fun should_deleteCharacter_when_dragToTheRight() {
        rule.performClickOnButton("1")
        result.assertTextEquals("1")
        result.performTouchInput { swipeRight() }
        result.assertTextEquals("")
    }

    @Test
    fun should_displayAllNumbers_when_clickingAllNumbers() {
        for (number in 0..9) {
            rule.performClickOnButton(number.toString())
        }
        rule.performClickOnButton("000")
        rule.performClickOnButton(".")
        result.assertTextEquals("0123456789000.")
    }

    @Test
    fun should_clearResult_when_enteringManyOperations() {
        rule.performClickOnButton("1")
        rule.performClickOnButton("2")
        rule.performClickOnButton("C")
        result.assertTextEquals("")
    }

    @Test
    fun should_clearResult_when_enteringOneOperations() {
        rule.performClickOnButton("1")
        rule.performClickOnButton("C")
        result.assertTextEquals("")
    }

    @Test
    fun should_clearResult_when_notEnteringAnyOperation() {
        rule.performClickOnButton("C")
        result.assertTextEquals("")
    }
}