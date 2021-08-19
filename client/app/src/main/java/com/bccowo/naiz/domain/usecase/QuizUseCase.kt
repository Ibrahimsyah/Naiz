package com.bccowo.naiz.domain.usecase

import androidx.lifecycle.LiveData
import com.bccowo.naiz.domain.model.Quiz

interface QuizUseCase {
    fun getQuiz() : LiveData<List<Quiz>>
}