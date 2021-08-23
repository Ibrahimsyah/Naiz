package com.bccowo.naiz.domain.usecase

import com.bccowo.naiz.domain.model.Quiz
import com.bccowo.naiz.domain.model.QuizQuestion

interface QuizUseCase {
    suspend fun getQuiz(accessToken: String): List<Quiz>
    suspend fun getQuizQuestion(accessToken: String, quizId: Int): List<QuizQuestion>
}