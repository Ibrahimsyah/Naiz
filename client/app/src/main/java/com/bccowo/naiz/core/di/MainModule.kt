package com.bccowo.naiz.core.di

import com.bccowo.naiz.core.data.NaizRepository
import com.bccowo.naiz.core.data.source.local.LocalDataSource
import com.bccowo.naiz.core.data.source.remote.RemoteDataSource
import com.bccowo.naiz.domain.repository.INaizRepository
import com.bccowo.naiz.domain.usecase.*
import org.koin.dsl.module

val mainModule = module {
    single<INaizRepository> { NaizRepository(get(), get()) }
    single<CandiUseCase> { CandiInteractor(get()) }
    single<QuizUseCase> { QuizInteractor(get()) }
    single<UserUseCase> { UserInteractor(get()) }
}