package com.decagonhq.clads_client.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.decagonhq.clads_client.data.model.GenericResult
import com.decagonhq.clads_client.data.model.LoginRequest
import com.decagonhq.clads_client.data.repository.AuthRepository
import com.decagonhq.clads_client.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private var _loginResponse = MutableLiveData<Resource<GenericResult<String>>>()
    val loginResponse: LiveData<Resource<GenericResult<String>>> get() = _loginResponse

    fun loginUser(loginRequest: LoginRequest) {

        _loginResponse.value = Resource.Loading()
        viewModelScope.launch {
            _loginResponse.value = authRepository.loginUser(loginRequest)
        }
    }
}
