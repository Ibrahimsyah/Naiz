package com.bccowo.naiz.presentation.detail_ornament.similar_ornament

import androidx.lifecycle.ViewModel
import com.bccowo.naiz.domain.usecase.CandiUseCase

class SimilarOrnamentViewModel(private val candiUseCase: CandiUseCase) : ViewModel() {
    fun getSimilarOrnament(ornamentId: Int) = candiUseCase.getSimilarOrnament(0)
}