package com.decagonhq.clads_client.presentation.ui

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.decagonhq.clads_client.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class SplashScreenActivityTest {

    @get: Rule
    val activityRule = ActivityScenarioRule(SplashScreenActivity::class.java)

    // checking the layout if its visible or displayed to user
    @Test
    fun checkActivityVisibility() {
        Espresso.onView(withId(R.id.splash_activity))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    // checking if the text in mainActivity is visible as we want
    @Test
    fun checkTextVisibility() {
        Espresso.onView(withId(R.id.loading_text_view))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
    @Test
    fun checkProgressBarVisibility() {
        Espresso.onView(withId(R.id.splashScreen_progressBar))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}
