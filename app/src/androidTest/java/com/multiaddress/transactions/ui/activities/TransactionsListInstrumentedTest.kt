package com.multiaddress.transactions.ui.activities

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.multiaddress.transactions.R
import com.multiaddress.transactions.data.Tx
import com.multiaddress.transactions.ui.waitForViewToShow
import com.schibsted.spain.barista.assertion.BaristaAssertions.assertThatBackButtonClosesTheApp
import com.schibsted.spain.barista.assertion.BaristaListAssertions.assertDisplayedAtPosition
import com.schibsted.spain.barista.assertion.BaristaListAssertions.assertListItemCount
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import org.hamcrest.CoreMatchers.*
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class TransactionsListInstrumentedTest {

    @get:Rule
    var rule = ActivityScenarioRule(TransactionsListActivity::class.java)

    @Test
    fun useAppContext() {
        val appContext = ApplicationProvider.getApplicationContext<Context>()
        assertEquals("com.multiaddress.transactions", appContext.packageName)
    }

    @Test
    fun refreshActionCheck() {
        onView(withId(R.id.action_refresh)).check(matches(allOf(isDisplayed(), isClickable())))
    }

    @Test
    fun walletCheck() {
        waitForViewToShow(withId(R.id.vWalletBalanceCard))
        assertDisplayed(R.id.vWalletBalanceCard)
        assertDisplayed(R.id.vWalletBalance, "0.00008549 BTC")
    }

    @Test
    fun transactionsListCheck() {
        waitForViewToShow(withId(R.id.vTransactionsList))
        assertDisplayed(R.id.vTransactionsHeader, "50 transactions")
        assertDisplayed(R.id.vTransactionsList)
        assertListItemCount(R.id.vTransactionsList, 50)
        assertDisplayedAtPosition(R.id.vTransactionsList, 0, R.id.vTransactionSizeText, "2843 bytes")
        assertDisplayedAtPosition(R.id.vTransactionsList, 0, R.id.vTransactionWeightText, "11372")
        assertDisplayedAtPosition(R.id.vTransactionsList, 0, R.id.vTransactionSentReceivedText, "SENT")
        assertDisplayedAtPosition(R.id.vTransactionsList, 0, R.id.vTransactionBtcValueText, "-0.00612687 BTC")
        assertDisplayedAtPosition(R.id.vTransactionsList, 0, R.id.vTransactionFeeValueText, "Fee: 0.00036881 BTC")
        assertDisplayedAtPosition(
            R.id.vTransactionsList,
            0,
            R.id.vTransactionHashText,
            "Hash: cbc06203f949804a512290ade05dcab35cf30c16b43bb0ede6f5074f1f8c3b9e"
        )
        assertDisplayedAtPosition(R.id.vTransactionsList, 0, R.id.vTransactionToFromText, "To: 1LMmvgrvSpufWEQFj4mrRYGuA7L44kvg8w")
        onData(`is`(instanceOf(Tx::class.java)))
    }

    @Test
    fun checkThatBackClosesApp() {
        assertThatBackButtonClosesTheApp()
    }
}
