package com.bccowo.naiz.presentation.detector

import android.content.SharedPreferences
import android.graphics.Bitmap
import androidx.lifecycle.*
import com.bccowo.naiz.core.config.SharedPreference
import com.bccowo.naiz.domain.model.Candi
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
    private val _detectionResult = MutableLiveData<Boolean>(null)
    private val _loading = MutableLiveData(false)
    val detectionResult get() = _detectionResult
    val loading get() = _loading

    fun getCandiData(): LiveData<List<Candi>> {
        return liveData {
            emit(candiUseCase.getAllCandi(accessToken))
        }
    }

    fun detectImage(bitmap: Bitmap) {
        _loading.postValue(true)
        viewModelScope.launch {
            try {
                detectorUseCase.detectImage(bitmap, "")
                delay(2000)
                _detectionResult.postValue(true)
            } catch (e: Exception) {
                _detectionResult.postValue(true)
            } finally {
                _loading.postValue(false)
            }
        }
    }
}