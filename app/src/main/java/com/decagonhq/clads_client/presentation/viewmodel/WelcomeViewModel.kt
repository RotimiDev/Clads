package com.decagonhq.clads_client.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.decagonhq.clads_client.data.repository.WelcomeRepository
import javax.inject.Inject

class WelcomeViewModel @Inject constructor(
    private val welcomeRepository: WelcomeRepository
) : ViewModel()
