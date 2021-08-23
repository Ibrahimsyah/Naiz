package com.bccowo.naiz.domain.usecase

import com.bccowo.naiz.domain.model.Quiz
import com.bccowo.naiz.domain.model.QuizQuestion
import com.bccowo.naiz.domain.repository.INaizRepository

class QuizInteractor(private val naizRepository: INaizRepository) : QuizUseCase {
    override suspend fun getQuiz(accessToken: String): List<Quiz> {
        return naizRepository.getAllQuiz(accessToken)
    }

    override suspend fun getQuizQuestion(accessToken: String, quizId: Int): List<QuizQuestion> {
        return naizRepository.getQuizQuestions(accessToken, quizId)
    }
}