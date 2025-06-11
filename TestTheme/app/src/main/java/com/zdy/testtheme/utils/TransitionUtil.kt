package com.zdy.testtheme.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Point
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import com.zdy.testtheme.TransitionActivity
import com.zdy.testtheme.themes.ThemeHelper
import com.zdy.testtheme.themes.ThemesEnum

object TransitionUtil {
    var transitionBitmap: Bitmap? = null



    fun switchToTheme(context: Context, theme: ThemesEnum, centerPoint: Point) {
        context.startActivity(TransitionActivity.getIntent(context, theme, centerPoint))
    }

    fun View.takeScreenshot(): Bitmap {
        val bitmap = Bitmap.createBitmap(this.width, this.height, Bitmap.Config.RGB_565)
        val canvas = Canvas(bitmap)
        this.draw(canvas)
        return bitmap
    }

    fun View.centerOnScreen(): Point {
        if (width == 0 || height == 0) {
            return Point(0,0)
        }
        val location = IntArray(2)
        getLocationOnScreen(location)
        return Point(location[0] + width / 2, location[1] + height / 2)
    }

}