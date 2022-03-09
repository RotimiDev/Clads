package com.decagonhq.clads_client.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.decagonhq.clads_client.data.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileFragmentViewModel @Inject constructor(private val repository: ProfileRepository) : ViewModel()
