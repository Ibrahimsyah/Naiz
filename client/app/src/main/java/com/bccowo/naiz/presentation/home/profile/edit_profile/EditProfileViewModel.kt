package com.bccowo.naiz.presentation.home.profile.edit_profile

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.bccowo.naiz.core.config.SharedPreference
import com.bccowo.naiz.domain.usecase.UserUseCase

class EditProfileViewModel(
    private val pref: SharedPreferences
) : ViewModel() {
    val userName get() = pref.getString(SharedPreference.PREF_USER_NAME, "")
    val userPhoto get() = pref.getString(SharedPreference.PREF_USER_PHOTO, "")
    val userEmail get() = pref.getString(SharedPreference.PREF_USER_EMAIL, "")
    val userPhone get() = pref.getString(SharedPreference.PREF_USER_PHONE, "")
}