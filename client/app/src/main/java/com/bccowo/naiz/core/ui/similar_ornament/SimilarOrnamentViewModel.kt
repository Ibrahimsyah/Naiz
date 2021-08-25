package com.bccowo.naiz.core.ui.similar_ornament

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.bccowo.naiz.core.config.SharedPreference
import com.bccowo.naiz.domain.model.Relief
import com.bccowo.naiz.domain.usecase.ReliefUseCase
import kotlinx.coroutines.Dispatchers

class SimilarOrnamentViewModel(private val reliefUseCase: ReliefUseCase, pref: SharedPreferences) :
    ViewModel() {
    private val accessToken = pref.getString(SharedPreference.PREF_USER_TOKEN, "") as String

    fun getSimilarOrnament(reliefName: String): LiveData<List<Relief>> {
        return liveData(Dispatchers.IO) {
            emit(reliefUseCase.getSimilarRelief(reliefName, accessToken))
        }
    }
}