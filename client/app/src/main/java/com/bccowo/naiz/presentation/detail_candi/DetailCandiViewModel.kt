package com.bccowo.naiz.presentation.detail_candi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bccowo.naiz.domain.model.Candi
import com.bccowo.naiz.domain.usecase.CandiUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailCandiViewModel(private val candiUseCase: CandiUseCase) :
    ViewModel() {

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