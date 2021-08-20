package com.bccowo.naiz.core.di

import com.bccowo.naiz.presentation.detail_candi.DetailCandiViewModel
import com.bccowo.naiz.presentation.detail_candi.nearest_candi.NearestCandiViewModel
import com.bccowo.naiz.presentation.detail_candi.ornament.OrnamentViewModel
import com.bccowo.naiz.presentation.detail_ornament.DetailOrnamentViewModel
import com.bccowo.naiz.presentation.detail_ornament.similar_ornament.SimilarOrnamentViewModel
import com.bccowo.naiz.presentation.home.bookmark.BookmarkViewModel
import com.bccowo.naiz.presentation.home.candilist.CandiListViewModel
import com.bccowo.naiz.presentation.home.home.HomeViewModel
import com.bccowo.naiz.presentation.home.quiz.QuizViewModel
import com.bccowo.naiz.presentation.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { QuizViewModel(get()) }
    viewModel { CandiListViewModel(get()) }
    viewModel { BookmarkViewModel(get()) }
    viewModel { DetailCandiViewModel(get()) }
    viewModel { NearestCandiViewModel(get()) }
    viewModel { OrnamentViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { DetailOrnamentViewModel(get()) }
    viewModel { SimilarOrnamentViewModel(get()) }
}