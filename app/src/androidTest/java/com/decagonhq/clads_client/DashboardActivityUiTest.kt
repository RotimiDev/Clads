package com.decagonhq.clads_client
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.decagonhq.clads_client.presentation.ui.DashboardActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.regex.Pattern.matches

@RunWith(AndroidJUnit4ClassRunner::class)
class DashboardActivityUiTest {
    @get:Rule
    val activityRule: ActivityScenarioRule<DashboardActivity> = ActivityScenarioRule(DashboardActivity::class.java)

    @Test
    fun check_DashboardActivity_Visibility() {
        onView(withId(R.id.drawer_layout)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun check_Toolbar_Visibility() {
        onView(withId(R.id.toolbar)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun check_NavigationView_Visibility() {
        onView(withId(R.id.mainActivity_navView)).perform(DrawerActions.open())
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun check_BottomNav_Visibility() {
        onView(withId(R.id.bottomNavigationView)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}
