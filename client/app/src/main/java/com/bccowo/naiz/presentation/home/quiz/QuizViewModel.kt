package com.bccowo.naiz.presentation.home.quiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bccowo.naiz.domain.model.Quiz
import com.bccowo.naiz.domain.usecase.QuizUseCase

class QuizViewModel(private val quizUseCase: QuizUseCase) : ViewModel(){
    private val quizList: LiveData<List<Quiz>> by lazy {
        quizUseCase.getQuiz()
    }

    fun getQuiz(): LiveData<List<Quiz>> {
        return quizList
    }
}