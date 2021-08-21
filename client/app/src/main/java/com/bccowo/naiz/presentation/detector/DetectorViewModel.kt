package com.bccowo.naiz.presentation.detector

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bccowo.naiz.domain.usecase.DetectorUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DetectorViewModel(private val detectorUseCase: DetectorUseCase) : ViewModel() {
    fun detectImage(bitmap: Bitmap) : LiveData<Boolean>{
        val result = MutableLiveData(false)
        viewModelScope.launch {
            detectorUseCase.detectImage(bitmap, "")
            delay(2000)
            result.postValue(true)
        }
        return result
    }
}