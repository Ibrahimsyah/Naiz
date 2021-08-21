package com.bccowo.naiz.presentation.home.home

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bccowo.naiz.core.config.SharedPreference
import com.bccowo.naiz.domain.model.Candi
import com.bccowo.naiz.domain.model.CandiProgress
import com.bccowo.naiz.domain.usecase.CandiUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val candiUseCase: CandiUseCase, pref: SharedPreferences) :
    ViewModel() {
    private val accessToken = pref.getString(SharedPreference.PREF_USER_TOKEN, "") as String

    private val _popularCandiData: LiveData<List<Candi>> by lazy {
        val result = MutableLiveData<List<Candi>>()
        viewModelScope.launch {
            val candiList = candiUseCase.getPopularCandi(accessToken)
            result.postValue(candiList)
        }
        result
    }

    private val candiProgressData: LiveData<List<CandiProgress>> by lazy {
        candiUseCase.getCandiProgress()
    }

    fun getPopularCandi() = _popularCandiData

    fun getCandiProgress(): LiveData<List<CandiProgress>> {
        return candiProgressData
    }

    fun checkCandiBookmarked(id: Int) = candiUseCase.checkCandiBookmarked(id)

    fun addCandiToBookmark(candi: Candi) {
        viewModelScope.launch(Dispatchers.IO) {
            candiUseCase.insertBookmark(candi)
        }
    }

    fun removeCandiFromBookmark(candi: Candi) {
        viewModelScope.launch(Dispatchers.IO) {
            candiUseCase.removeBookmark(candi)
        }
    }
}