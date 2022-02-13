package com.decagonhq.clads_client

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.decagonhq.clads_client.presentation.ui.SignUpFragment
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)

class EmailConfirmationFragmentTest : TestCase() {

    private lateinit var fragmentScenario: FragmentScenario<SignUpFragment>

    @Before
    fun setup() {
        fragmentScenario = launchFragmentInContainer(themeResId = R.style.Theme_CladsClient)
    }

    // Test signup text visibility

    @Test
    fun testVerifyEmailButton() {
        Espresso.onView(ViewMatchers.withText(R.id.verifyEmailAddress))
    }
}
