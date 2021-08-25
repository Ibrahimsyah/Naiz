package com.bccowo.naiz.core.ui.other_ornament

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.bccowo.naiz.core.config.SharedPreference
import com.bccowo.naiz.domain.model.Relief
import com.bccowo.naiz.domain.usecase.ReliefUseCase

class OtherOrnamentViewModel(private val reliefUseCase: ReliefUseCase, pref: SharedPreferences) :
    ViewModel() {
    private val accessToken = pref.getString(SharedPreference.PREF_USER_TOKEN, "") as String

    fun getOtherRelief(candiId: Int) = liveData {
        emit(reliefUseCase.getOtherRelief(candiId, accessToken))
    }
}