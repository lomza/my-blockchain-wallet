package com.multiaddress.transactions.ui

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import org.hamcrest.Matcher

fun waitForViewToShow(matcher: Matcher<View>) {
    val idlingResource = ViewShowIdlingResource(matcher)
    try {
        IdlingRegistry.getInstance().register(idlingResource)
        onView(matcher).check(matches(isDisplayed()))
    } finally {
        IdlingRegistry.getInstance().unregister(idlingResource)
    }
}
