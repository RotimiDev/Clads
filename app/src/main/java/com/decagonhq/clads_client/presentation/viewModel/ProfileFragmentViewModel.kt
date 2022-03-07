package com.decagonhq.clads_client.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.decagonhq.clads_client.data.repsitory.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileFragmentViewModel @Inject constructor(private val repository: ProfileRepository) : ViewModel()
