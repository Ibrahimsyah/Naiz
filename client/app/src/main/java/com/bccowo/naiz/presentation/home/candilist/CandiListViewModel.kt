package com.bccowo.naiz.presentation.home.candilist

import androidx.lifecycle.*
import com.bccowo.naiz.domain.model.Candi
import com.bccowo.naiz.domain.usecase.CandiUseCase
import kotlinx.coroutines.launch

class CandiListViewModel(private val candiUseCase: CandiUseCase): ViewModel() {
    private val candiData: MutableLiveData<List<Candi>> by lazy {
        val result = MutableLiveData<List<Candi>>()
        candiUseCase.getAllCandi().map { result.postValue(it) }
        result
    }

    private val _loading = MutableLiveData(false)
    val loading get() = _loading

    fun getCandiList(): LiveData<List<Candi>> {
        return candiData
    }

    fun searchCandi(query: String) {
        candiData.postValue(listOf())
        loading.postValue(true)
        viewModelScope.launch {
            loading.postValue(false)
        }
    }
}