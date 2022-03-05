package com.decagonhq.clads_client.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.decagonhq.clads_client.data.model.GetImage
import com.decagonhq.clads_client.data.repository.ProfileRepository
import com.decagonhq.clads_client.presentation.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ProfileFragmentViewModel @Inject constructor(private val repository: ProfileRepository) : ViewModel(){


        init {
            getProfileImage()
        }
        var profileImage: MutableLiveData<Resource<GetImage>> = MutableLiveData()

        private fun getProfileImage() = viewModelScope.launch {
            profileImage.postValue(Resource.Loading())
            val profilePicture = repository.getProfileImage("")
            profileImage.postValue(handleUserData(profilePicture))
        }
        private fun handleUserData(userImage: Response<GetImage>): Resource<GetImage> {
            if (userImage.isSuccessful){
                userImage.body()?.let{ data ->
                    return Resource.Success(data)
                }
            }
            return Resource.Error(null,userImage.message())
        }
}