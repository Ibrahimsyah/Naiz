package com.bccowo.naiz.presentation.quiz

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.bccowo.naiz.core.config.SharedPreference
import com.bccowo.naiz.domain.model.QuizQuestion
import com.bccowo.naiz.domain.usecase.QuizUseCase
import kotlinx.coroutines.launch
import java.lang.Exception

class QuizEventViewModel(private val quizUseCase: QuizUseCase, pref: SharedPreferences) :
    ViewModel() {
    private val accessToken = pref.getString(SharedPreference.PREF_USER_TOKEN, "") as String

    fun getQuestions(quizId: Int): LiveData<List<QuizQuestion>> {
        return liveData {
            emit(quizUseCase.getQuizQuestion(accessToken, quizId))
        }
    }

    fun postResult(quizId: Int, score: Int){
        viewModelScope.launch {
            try{
                quizUseCase.submitQuizScore(quizId, score, accessToken)
            }catch (e: Exception){ }
        }
    }
}