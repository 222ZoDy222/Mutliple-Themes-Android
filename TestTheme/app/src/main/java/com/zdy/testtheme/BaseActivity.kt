package com.zdy.testtheme

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.zdy.testtheme.themes.ThemeHelper
import com.zdy.testtheme.themes.ThemesEnum
import kotlinx.coroutines.launch

open class BaseActivity : AppCompatActivity() {

    private var currentTheme : ThemesEnum = ThemesEnum.light

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currentTheme = ThemeHelper.getSavedTheme(this)
        setTheme(currentTheme.resID)
        lifecycleScope.launch {
            ThemeHelper.currentTheme.collect { theme ->
                if(theme != currentTheme){
                    Log.i(App.TAG, "Must change theme")
                    recreate()
                } else{
                    Log.i(App.TAG, "Current theme is fine")
                }
            }
        }

    }


}