package com.bccowo.naiz.core.di

import com.bccowo.naiz.core.data.NaizRepository
import com.bccowo.naiz.domain.repository.INaizRepository
import com.bccowo.naiz.domain.usecase.CandiInteractor
import com.bccowo.naiz.domain.usecase.CandiUseCase
import com.bccowo.naiz.domain.usecase.QuizInteractor
import com.bccowo.naiz.domain.usecase.QuizUseCase
import org.koin.dsl.module

val mainModule = module {
    single<INaizRepository> { NaizRepository() }
    single<CandiUseCase> { CandiInteractor(get()) }
    single<QuizUseCase> { QuizInteractor(get()) }
}