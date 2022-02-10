package com.decagonhq.clads_client.presentation.ui

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.decagonhq.clads_client.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class ChoiceLoginFragmentTest

@Before
fun setUp() {
    launchFragmentInContainer<ChoiceLoginFragment>(
        themeResId = R.style.Theme_AppCompat
    )

}
