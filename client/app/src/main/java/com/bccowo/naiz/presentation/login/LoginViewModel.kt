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
    private val userPhoto =
        "https://i.pinimg.com/736x/80/cd/66/80cd662b2d7c0ae90ff1de1be49b36aa.jpg"
    private val _loading = MutableLiveData(false)
    private val _status = MutableLiveData<Boolean?>(null)

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
                        putString(SharedPreference.PREF_USER_PHOTO, userPhoto)
                        putString(SharedPreference.PREF_USER_PHONE, data.phone)
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