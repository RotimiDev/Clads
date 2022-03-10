package com.decagonhq.clads_client.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.decagonhq.clads_client.data.model.Profile
import com.decagonhq.clads_client.data.model.UploadImage
import com.decagonhq.clads_client.data.repository.ProfileRepository
import com.decagonhq.clads_client.presentation.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel@Inject constructor(private val repository: ProfileRepository) : ViewModel() {
    private var _image: MutableLiveData<Resource<UploadImage>> = MutableLiveData()
    val postImage: LiveData<Resource<UploadImage>> get() = _image

    fun uploadImage(token: String, image: MultipartBody.Part) = viewModelScope.launch {
        _image.postValue(Resource.Loading())
        val sent = repository.postProfileImage(token, image)

        _image.postValue(handleSentPost(sent))
    }
    private fun handleSentPost(sent: Response<UploadImage>): Resource<UploadImage>? {

        if (sent.isSuccessful) {
            sent.body()?.let { sentPost ->
                return Resource.Success(sentPost)
            }
        }
        return Resource.Error(null, sent.message())
    }

    private var _profile = MutableLiveData<Resource<Profile>>()
    val profileDetails: LiveData<Resource<Profile>> get() = _profile

    fun getProfileDetails(token: String) = viewModelScope.launch(Dispatchers.IO) {
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
