package com.decagonhq.clads_client

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)

class SignupActivityTest {

    @get:Rule
    val activityRule: ActivityScenarioRule<SignupActivity> =
        ActivityScenarioRule(SignupActivity::class.java)

    // Test signup activity visibility
    @Test
    fun testActivityInView() {
        onView(withId(R.id.activity_signup)).check(matches(isDisplayed()))
    }

    // Edit text visibility
    @Test
    fun testFirstNameEditTextInView() {
        onView(withId(R.id.firstNameEditText)).check(matches(isDisplayed()))
    }

    // Test spinner visibility
    @Test
    fun testSpinnerIsDisplayed() {
        onView(withId(R.id.spinner)).check(matches(isDisplayed()))
    }
}
