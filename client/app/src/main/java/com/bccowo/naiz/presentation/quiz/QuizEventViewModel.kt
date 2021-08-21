package com.bccowo.naiz.presentation.quiz

import androidx.lifecycle.ViewModel
import com.bccowo.naiz.domain.usecase.QuizUseCase

class QuizEventViewModel(private val quizUseCase: QuizUseCase) : ViewModel() {

    fun getQuestions() = quizUseCase.getQuizQuestion(0)
}