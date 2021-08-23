package com.bccowo.naiz.presentation.home.quiz

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.bccowo.naiz.core.config.SharedPreference
import com.bccowo.naiz.domain.model.Quiz
import com.bccowo.naiz.domain.usecase.QuizUseCase

class QuizViewModel(private val quizUseCase: QuizUseCase, pref: SharedPreferences) : ViewModel() {
    private val accessToken = pref.getString(SharedPreference.PREF_USER_TOKEN, "") as String
    private val quizList: LiveData<List<Quiz>> by lazy {
        liveData {
            emit(quizUseCase.getQuiz(accessToken))
        }
    }

    fun getQuiz(): LiveData<List<Quiz>> {
        return quizList
    }
}