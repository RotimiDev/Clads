package com.decagonhq.clads_client.presentation.ui

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.decagonhq.clads_client.R
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

class LoginFormFragmentTest : TestCase() {
    private lateinit var fragmentScenario: FragmentScenario<LoginFormFragment>

    @Before
    fun setup() {
        fragmentScenario = launchFragmentInContainer(themeResId = R.style.Theme_CladsClient)
    }

    @Test
    fun test_are_views_working() {
        onView(ViewMatchers.withId(R.id.etEnterEmail))
            .perform(
                ViewActions.typeText("jmweltokg@gmail.com"),
                ViewActions.closeSoftKeyboard()
            )

        onView(ViewMatchers.withId(R.id.etPassword))
            .perform(
                ViewActions.typeText("pass@123"),
                ViewActions.closeSoftKeyboard()
            )

        onView(ViewMatchers.withId(R.id.btnLogin)).perform(ViewActions.click())
    }

    @Test
    fun test_are_text_visible() {
        onView(ViewMatchers.withText(R.string.welcome_back))
        onView(ViewMatchers.withText(R.string.login_to_continue))
        onView(ViewMatchers.withText(R.string.forgot_password))
        onView(ViewMatchers.withText(R.string.signup_for_free))
        onView(ViewMatchers.withText(R.string.new_use))
    }
}
