package com.bccowo.naiz.core.util

import android.graphics.Bitmap
import android.view.View
import java.io.File

object Extension {
    fun View.visible() {
        this.visibility = View.VISIBLE
    }

    fun View.gone() {
        this.visibility = View.GONE
    }

    fun File.writeBitmap(bitmap: Bitmap, format: Bitmap.CompressFormat, quality: Int) {
        outputStream().use { out ->
            bitmap.compress(format, quality, out)
            out.flush()
        }
    }
}