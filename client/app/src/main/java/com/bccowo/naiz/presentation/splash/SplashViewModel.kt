package com.bccowo.naiz.presentation.splash

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bccowo.naiz.core.config.SharedPreference
import com.bccowo.naiz.domain.usecase.UserUseCase
import kotlinx.coroutines.launch

class SplashViewModel(private val userUseCase: UserUseCase, private val pref: SharedPreferences) :
    ViewModel() {
    fun checkCredentials(): LiveData<Boolean> {
        val result = MutableLiveData<Boolean>(null)
        try {
            val token = pref.getString(SharedPreference.PREF_USER_TOKEN, "") as String
            viewModelScope.launch {
                userUseCase.checkCredentials(token)
            }
            result.postValue(true)
        } catch (e: Exception) {
            result.postValue(false)
        }
        return result
    }
}