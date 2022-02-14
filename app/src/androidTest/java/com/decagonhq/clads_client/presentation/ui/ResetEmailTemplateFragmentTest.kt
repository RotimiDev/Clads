package com.decagonhq.clads_client.presentation.ui

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.decagonhq.clads_client.R
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ResetEmailTemplateFragmentTest : TestCase() {

    private lateinit var fragmentScenario: FragmentScenario<ResetEmailTemplateFragment>

    companion object {
        const val PASSWORD = "pass123"
    }

    @Before
    fun setup() {
        fragmentScenario = launchFragmentInContainer(themeResId = R.style.Theme_CladsClient)
    }

    @Test
    fun test_are_views_displayed() {
        onView(withId(R.id.reset_password_button))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.clads_logo_image_view))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.reset_password_card_view))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.facebook_logo_image_view))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun test_if_button_clicks() {
        onView(withId(R.id.reset_password_button))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withText(R.string.all_reset_password))
        onView(withId(R.id.reset_password_button)).perform(ViewActions.click())
    }

    @Test
    fun test_navigation() {
        onView(withId(R.id.reset_password_button)).perform(ViewActions.click())

        onView(withId(R.id.newPassword_text_input_edit_text))
            .perform(
                ViewActions.typeText(PASSWORD),
                ViewActions.closeSoftKeyboard()
            )
        onView(withText(R.string.all_forgot_password))
        onView(withText(R.string.all_i_can_remember_my_password))
        onView(withText(R.string.all_login))
    }
}
