package com.bccowo.naiz.presentation.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bccowo.naiz.core.data.source.remote.request.RegisterRequest
import com.bccowo.naiz.domain.usecase.UserUseCase
import kotlinx.coroutines.launch

class RegisterViewModel(private val userUseCase: UserUseCase) : ViewModel() {
    private val _loading = MutableLiveData(false)
    private val _status = MutableLiveData(false)

    val loading get() = _loading
    val status get() = _status

    fun registerUser(registerRequest: RegisterRequest) {
        _status.postValue(false)
        _loading.postValue(true)
        viewModelScope.launch {
            _loading.postValue(true)
            val response = userUseCase.registerUser(registerRequest)
            _status.postValue(response.success)
            _loading.postValue(false)
        }
    }
}