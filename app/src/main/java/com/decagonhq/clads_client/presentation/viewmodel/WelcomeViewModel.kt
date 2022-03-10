package com.decagonhq.clads_client.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.decagonhq.clads_client.data.repository.AuthRepository
import com.decagonhq.clads_client.presentation.model.GenericResult
import com.decagonhq.clads_client.presentation.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    var authenticationToken: MutableLiveData<Resource<GenericResult<String>>> = MutableLiveData()

    fun verifyAuthToken(token: String) = viewModelScope.launch(Dispatchers.IO) {
        authenticationToken.postValue(Resource.Loading())
        val tokenResponse = authRepository.verifyUserMail(token)
        authenticationToken.postValue(handleToken(tokenResponse))
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
