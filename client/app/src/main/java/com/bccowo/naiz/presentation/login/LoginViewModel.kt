package com.bccowo.naiz.presentation.login

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bccowo.naiz.core.config.SharedPreference
import com.bccowo.naiz.core.data.source.remote.request.LoginRequest
import com.bccowo.naiz.core.data.source.remote.response.LoginData
import com.bccowo.naiz.domain.usecase.UserUseCase
import kotlinx.coroutines.launch

class LoginViewModel(private val userUseCase: UserUseCase, private val prefs: SharedPreferences) :
    ViewModel() {
    private val _loading = MutableLiveData(false)
    private val _status = MutableLiveData(false)

    val loading get() = _loading
    val status get() = _status

    fun loginUser(loginRequest: LoginRequest) {
        _status.postValue(null)
        _loading.postValue(true)
        viewModelScope.launch {
            _loading.postValue(true)
            try {
                val response = userUseCase.loginUser(loginRequest)
                if (response.success) {
                    val data = response.data as LoginData
                    prefs.edit().apply {
                        putString(SharedPreference.PREF_USER_TOKEN, data.token)
                        putInt(SharedPreference.PREF_USER_ID, data.id)
                        putString(SharedPreference.PREF_USER_NAME, data.name)
                        putString(SharedPreference.PREF_USER_EMAIL, data.email)
                        apply()
                    }
                }
                _status.postValue(true)
            } catch (e: Exception) {
                _status.postValue(false)
            }
            _loading.postValue(false)


        }
    }
}