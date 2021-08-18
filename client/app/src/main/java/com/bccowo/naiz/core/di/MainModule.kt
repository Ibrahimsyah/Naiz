package com.bccowo.naiz.core.di

import com.bccowo.naiz.core.data.NaizRepository
import com.bccowo.naiz.domain.repository.INaizRepository
import com.bccowo.naiz.domain.usecase.CandiInteractor
import com.bccowo.naiz.domain.usecase.CandiUseCase
import org.koin.dsl.module

val mainModule = module {
    single<CandiUseCase> { CandiInteractor(get()) }
    single<INaizRepository> { NaizRepository() }
}