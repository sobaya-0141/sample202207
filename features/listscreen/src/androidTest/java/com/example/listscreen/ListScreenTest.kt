package com.example.listscreen

import androidx.compose.material.MaterialTheme
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun uiState_Error() {
        composeTestRule.setContent {
            MaterialTheme {
                ListScreen(
                    uiState = ListState(
                        isError = true,
                        isLoading = false,
                        data = null
                    ),
                    onClickRetry = { /*TODO*/ }
                )
            }
        }
        composeTestRule.onNodeWithText("エラーです").assertIsDisplayed()
    }

    @Test
    fun uiState_Loading() {
        composeTestRule.setContent {
            MaterialTheme {
                ListScreen(
                    uiState = ListState(
                        isError = false,
                        isLoading = true,
                        data = null
                    ),
                    onClickRetry = { /*TODO*/ }
                )
            }
        }
        composeTestRule.onNodeWithText("loading").assertIsDisplayed()
    }

    @Test
    fun uiState_Success() {
        composeTestRule.setContent {
            MaterialTheme {
                ListScreen(
                    uiState = ListState(
                        isError = false,
                        isLoading = false,
                        data = null
                    ),
                    onClickRetry = { /*TODO*/ }
                )
            }
        }
        composeTestRule.onNodeWithText("SUCCESS").assertIsDisplayed()
    }
}