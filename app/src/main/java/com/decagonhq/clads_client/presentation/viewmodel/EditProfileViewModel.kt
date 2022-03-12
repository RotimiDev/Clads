package com.decagonhq.clads_client.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.decagonhq.clads_client.data.model.UploadImage
import com.decagonhq.clads_client.data.repository.ProfileRepository
import com.decagonhq.clads_client.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
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
}
