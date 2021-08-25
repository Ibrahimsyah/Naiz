package com.bccowo.naiz.core.di

import com.bccowo.naiz.core.data.NaizRepository
import com.bccowo.naiz.domain.repository.INaizRepository
import com.bccowo.naiz.domain.usecase.*
import org.koin.dsl.module

val mainModule = module {
    single<INaizRepository> { NaizRepository(get(), get()) }
    single<CandiUseCase> { CandiInteractor(get()) }
    single<QuizUseCase> { QuizInteractor(get()) }
    single<UserUseCase> { UserInteractor(get()) }
    single<DetectorUseCase> { DetectorInteractor(get()) }
    single<ReliefUseCase> { ReliefInteractor(get()) }
}