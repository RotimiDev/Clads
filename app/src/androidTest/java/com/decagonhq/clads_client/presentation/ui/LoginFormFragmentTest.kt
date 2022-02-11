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

    companion object{
        const val EMAIL = "jmweltokg@gmail.com"
        const val PASSWORD = "pass123"
    }

    @Before
    fun setup() {
        fragmentScenario = launchFragmentInContainer(themeResId = R.style.Theme_CladsClient)
    }

    @Test
    fun test_are_views_working() {
        onView(ViewMatchers.withId(R.id.enter_email_edit_text))
            .perform(
                ViewActions.typeText(EMAIL),
                ViewActions.closeSoftKeyboard()
            )

        onView(ViewMatchers.withId(R.id.login_password_edit_text))
            .perform(
                ViewActions.typeText(PASSWORD),
                ViewActions.closeSoftKeyboard()
            )

        onView(ViewMatchers.withId(R.id.login_button)).perform(ViewActions.click())
    }

    @Test
    fun test_are_text_visible() {
        onView(ViewMatchers.withText(R.string.login_fragment_welcome_text))
        onView(ViewMatchers.withText(R.string.login_fragment_subHeading))
        onView(ViewMatchers.withText(R.string.all_forgot_password))
        onView(ViewMatchers.withText(R.string.login_fragment_signup_for_free))
        onView(ViewMatchers.withText(R.string.login_fragment_new_user))
    }
}
