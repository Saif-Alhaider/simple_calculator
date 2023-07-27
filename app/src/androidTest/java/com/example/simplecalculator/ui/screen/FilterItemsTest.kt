package com.example.simplecalculator.ui.screen

import android.util.Log
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
    fun should_changeToActive_whenClickingOnCategory() {
        rule.onNode(hasTestTag("CLOTHES") and hasClickAction()).performClick()
        rule.onNode(
            hasTestTag("CLOTHES")
                    and hasClickAction()
                    and
                    hasContentDescription("isActive:true")
        )
            .assertExists()
    }

    @Test
    fun shouldNot_displayElectronics_in_ClothesCategory() {
        rule.onAllNodes(hasContentDescription("ELECTRONICS")).assertCountEquals(0)
    }

    @Test
    fun myTest(){
        Log.i("gg", "myTest: ${rule.onNode(hasContentDescription("lazyRow")).onChildren().fetchSemanticsNodes().size}")
    }
    @Test
    fun should_displayElectronics_in_ElectronicsCategory() {
        rule.onNode(hasTestTag("ELECTRONICS") and hasClickAction()).performClick()

        rule.onAllNodes(hasContentDescription("ELECTRONICS") and hasNoClickAction())
            .assertAny(hasContentDescription("ELECTRONICS"))
    }

    @Test
    fun should_playAnimation_on_NavigateToOtherCategory() {
        rule.onNode(hasTestTag("ELECTRONICS") and hasClickAction()).performClick()
        rule.mainClock.autoAdvance = false
        rule.mainClock.advanceTimeBy(50)
        rule.onAllNodes(hasContentDescription("ELECTRONICS")).assertCountEquals(0)
    }

}