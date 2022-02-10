package com.decagonhq.clads_client.presentation.ui

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.decagonhq.clads_client.R
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ResetEmailTemplateFragmentTest : TestCase(){


    private lateinit var fragmentScenario: FragmentScenario<ResetEmailTemplateFragment>

    @Before
    fun setup(){
        fragmentScenario = launchFragmentInContainer(themeResId = R.style.Theme_CladsClient)
    }

    @Test
    fun test_are_views_displayed(){
        onView(withId(R.id.btnResetPassword))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.obiomaLogo3))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.cvResetPassword))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.ivFacebook))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun test_if_button_clicks(){
        onView(withId(R.id.btnResetPassword))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withText(R.string.reset_password))
        onView(withId(R.id.btnResetPassword)).perform(ViewActions.click())
    }

    @Test
    fun test_navigation(){
        onView(withId(R.id.btnResetPassword)).perform(ViewActions.click())

        onView(withId(R.id.etNewPassword))
            .perform(ViewActions.typeText("pass@123"),
                ViewActions.closeSoftKeyboard()
            )
        onView(withText(R.string.forgot_password))
        onView(withText(R.string.i_can_remember_my_password))
        onView(withText(R.string.login))
    }

}