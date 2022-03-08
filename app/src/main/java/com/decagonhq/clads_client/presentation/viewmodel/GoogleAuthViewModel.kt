package com.decagonhq.clads_client.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.decagonhq.clads_client.data.model.Role
import com.decagonhq.clads_client.data.repository.AuthRepository
import com.decagonhq.clads_client.presentation.model.GenericResult
import com.decagonhq.clads_client.presentation.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoogleAuthViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {
    private var _loginResponse = MutableLiveData<Resource<GenericResult<String>>>()
    val loginResponse: LiveData<Resource<GenericResult<String>>> get() = _loginResponse

    fun loginUserWithGoogle(bearer: String, role: Role) {
        _loginResponse.value = Resource.Loading()
        viewModelScope.launch {
            _loginResponse.value = authRepository.googleUser(bearer, role)
        }
    }
}
