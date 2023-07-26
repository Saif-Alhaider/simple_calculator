package com.example.simplecalculator.ui.screen

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
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

    @Test
    fun when_add_two_numbers_should_present_the_result_of_the_addition() {
        rule.performClickOnButton("1")
        rule.performClickOnButton("+")
        rule.performClickOnButton("2")
        rule.performClickOnButton("=")
        result.assertTextEquals("3")
    }

    @Test
    fun when_add_multiple_numbers_should_represent_their_result_of_the_addition() {
        rule.performClickOnButton("1")
        rule.performClickOnButton("+")
        rule.performClickOnButton("2")
        rule.performClickOnButton("+")
        rule.performClickOnButton("3")
        rule.performClickOnButton("=")
        result.assertTextEquals("6")
    }

    @Test
    fun when_multiply_by_number_then_add_another_number() {
        rule.performClickOnButton("2")
        rule.performClickOnButton("X")
        rule.performClickOnButton("3")
        rule.performClickOnButton("+")
        rule.performClickOnButton("4")
        rule.performClickOnButton("=")
        result.assertTextEquals("10")
    }

    @Test
    fun when_divide_multi_times() {
        rule.performClickOnButton("1")
        rule.performClickOnButton("0")
        rule.performClickOnButton("0")
        rule.onNodeWithContentDescription("divide").performClick()
        rule.performClickOnButton("2")
        rule.onNodeWithContentDescription("divide").performClick()
        rule.performClickOnButton("2")
        rule.onNodeWithContentDescription("divide").performClick()
        rule.performClickOnButton("5")
        rule.performClickOnButton("=")

        result.assertTextEquals("5")
    }

    @Test
    fun should_evaluate_zero_when_modeling_even_numbers(){
        rule.performClickOnButton("4")
        rule.performClickOnButton("%")
        rule.performClickOnButton("4")
        rule.performClickOnButton("=")

        result.assertTextEquals("0")
    }
    @Test
    fun should_evaluate_number_when_modeling_odd_numbers(){
        rule.performClickOnButton("5")
        rule.performClickOnButton("%")
        rule.performClickOnButton("3")
        rule.performClickOnButton("=")

        result.assertTextEquals("2")
    }
    @Test
    fun should_evaluate_one_when_modeling_evenAndOdd_numbers(){
        rule.performClickOnButton("2")
        rule.performClickOnButton("%")
        rule.performClickOnButton("3")
        rule.performClickOnButton("=")

        result.assertTextEquals("1")
    }

}