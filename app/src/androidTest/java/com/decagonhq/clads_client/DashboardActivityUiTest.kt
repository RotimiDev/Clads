package com.decagonhq.clads_client

import android.view.Gravity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.contrib.DrawerMatchers.isClosed
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.decagonhq.clads_client.presentation.ui.DashboardActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class DashboardActivityUiTest {
    @get:Rule
    val activityRule: ActivityScenarioRule<DashboardActivity> =
        ActivityScenarioRule(DashboardActivity::class.java)

    @Test
    fun test_dashBoardActivity_visibility() {
        onView(withId(R.id.drawer_layout)).check(matches(isDisplayed()))
    }

    @Test
    fun test_toolBar_visibility() {
        onView(withId(R.id.toolbar_include)).check(matches(isDisplayed()))
    }

    @Test
    fun test_navigationDrawer_visibility() {
        onView(withId(R.id.drawer_layout))
            .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
            .perform(DrawerActions.open())
            .check(matches(isDisplayed()))
    }

    @Test
    fun test_bottomNavigation_visibility() {
        onView(withId(R.id.bottomNavigationView)).check(matches(isDisplayed()))
    }
}
