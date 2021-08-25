package com.bccowo.naiz.core.util

import com.bccowo.naiz.core.data.source.local.entities.CandiEntity
import com.bccowo.naiz.core.data.source.remote.response.CandiResponse
import com.bccowo.naiz.core.data.source.remote.response.QuizQuestionResponse
import com.bccowo.naiz.core.data.source.remote.response.QuizResponse
import com.bccowo.naiz.domain.model.*

object Mapper {
    fun candiModelToEntity(candi: Candi): CandiEntity {
        val relief = candi.reliefs.map {
            com.bccowo.naiz.core.data.source.local.entities.Relief(
                it.id, it.name, it.description, it.image
            )
        }
        return CandiEntity(
            candi.id,
            candi.name,
            candi.address,
            candi.description,
            candi.image,
            candi.rating,
            candi.ratingCount,
            candi.longitude,
            candi.latitude,
            relief,
            candi.totalReliefs
        )
    }

    fun candiEntityListToModel(candiList: List<CandiEntity>): List<Candi> {
        return candiList.map {
            val reliefs = it.reliefs.map { ot -> Relief(ot.id, ot.name, ot.description, ot.image) }
            Candi(
                it.id,
                it.name,
                it.address,
                it.description,
                it.image,
                it.rating,
                it.ratingCount,
                it.longitude,
                it.latitude,
                reliefs,
                it.totalRelief
            )
        }
    }

    fun candiResponseToModel(candiResponse: CandiResponse): Candi {
        val rateCount = candiResponse.rating.size
        val rateAverage = if (rateCount != 0) {
            candiResponse.rating.fold(
                0.0,
                { acc, candiRating -> acc + candiRating.rate }) / rateCount
        } else 0.0

        val reliefList = candiResponse.reliefs.map {
            Relief(
                it.id, it.detail.name, it.detail.description, it.detail.image
            )
        }

        return Candi(
            candiResponse.id,
            candiResponse.name,
            candiResponse.description,
            candiResponse.address,
            candiResponse.image,
            rateAverage,
            rateCount,
            candiResponse.longitude.toDouble(),
            candiResponse.latitude.toDouble(),
            reliefList,
            candiResponse.total_reliefs
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
            QuizOptions(it.option, it.isTrue)
        }

        return QuizQuestion(
            quizQuestionResponse.id,
            quizQuestionResponse.question,
            quizQuestionResponse.image,
            choices
        )
    }

    fun similarReliefResponseToModel(reliefResponse: com.bccowo.naiz.core.data.source.remote.response.Relief): Relief {
        return Relief(
            reliefResponse.id,
            reliefResponse.name,
            reliefResponse.description,
            reliefResponse.image
        )
    }
}