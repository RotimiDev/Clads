package com.decagonhq.clads_client.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.decagonhq.clads_client.data.model.GenericResult
import com.decagonhq.clads_client.data.repository.AuthRepository
import com.decagonhq.clads_client.presentation.model.UpdateProfilePayload
import com.decagonhq.clads_client.presentation.model.UpdateProfileRequest
import com.decagonhq.clads_client.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateProfileViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private var _updateProfileResponse = MutableLiveData<Resource<GenericResult<UpdateProfilePayload>>>()
    val updateProfileResponse: LiveData<Resource<GenericResult<UpdateProfilePayload>>> get() = _updateProfileResponse

    fun updateUserProfile (bearer: String, updateProfileRequest: UpdateProfileRequest){
        _updateProfileResponse.value = Resource.Loading()
        viewModelScope.launch {
            _updateProfileResponse.value = authRepository.updateUserProfile(bearer, updateProfileRequest)
        }
    }


}