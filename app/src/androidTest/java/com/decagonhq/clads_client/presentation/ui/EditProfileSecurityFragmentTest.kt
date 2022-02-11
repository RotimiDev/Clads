package com.decagonhq.clads_client.presentation.ui

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.decagonhq.clads_client.R
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

class EditProfileSecurityFragmentTest : TestCase() {
    private lateinit var fragment: FragmentScenario<EditProfileSecurityFragment>

    companion object {
        const val PHONE = "07000000000"
        const val OLDPASSWORD = "QWWERRRTT"
        const val NEWPASSWORD = "ASSDDFGGHH"
    }

    @Before
    fun setup() {
        fragment = launchFragmentInContainer(themeResId = R.style.Theme_CladsClient)
    }

    @Test
    fun type_in_edit_texts() {
        Espresso.onView(ViewMatchers.withId(R.id.security_phone_edit_text))
            .perform(ViewActions.typeText(PHONE), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.security_old_password_edit_text))
            .perform(ViewActions.typeText(OLDPASSWORD), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.security_new_password_edit_text))
            .perform(ViewActions.typeText(NEWPASSWORD), ViewActions.closeSoftKeyboard())
    } 
}
