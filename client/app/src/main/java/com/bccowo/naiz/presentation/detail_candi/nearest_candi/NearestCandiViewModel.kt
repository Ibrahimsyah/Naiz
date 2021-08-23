package com.bccowo.naiz.presentation.detail_candi.nearest_candi

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bccowo.naiz.core.config.SharedPreference
import com.bccowo.naiz.domain.model.Candi
import com.bccowo.naiz.domain.usecase.CandiUseCase
import kotlinx.coroutines.launch

class NearestCandiViewModel(private val candiUseCase: CandiUseCase, pref: SharedPreferences) : ViewModel() {
    private val accessToken = pref.getString(SharedPreference.PREF_USER_TOKEN, "") as String

    private val candiData : LiveData<List<Candi>> by lazy {
        val result = MutableLiveData<List<Candi>>()
        viewModelScope.launch {
            val candiList = candiUseCase.getAllCandi(accessToken)
            result.postValue(candiList)
        }
        result
    }
    fun getCandiList() = candiData
}