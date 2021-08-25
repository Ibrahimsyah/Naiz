package com.bccowo.naiz.presentation.detail_candi.ornament

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.bccowo.naiz.core.config.SharedPreference
import com.bccowo.naiz.domain.usecase.ReliefUseCase

class OrnamentViewModel(private val reliefUseCase: ReliefUseCase, pref: SharedPreferences) :
    ViewModel() {
    private val accessToken = pref.getString(SharedPreference.PREF_USER_TOKEN, "") as String

    fun getCandiRelief(candiId: Int) = liveData {
        emit(reliefUseCase.getCandiRelief(candiId, accessToken))
    }
}