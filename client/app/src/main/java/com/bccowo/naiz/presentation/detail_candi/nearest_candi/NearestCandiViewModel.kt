package com.bccowo.naiz.presentation.detail_candi.nearest_candi

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.bccowo.naiz.core.config.SharedPreference
import com.bccowo.naiz.domain.model.Candi
import com.bccowo.naiz.domain.usecase.CandiUseCase

class NearestCandiViewModel(private val candiUseCase: CandiUseCase, pref: SharedPreferences) : ViewModel() {
    private val accessToken = pref.getString(SharedPreference.PREF_USER_TOKEN, "") as String

    fun getCandiList(candiId: Int): LiveData<List<Candi>> {
        return liveData {
            emit(candiUseCase.getRelatedCandi(candiId, accessToken))
        }
    }
}