package com.bccowo.naiz.core.util

import android.util.Log
import com.bccowo.naiz.core.data.source.local.entities.CandiEntity
import com.bccowo.naiz.core.data.source.remote.response.CandiResponse
import com.bccowo.naiz.core.data.source.remote.response.QuizQuestionResponse
import com.bccowo.naiz.core.data.source.remote.response.QuizResponse
import com.bccowo.naiz.domain.model.Candi
import com.bccowo.naiz.domain.model.Quiz
import com.bccowo.naiz.domain.model.QuizOptions
import com.bccowo.naiz.domain.model.QuizQuestion

object Mapper {
    fun candiModelToEntity(candi: Candi): CandiEntity {
        return CandiEntity(
            candi.id,
            candi.name,
            candi.address,
            candi.description,
            candi.image,
            candi.rating,
            candi.ratingCount,
            candi.longitude,
            candi.latitude
        )
    }

    fun candiEntityListToModel(candiList: List<CandiEntity>): List<Candi> {
        return candiList.map {
            Candi(
                it.id,
                it.name,
                it.address,
                it.description,
                it.image,
                it.rating,
                it.ratingCount,
                it.longitude,
                it.latitude
            )
        }
    }

    fun candiResponseToModel(candiResponse: CandiResponse): Candi {
        val rateCount = candiResponse.rating.size
        val rateAverage =
            candiResponse.rating.fold(
                0.0,
                { acc, candiRating -> acc + candiRating.rate }) / rateCount
        return Candi(
            candiResponse.id,
            candiResponse.name,
            candiResponse.description,
            candiResponse.address,
            candiResponse.image,
            rateAverage,
            rateCount,
            candiResponse.longitude.toDouble(),
            candiResponse.latitude.toDouble()
        )
    }

    fun quizResponseToModel(quizResponse: QuizResponse): Quiz {
        return Quiz(
            quizResponse.id,
            quizResponse.level,
            quizResponse.description,
            quizResponse.title,
            "playable"
        )
    }

    fun quizQuestionsResponseToModel(quizQuestionResponse: QuizQuestionResponse): QuizQuestion {
        val choices = quizQuestionResponse.options.map {
            Log.d("hehe", "Mapper: $it")
            QuizOptions(it.option, it.isTrue)
        }

        return QuizQuestion(
            quizQuestionResponse.id,
            quizQuestionResponse.question,
            quizQuestionResponse.image,
            choices
        )
    }
}