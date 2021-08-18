package com.bccowo.naiz.core.di

import com.bccowo.naiz.presentation.home.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
}