package com.bccowo.naiz.presentation.detector

import android.content.SharedPreferences
import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.*
import com.bccowo.naiz.core.config.SharedPreference
import com.bccowo.naiz.domain.model.Candi
import com.bccowo.naiz.domain.model.DetectionResult
import com.bccowo.naiz.domain.usecase.CandiUseCase
import com.bccowo.naiz.domain.usecase.DetectorUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DetectorViewModel(
    private val detectorUseCase: DetectorUseCase,
    private val candiUseCase: CandiUseCase,
    pref: SharedPreferences
) : ViewModel() {
    private val accessToken = pref.getString(SharedPreference.PREF_USER_TOKEN, "") as String
    private val _detectionResult = MutableLiveData<DetectionResult>(null)
    private val _loading = MutableLiveData(false)
    val detectionResult get() = _detectionResult
    val loading get() = _loading

    fun getCandiData(): LiveData<List<Candi>> {
        return liveData {
            emit(candiUseCase.getAllCandi(accessToken))
        }
    }

    fun detectImage(imagePath: String) {
        _loading.postValue(true)
        viewModelScope.launch {
            try {
                val result = detectorUseCase.detectImage(imagePath)
                _detectionResult.postValue(result)
            } catch (e: Exception) {
            } finally {
                _loading.postValue(false)
            }
        }
    }
}