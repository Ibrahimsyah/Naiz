package com.bccowo.naiz.presentation.home.candilist

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bccowo.naiz.core.config.SharedPreference
import com.bccowo.naiz.domain.model.Candi
import com.bccowo.naiz.domain.usecase.CandiUseCase
import kotlinx.coroutines.launch

class CandiListViewModel(private val candiUseCase: CandiUseCase, pref: SharedPreferences): ViewModel() {
    private val accessToken = pref.getString(SharedPreference.PREF_USER_TOKEN, "") as String

    private val candiData: MutableLiveData<List<Candi>> by lazy {
        val result = MutableLiveData<List<Candi>>()
        viewModelScope.launch {
            val candiList = candiUseCase.getAllCandi(accessToken)
            result.postValue(candiList)
        }
        result
    }

    private val _loading = MutableLiveData(false)
    val loading get() = _loading

    fun getCandiList(): LiveData<List<Candi>> {
        return candiData
    }

    fun searchCandi(query: String) {
        loading.postValue(true)
        viewModelScope.launch {
            candiData.postValue(candiUseCase.searchCandi(accessToken, query))
            loading.postValue(false)
        }
    }
}