package com.bccowo.naiz.core.di

import com.bccowo.naiz.presentation.detail_candi.DetailCandiViewModel
import com.bccowo.naiz.presentation.detail_candi.nearest_candi.NearestCandiViewModel
import com.bccowo.naiz.presentation.home.bookmark.BookmarkViewModel
import com.bccowo.naiz.presentation.home.candilist.CandiListViewModel
import com.bccowo.naiz.presentation.home.home.HomeViewModel
import com.bccowo.naiz.presentation.home.quiz.QuizViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { QuizViewModel(get()) }
    viewModel { CandiListViewModel(get()) }
    viewModel { BookmarkViewModel(get()) }
    viewModel { DetailCandiViewModel(get()) }
    viewModel { NearestCandiViewModel(get()) }
}