package com.bccowo.naiz.core.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import androidx.exifinterface.media.ExifInterface

object Image {
    fun createOptimizedImage(path: String): Bitmap {
        return BitmapFactory.decodeFile(path).run {
            val exif = ExifInterface(path)
            val orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1)
            val matrix = Matrix()
            when (orientation) {
                6 -> {
                    matrix.postRotate(90F)
                }
                3 -> {
                    matrix.postRotate(180F)
                }
                8 -> {
                    matrix.postRotate(270F)
                }
            }
            Bitmap.createBitmap(this, 0, 0, this.width, this.height, matrix, true)
        }
    }

    fun createScaledImage(bitmap: Bitmap): Bitmap {
        return Bitmap.createScaledBitmap(bitmap, bitmap.width / 4, bitmap.height / 4, false)
    }
}