package com.example.nocolor

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect

object PixelUtils {

    fun pixelate(bitmap: Bitmap, pixelSize: Int, colorCount: Int): Bitmap {
        val width = bitmap.width
        val height = bitmap.height

        // Sửa lỗi không null
        val result = Bitmap.createBitmap(width, height, bitmap.config ?: Bitmap.Config.ARGB_8888)
        val canvas = Canvas(result)

        val paint = Paint()

        for (y in 0 until height step pixelSize) {
            for (x in 0 until width step pixelSize) {
                val pixelColor = bitmap.getPixel(x, y)
                val reducedColor = reduceColor(pixelColor, colorCount)
                paint.color = reducedColor
                canvas.drawRect(
                    Rect(x, y, (x + pixelSize).coerceAtMost(width), (y + pixelSize).coerceAtMost(height)),
                    paint
                )
            }
        }
        return result
    }

    private fun reduceColor(color: Int, levels: Int): Int {
        fun reduceComponent(component: Int): Int {
            val factor = 255 / (levels - 1)
            return (component / factor) * factor
        }

        val a = Color.alpha(color)
        val r = reduceComponent(Color.red(color))
        val g = reduceComponent(Color.green(color))
        val b = reduceComponent(Color.blue(color))

        return Color.argb(a, r, g, b)
    }
}