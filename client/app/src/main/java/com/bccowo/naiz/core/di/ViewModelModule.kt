package com.bccowo.naiz.core.di

import com.bccowo.naiz.presentation.detail_candi.DetailCandiViewModel
import com.bccowo.naiz.presentation.detail_candi.nearest_candi.NearestCandiViewModel
import com.bccowo.naiz.presentation.detail_candi.ornament.OrnamentViewModel
import com.bccowo.naiz.presentation.detail_ornament.DetailOrnamentViewModel
import com.bccowo.naiz.presentation.detail_ornament.similar_ornament.SimilarOrnamentViewModel
import com.bccowo.naiz.presentation.detector.DetectorViewModel
import com.bccowo.naiz.presentation.home.bookmark.BookmarkViewModel
import com.bccowo.naiz.presentation.home.candilist.CandiListViewModel
import com.bccowo.naiz.presentation.home.home.HomeViewModel
import com.bccowo.naiz.presentation.home.profile.ProfileViewModel
import com.bccowo.naiz.presentation.home.profile.edit_profile.EditProfileViewModel
import com.bccowo.naiz.presentation.home.quiz.QuizViewModel
import com.bccowo.naiz.presentation.login.LoginViewModel
import com.bccowo.naiz.presentation.quiz.QuizEventViewModel
import com.bccowo.naiz.presentation.register.RegisterViewModel
import com.bccowo.naiz.presentation.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SplashViewModel(get(), get()) }
    viewModel { HomeViewModel(get(), get()) }
    viewModel { QuizViewModel(get(), get()) }
    viewModel { CandiListViewModel(get(), get()) }
    viewModel { BookmarkViewModel(get()) }
    viewModel { DetailCandiViewModel(get()) }
    viewModel { NearestCandiViewModel(get(), get()) }
    viewModel { OrnamentViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { LoginViewModel(get(), get()) }
    viewModel { DetailOrnamentViewModel(get()) }
    viewModel { SimilarOrnamentViewModel(get()) }
    viewModel { QuizEventViewModel(get()) }
    viewModel { DetectorViewModel(get()) }
    viewModel { ProfileViewModel(get(), get()) }
    viewModel { EditProfileViewModel(get(), get()) }
}