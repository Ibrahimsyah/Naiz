package com.bccowo.naiz.presentation.home.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bccowo.naiz.domain.model.Candi
import com.bccowo.naiz.domain.model.CandiProgress
import com.bccowo.naiz.domain.usecase.CandiUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val candiUseCase: CandiUseCase) : ViewModel() {
    private val popularCandiData: LiveData<List<Candi>> by lazy {
        candiUseCase.getPopularCandi()
    }

    private val candiProgressData: LiveData<List<CandiProgress>> by lazy {
        candiUseCase.getCandiProgress()
    }

    fun getPopularCandi(): LiveData<List<Candi>> {
        return popularCandiData
    }

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