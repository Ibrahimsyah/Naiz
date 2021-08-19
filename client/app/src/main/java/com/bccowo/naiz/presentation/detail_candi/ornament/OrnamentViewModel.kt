package com.bccowo.naiz.presentation.detail_candi.ornament

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bccowo.naiz.domain.model.Ornament
import com.bccowo.naiz.domain.usecase.CandiUseCase

class OrnamentViewModel(private val candiUseCase: CandiUseCase) : ViewModel() {

    private val _loading = MutableLiveData(false)
    val loading get() = _loading

    fun getOrnamentList(candiId : Int): LiveData<List<Ornament>> {
        return candiUseCase.getCandiOrnaments(candiId)
    }
}