package com.decagonhq.clads_client.presentation.ui

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.decagonhq.clads_client.R
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

class NewPasswordFragmentTest : TestCase(){

    private lateinit var  fragmentScenario: FragmentScenario<NewPasswordFragment>

    @Before
    fun setup(){
        fragmentScenario = launchFragmentInContainer(themeResId = R.style.Theme_CladsClient)
    }

    @Test
    fun are_views_working(){
        onView(ViewMatchers.withId(R.id.etNewPassword))
            .perform(
                ViewActions.typeText("pass123"),
                ViewActions.closeSoftKeyboard()
            )
        onView(ViewMatchers.withId(R.id.etConfirmPassword))
            .perform(
                ViewActions.typeText("pass123"),
                ViewActions.closeSoftKeyboard()
            )
        onView(ViewMatchers.withId(R.id.btnResetPassword)).perform(ViewActions.click())

    }

}