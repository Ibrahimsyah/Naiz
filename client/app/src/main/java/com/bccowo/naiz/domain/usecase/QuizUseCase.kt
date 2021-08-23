package com.bccowo.naiz.domain.usecase

import com.bccowo.naiz.domain.model.Quiz
import com.bccowo.naiz.domain.model.QuizQuestion

interface QuizUseCase {
    suspend fun getQuiz(accessToken: String): List<Quiz>
    fun getQuizQuestion(quizId: Int): List<QuizQuestion>
}