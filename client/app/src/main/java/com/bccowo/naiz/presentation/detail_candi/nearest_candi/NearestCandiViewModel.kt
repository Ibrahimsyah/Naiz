package com.bccowo.naiz.presentation.detail_candi.nearest_candi

import androidx.lifecycle.ViewModel
import com.bccowo.naiz.domain.usecase.CandiUseCase

class NearestCandiViewModel(private val candiUseCase: CandiUseCase) : ViewModel() {
    fun getCandiList() = candiUseCase.getAllCandi()
}