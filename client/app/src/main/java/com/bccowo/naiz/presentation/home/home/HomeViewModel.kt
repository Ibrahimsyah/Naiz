package com.bccowo.naiz.presentation.home.home

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bccowo.naiz.core.config.SharedPreference
import com.bccowo.naiz.domain.model.Candi
import com.bccowo.naiz.domain.usecase.CandiUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val candiUseCase: CandiUseCase, private val pref: SharedPreferences) :
    ViewModel() {
    private val accessToken = pref.getString(SharedPreference.PREF_USER_TOKEN, "") as String
    private val userId = pref.getInt(SharedPreference.PREF_USER_ID, 0)

    val userName get() = pref.getString(SharedPreference.PREF_USER_NAME, "")
    val userPhoto get() = pref.getString(SharedPreference.PREF_USER_PHOTO, "")

    private val _popularCandiData: LiveData<List<Candi>> by lazy {
        val result = MutableLiveData<List<Candi>>()
        viewModelScope.launch {
            val candiList = candiUseCase.getAllCandi(accessToken)
            result.postValue(candiList)
        }
        result
    }


    fun getPopularCandi() = _popularCandiData

    fun getCandiProgress(): LiveData<List<Candi>> {
        return candiUseCase.getCandiProgress(userId, accessToken)
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