package com.decagonhq.clads_client

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.decagonhq.clads_client.presentation.ui.SignUpOptionsFragment
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)

class SignUpOptionsFragmentTest : TestCase() {

    private lateinit var optionsFragmentScenario: FragmentScenario<SignUpOptionsFragment>

    companion object {
        const val FIRST_NAME = "Akeem"
        const val LAST_NAME = "Rotimi"
        const val OTHER_NAME = "Terry"
        const val EMAIL_ADDRESS = "akeemrotimi20@gmail.com"
        const val PASSWORD = "J4eeee611!"
    }

    @Before
    fun setup() {
        optionsFragmentScenario = launchFragmentInContainer(themeResId = R.style.Theme_CladsClient)
    }

    // Test if views are working correctly
    @Test
    fun testSignUpViews() {
        onView(ViewMatchers.withId(R.id.firstName_textView))
            .perform(
                ViewActions.typeText(FIRST_NAME),
                ViewActions.closeSoftKeyboard()
            )

        onView(ViewMatchers.withId(R.id.lastName_textView))
            .perform(
                ViewActions.typeText(LAST_NAME),
                ViewActions.closeSoftKeyboard()
            )

        onView(ViewMatchers.withId(R.id.OtherName_TextView))
            .perform(
                ViewActions.typeText(OTHER_NAME),
                ViewActions.closeSoftKeyboard()
            )

        onView(ViewMatchers.withId(R.id.emailAddress_textview))
            .perform(
                ViewActions.typeText(EMAIL_ADDRESS),
                ViewActions.closeSoftKeyboard()
            )

        onView(ViewMatchers.withId(R.id.password_textview))
            .perform(
                ViewActions.typeText(PASSWORD),
                ViewActions.closeSoftKeyboard()
            )
    }

    // Test signup text view visibility

    @Test
    fun testSignUpVisibility() {

        // Test button and spinner visibility
        onView(ViewMatchers.withText(R.id.sign_up_button))
        onView(ViewMatchers.withText(R.id.spinner))
    }
}
