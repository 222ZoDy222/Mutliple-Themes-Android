package com.zdy.testtheme.themes

import android.content.Context
import android.content.res.Resources.Theme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object ThemeHelper {

    private const val SELECTED_THEME = "selected_theme"

    private val mCurrentTheme = MutableStateFlow<ThemesEnum?>(null)
    val currentTheme: StateFlow<ThemesEnum?> = mCurrentTheme


    fun applyTheme(context: Context, theme: ThemesEnum) {
        saveTheme(context, theme)
        context.setTheme(theme.resID)
    }

    private fun saveTheme(context: Context, theme: ThemesEnum) {
        mCurrentTheme.value = theme
        val prefs = context.getSharedPreferences(SELECTED_THEME, Context.MODE_PRIVATE)
        prefs.edit().putInt(SELECTED_THEME, theme.ordinal).apply()
    }

    fun getSavedTheme(context: Context): ThemesEnum {
        if (mCurrentTheme.value == null) {
            val prefs = context.getSharedPreferences(SELECTED_THEME, Context.MODE_PRIVATE)
            val savedThemeOrdinal = prefs.getInt(SELECTED_THEME, ThemesEnum.light.ordinal)
            mCurrentTheme.value = ThemesEnum.entries.getOrNull(savedThemeOrdinal) ?: ThemesEnum.light
        }
        return mCurrentTheme.value ?: ThemesEnum.light
    }
}