package com.bccowo.naiz.presentation.home.profile

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.bccowo.naiz.core.config.SharedPreference
import com.bccowo.naiz.domain.usecase.UserUseCase

class ProfileViewModel(val userUseCase: UserUseCase, private val pref: SharedPreferences): ViewModel() {
    val userName get() = pref.getString(SharedPreference.PREF_USER_NAME, "")
    val userPhoto get() = pref.getString(SharedPreference.PREF_USER_PHOTO, "")
}