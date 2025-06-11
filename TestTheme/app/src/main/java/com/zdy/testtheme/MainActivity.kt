package com.zdy.testtheme

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.zdy.testtheme.databinding.ActivityMainBinding
import com.zdy.testtheme.themes.ThemesEnum
import com.zdy.testtheme.utils.TransitionUtil
import com.zdy.testtheme.utils.TransitionUtil.centerOnScreen
import com.zdy.testtheme.utils.TransitionUtil.takeScreenshot

class MainActivity : BaseActivity() {

    lateinit var binding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.buttonLight.setOnClickListener {
            switchTheme(ThemesEnum.light, binding.buttonLight)
        }
        binding.buttonDark.setOnClickListener {
            switchTheme(ThemesEnum.night, binding.buttonDark)
        }
        binding.buttonGreen.setOnClickListener {
            switchTheme(ThemesEnum.green, binding.buttonGreen)
        }
        binding.buttonRed.setOnClickListener {
            switchTheme(ThemesEnum.red, findViewById(R.id.button_red))
        }

        binding.buttonCreate.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

    }

    private fun switchTheme(theme: ThemesEnum, view: View){
        val rootView = window.decorView.rootView
        TransitionUtil.transitionBitmap = rootView.takeScreenshot()
        // Launch the transition activity with the center point of the toggle button
        TransitionUtil.switchToTheme(this, theme, view.centerOnScreen())
    }
}
