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

class EditProfileAccountFragmentTest : TestCase() {
    private lateinit var fragment: FragmentScenario<EditProfileAccountFragment>

    companion object {
        const val FIRSTNAME = "Peter"
        const val MIDDLENAME = "Paul"
        const val OTHERNAME = "Praise"
        const val STREET = "Asjon way"
        const val CITY = "Lagos"
    }

    @Before
    fun setup() {
        fragment = launchFragmentInContainer(themeResId = R.style.Theme_CladsClient)
    }

    @Test
    fun type_in_edit_texts() {
        onView(ViewMatchers.withId(R.id.edit_first_name_edit_text))
            .perform(ViewActions.typeText(FIRSTNAME), ViewActions.closeSoftKeyboard())
        onView(ViewMatchers.withId(R.id.edit_last_name_edit_text))
            .perform(ViewActions.typeText(MIDDLENAME), ViewActions.closeSoftKeyboard())
        onView(ViewMatchers.withId(R.id.edit_other_name_edit_text))
            .perform(ViewActions.typeText(OTHERNAME), ViewActions.closeSoftKeyboard())
        onView(ViewMatchers.withId(R.id.account_street_edit_text))
            .perform(ViewActions.typeText(STREET), ViewActions.closeSoftKeyboard())
        onView(ViewMatchers.withId(R.id.account_city_edit_text))
            .perform(ViewActions.typeText(CITY), ViewActions.closeSoftKeyboard())

        onView(ViewMatchers.withId(R.id.account_save_change_button)).perform(ViewActions.click())
    }
}
