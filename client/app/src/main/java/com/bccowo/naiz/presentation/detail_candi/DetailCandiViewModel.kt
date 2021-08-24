package com.bccowo.naiz.presentation.detail_candi

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.bccowo.naiz.core.config.SharedPreference
import com.bccowo.naiz.domain.model.Candi
import com.bccowo.naiz.domain.usecase.CandiUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailCandiViewModel(private val candiUseCase: CandiUseCase, pref: SharedPreferences) :
    ViewModel() {
    private val accessToken = pref.getString(SharedPreference.PREF_USER_TOKEN, "") as String

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

    fun submitCandiReview(candiId: Int, rate: Int): LiveData<Boolean> {
        return liveData {
            val result = candiUseCase.submitCandiReview(candiId, rate, accessToken)
            emit(result.success)
        }
    }
}