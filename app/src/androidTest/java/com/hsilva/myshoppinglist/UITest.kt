package com.hsilva.myshoppinglist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.hsilva.myshoppinglist.ui.theme.MyShoppingAppTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UITest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun openApp() {
        // Start the app
        composeTestRule.setContent {
            MyShoppingAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ShoppingNavHost()
                }
            }
        }
    }

    @Test
    fun assertLayout() {
        composeTestRule.onNodeWithTag("TextInput").apply {
            assertIsDisplayed()
        }

        composeTestRule.onNodeWithTag("AddButton").apply {
            assertIsDisplayed()
        }
    }

/*    @Test
    fun assertNewItemIsCreated() {
        val currentItems = composeTestRule.onNodeWithTag("ShoppingList")
            .onChildren().fetchSemanticsNodes().size

        repeat(currentItems) {
            composeTestRule.onNodeWithTag("ShoppingList")
                .onChildAt(it).onChildAt(2).performClick()
        }

        val testItem = UUID.randomUUID().toString().subSequence(0, 5).toString()

        composeTestRule.onNodeWithTag("TextInput").apply {
            assertIsDisplayed()
            performTextInput(testItem)
        }

        composeTestRule.onNodeWithTag("AddButton").apply {
            assertIsDisplayed()
            performClick()
        }

        composeTestRule.onNodeWithText(testItem).assertIsDisplayed()
    }

    @Test
    fun assertListIsDeleted() {
        val currentItems = composeTestRule.onNodeWithTag("ShoppingList")
            .onChildren().fetchSemanticsNodes().size

        repeat(currentItems) {
            composeTestRule.onNodeWithTag("ShoppingList")
                .onChildAt(it).onChildAt(2).performClick()
        }

        composeTestRule.onNodeWithTag("DeleteButton").assertIsNotDisplayed()
    }*/
}