package com.decagonhq.clads_client.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.decagonhq.clads_client.data.model.GenericResult
import com.decagonhq.clads_client.data.repository.AuthRepository
import com.decagonhq.clads_client.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private var _authenticationStatus = MutableLiveData<Resource<GenericResult<String>>>()
    val authenticationToken: LiveData<Resource<GenericResult<String>>> get() = _authenticationStatus

    fun verifyAuthToken(token: String) = viewModelScope.launch(Dispatchers.IO) {
        _authenticationStatus.postValue(Resource.Loading())
        val tokenResponse = authRepository.verifyUserMail(token)
        _authenticationStatus.postValue(handleToken(tokenResponse))
    }

    private fun handleToken(userData: Response<GenericResult<String>>): Resource<GenericResult<String>> {
        if (userData.isSuccessful) {
            userData.body()?.let { data ->
                return Resource.Success(data)
            }
        }
        return Resource.Error(null, userData.message())
    }
}
