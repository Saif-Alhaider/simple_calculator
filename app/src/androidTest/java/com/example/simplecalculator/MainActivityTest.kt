package com.example.simplecalculator

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith

@HiltAndroidTest // 1
class MainActivityTest{
    @get:Rule
    var hiltAndroidRule = HiltAndroidRule(this) // 5

    @Before
    fun setUp() {
        hiltAndroidRule.inject() // 6
    }
}