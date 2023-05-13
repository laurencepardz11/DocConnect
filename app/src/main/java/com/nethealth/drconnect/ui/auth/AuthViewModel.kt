package com.nethealth.drconnect.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nethealth.drconnect.data.remote.Resource
import com.nethealth.drconnect.repo.AuthRepository
import com.nethealth.drconnect.vo.Jwt
import com.nethealth.drconnect.vo.JwtBody
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val repo: AuthRepository) : ViewModel() {

    private val _loginResult = MutableStateFlow<Resource<Jwt>>(Resource.initial())
    val loginResult: StateFlow<Resource<Jwt>> = _loginResult

    fun getJwtToken(body: JwtBody) = viewModelScope.launch {
        _loginResult.value = Resource.loading()
        repo.getJwtToken(body).collect {
            _loginResult.value = it
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

}


