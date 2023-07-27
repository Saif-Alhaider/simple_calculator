package com.example.simplecalculator.ui.screen

import androidx.compose.ui.test.assertAny
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasNoClickAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.performClick
import com.example.simplecalculator.ui.screen.filter_items.FilterItemsScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FilterItemsTest {


    @get:Rule
    val rule = createComposeRule()

    @Before
    fun setup() {
        rule.setContent { FilterItemsScreen() }
    }

    @Test
    fun should_categoryClothesBeInitialCategory() {
        rule.onNode(
            hasTestTag("CLOTHES")
                    and hasContentDescription("isActive:true")
        )
    }

    @Test
    fun should_changeToActive_whenClickingOnCategory() {
        rule.onNode(hasTestTag("ELECTRONICS") and hasClickAction()).performClick()
        rule.onNode(
            hasTestTag("ELECTRONICS")
                    and
                    hasContentDescription("isActive:true")
        ).assertExists()
    }

    @Test
    fun shouldNot_displayElectronics_in_ClothesCategory() {
        rule.onAllNodes(hasContentDescription("ELECTRONICS") and hasNoClickAction())
            .assertCountEquals(0)
    }

    @Test
    fun should_buttonsBeSingleSelected_when_clickOnMultipleButtons() {
        val filterButtonsCount = rule.onNode(hasContentDescription("lazyRow")).onChildren()
            .fetchSemanticsNodes().size
        rule.onAllNodes(hasContentDescription("isActive:false"))
            .assertCountEquals(filterButtonsCount - 1)
    }

    @Test
    fun should_displayElectronics_in_ElectronicsCategory() {
        rule.onNode(hasTestTag("ELECTRONICS") and hasClickAction()).performClick()

        rule.onAllNodes(hasContentDescription("lazyItem"))
            .assertAny(hasContentDescription("lazyItem"))
    }

}