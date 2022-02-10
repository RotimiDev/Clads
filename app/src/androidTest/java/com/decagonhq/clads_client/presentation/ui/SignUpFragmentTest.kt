package com.decagonhq.clads_client.presentation.ui

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.decagonhq.clads_client.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SignUpFragmentTest {

    //    launching AND TESTING A FRAGMENT WHICH CONTAINS UI
    @Before
    fun setUp() {
        launchFragmentInContainer<SignUpFragment>(
            themeResId = R.style.Theme_AppCompat
        )

    }

    //checking the layout if its visible or displayed to user
    @Test
    fun checkFragmentVisibility() {
        onView(withId(R.id.signupLayout))
            .check(matches(isDisplayed()))
    }

    //    checking if the button is visible
    @Test
    fun checkingButtonVisibility() {
        onView(withId(R.id.sign_up_button))
            .check(matches(isDisplayed()))

        onView(withId(R.id.login_button))
            .check(matches(isDisplayed()))
    }

    //checking if the text in signUpFragment is visible as we want
    @Test
    fun checkingTextVisibility() {
        onView(withId(R.id.txtLorem))
            .check(matches(isDisplayed()))
    }

    //checking if the test in our view is the right one
    @Test
    fun textTextIsSignUpFragment() {
        onView(withId(R.id.txtLorem)).check(matches(withText(R.string.lorem_ipsum_ipsum_sum)))
    }


}