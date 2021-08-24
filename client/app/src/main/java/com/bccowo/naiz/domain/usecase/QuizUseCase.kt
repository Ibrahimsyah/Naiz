package com.bccowo.naiz.domain.usecase

import com.bccowo.naiz.core.data.source.remote.response.BasicResponse
import com.bccowo.naiz.domain.model.Quiz
import com.bccowo.naiz.domain.model.QuizQuestion

interface QuizUseCase {
    suspend fun getQuiz(accessToken: String): List<Quiz>
    suspend fun getQuizQuestion(accessToken: String, quizId: Int): List<QuizQuestion>
    suspend fun submitQuizScore(quizId: Int, score: Int, accessToken: String): BasicResponse
}