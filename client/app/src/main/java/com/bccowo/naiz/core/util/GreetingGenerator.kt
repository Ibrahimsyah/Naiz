package com.bccowo.naiz.core.util

import android.content.Context
import com.bccowo.naiz.R
import java.util.*

object GreetingGenerator {
    fun generateGreeting(context: Context): String {
        val calendar = Calendar.getInstance()
        val greeting = when (calendar.get(Calendar.HOUR_OF_DAY)) {
            in 0..10 -> R.string.selamat_pagi
            in 11..16 -> R.string.selamat_siang
            else -> R.string.selamat_malam
        }
        return context.getString(greeting)
    }
}