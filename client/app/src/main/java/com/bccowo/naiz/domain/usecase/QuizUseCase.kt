package com.bccowo.naiz.domain.usecase

import androidx.lifecycle.LiveData
import com.bccowo.naiz.domain.model.Quiz
import com.bccowo.naiz.domain.model.QuizQuestion

interface QuizUseCase {
    fun getQuiz(): LiveData<List<Quiz>>
    fun getQuizQuestion(quizId: Int): List<QuizQuestion>
}