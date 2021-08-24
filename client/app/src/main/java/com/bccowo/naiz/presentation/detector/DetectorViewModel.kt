package com.bccowo.naiz.presentation.detector

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.os.Environment
import androidx.lifecycle.*
import com.bccowo.naiz.core.config.SharedPreference
import com.bccowo.naiz.core.util.Extension.writeBitmap
import com.bccowo.naiz.domain.model.Candi
import com.bccowo.naiz.domain.model.DetectionResult
import com.bccowo.naiz.domain.usecase.CandiUseCase
import com.bccowo.naiz.domain.usecase.DetectorUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

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

    fun detectImage(context: Context, image: Bitmap) {
        _loading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            val bitmap = Bitmap.createScaledBitmap(image, image.width / 2, image.height / 2, false)
            val file = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "scan.png")
            file.writeBitmap(bitmap, Bitmap.CompressFormat.PNG, 85)
            try {
                val result = detectorUseCase.detectImage(file.absolutePath)
                _detectionResult.postValue(result)
            } catch (e: Exception) {
            } finally {
                _loading.postValue(false)
            }
        }
    }
}