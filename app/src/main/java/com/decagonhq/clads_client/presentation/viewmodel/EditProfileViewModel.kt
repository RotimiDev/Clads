package com.decagonhq.clads_client.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.decagonhq.clads_client.data.model.Profile
import com.decagonhq.clads_client.data.model.UploadImage
import com.decagonhq.clads_client.data.repository.ProfileRepository
import com.decagonhq.clads_client.presentation.network.NetworkConstants.Companion.TOKEN
import com.decagonhq.clads_client.presentation.utils.Resource
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import retrofit2.Response
import javax.inject.Inject

class EditProfileViewModel@Inject constructor(private val repository: ProfileRepository) : ViewModel() {
    var postImage: MutableLiveData<Resource<UploadImage>> = MutableLiveData()
    fun uploadImage(token: String, image: MultipartBody.Part) = viewModelScope.launch {
        postImage.postValue(Resource.Loading())
        val sent = repository.postProfileImage(token, image)

        postImage.postValue(handleSentPost(sent))
    }
    private fun handleSentPost(sent: Response<UploadImage>): Resource<UploadImage>? {

        if (sent.isSuccessful) {
            sent.body()?.let { sentPost ->
                return Resource.Success(sentPost)
            }
        }
        return Resource.Error(null, sent.message())
    }

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
