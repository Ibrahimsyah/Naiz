package com.bccowo.naiz.presentation.detail_ornament

import androidx.lifecycle.ViewModel
import com.bccowo.naiz.domain.usecase.CandiUseCase

class DetailOrnamentViewModel(private val candiUseCase: CandiUseCase): ViewModel() {
    fun getDetailOrnament(ornamentId: Int) = candiUseCase.getOrnamentDetail(ornamentId)
}