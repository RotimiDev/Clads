package com.decagonhq.clads_client.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.decagonhq.clads_client.data.model.Profile
import com.decagonhq.clads_client.data.repsitory.ProfileRepository
import com.decagonhq.clads_client.presentation.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(private val repository: ProfileRepository) : ViewModel() {

    var profileDetails: MutableLiveData<Resource<Profile>> = MutableLiveData()

    fun getProfileDetails(token: String) = viewModelScope.launch {
        profileDetails.postValue(Resource.Loading())
        val profileData = repository.getProfile(token)
        profileDetails.postValue(handleUserData(profileData))
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
