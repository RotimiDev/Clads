package com.decagonhq.clads_client.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.decagonhq.clads_client.data.model.GenericResult
import com.decagonhq.clads_client.data.model.RegistrationPayload
import com.decagonhq.clads_client.data.model.RegistrationRequest
import com.decagonhq.clads_client.data.repository.AuthRepository
import com.decagonhq.clads_client.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private var _registerResponse = MutableLiveData<Resource<GenericResult<RegistrationPayload>>>()
    val registerResponse: LiveData<Resource<GenericResult<RegistrationPayload>>> get() = _registerResponse

    fun registerUser(registerRequest: RegistrationRequest) {
        _registerResponse.value = Resource.Loading()
        viewModelScope.launch {
            _registerResponse.value = authRepository.registerUser(registerRequest)
        }
    }
}
