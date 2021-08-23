package com.bccowo.naiz.domain.usecase

import com.bccowo.naiz.domain.model.Quiz
import com.bccowo.naiz.domain.model.QuizQuestion
import com.bccowo.naiz.domain.repository.INaizRepository

class QuizInteractor(private val naizRepository: INaizRepository) : QuizUseCase {
    override suspend fun getQuiz(accessToken: String): List<Quiz> {
        return naizRepository.getAllQuiz(accessToken)
    }

    override fun getQuizQuestion(quizId: Int): List<QuizQuestion> {
        return listOf(
            QuizQuestion(
                1,
                "Question 1",
                "https://s3.theasianparent.com/tap-assets-prod/wp-content/uploads/sites/24/2021/07/arca-4-768x380.jpg",
                listOf("Option 1", "Option 2", "Option 3", "Option 4"),
                2
            ),
            QuizQuestion(
                2,
                "Question 2",
                null,
                listOf("Option 1", "Option 2", "Option 3", "Option 4"),
                0
            ),
            QuizQuestion(
                3,
                "Question 3",
                null,
                listOf("Option 1", "Option 2", "Option 3", "Option 4"),
                1
            ),
            QuizQuestion(
                4,
                "Question 4",
                "https://s3.theasianparent.com/tap-assets-prod/wp-content/uploads/sites/24/2021/07/arca-4-768x380.jpg",
                listOf("Option 1", "Option 2", "Option 3", "Option 4"),
                3
            )
        )
    }

}