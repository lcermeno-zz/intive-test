package com.qiubo.intive.misc

import android.app.Activity
import android.util.DisplayMetrics

object ScreenHelper {

    fun getScreenRealWidth(activity: Activity): Int {
        val displayMetrics = DisplayMetrics()
        setupMetrics(activity, displayMetrics)
        return displayMetrics.widthPixels
    }

    private fun setupMetrics(activity: Activity, displayMetrics: DisplayMetrics) {
        activity.windowManager.defaultDisplay.getRealMetrics(displayMetrics)
    }
}