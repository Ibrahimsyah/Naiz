package com.bccowo.naiz.core.util

import android.view.View

object Extension {
    fun View.visible() {
        this.visibility = View.VISIBLE
    }

    fun View.gone() {
        this.visibility = View.GONE
    }
}