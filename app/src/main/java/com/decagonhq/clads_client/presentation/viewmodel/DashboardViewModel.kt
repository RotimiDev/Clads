package com.decagonhq.clads_client.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.decagonhq.clads_client.data.model.Profile
import com.decagonhq.clads_client.data.repository.ProfileRepository
import com.decagonhq.clads_client.presentation.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(private val repository: ProfileRepository):ViewModel() {

    private var _profile = MutableLiveData<Resource<Profile>>()
    val dashboardProfileDetails: LiveData<Resource<Profile>> get() = _profile

    fun getDetails(token: String) = viewModelScope.launch(Dispatchers.IO) {
        _profile.postValue(Resource.Loading())
        val profileData = repository.getProfile(token)
        _profile.postValue(handleUserData(profileData))
    }
    private fun handleUserData(userData: Response<Profile>): Resource<Profile> {
        if (userData.isSuccessful) {
            userData.body()?.let { data ->
                return Resource.Success(data)
            }
        }
        return Resource.Error(null, userData.message())
    }
}