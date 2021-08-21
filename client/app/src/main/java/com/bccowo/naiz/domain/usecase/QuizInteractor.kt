package com.bccowo.naiz.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.bccowo.naiz.domain.model.Quiz
import com.bccowo.naiz.domain.model.QuizQuestion
import com.bccowo.naiz.domain.repository.INaizRepository

class QuizInteractor(private val naizRepository: INaizRepository) : QuizUseCase {
    override fun getQuiz(): LiveData<List<Quiz>> {
        return liveData {
            emit(
                listOf(
                    Quiz(0, 1, "Description Quiz 1", "Quiz 1", "complete"),
                    Quiz(0, 2, "Description Quiz 2", "Quiz 2", "playable"),
                    Quiz(0, 3, "Description Quiz 3", "Quiz 3", "locked"),
                    Quiz(0, 4, "Description Quiz 4", "Quiz 4", "playable"),
                    Quiz(0, 5, "Description Quiz 5", "Quiz 5", "complete"),
                    Quiz(0, 6, "Description Quiz 6", "Quiz 6", "locked"),
                    Quiz(0, 7, "Description Quiz 7", "Quiz 7", "playable"),
                )
            )
        }
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