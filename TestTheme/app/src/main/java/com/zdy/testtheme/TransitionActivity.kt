package com.zdy.testtheme

import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zdy.testtheme.themes.ThemeHelper
import com.zdy.testtheme.themes.ThemesEnum
import com.zdy.testtheme.utils.CircularRevealImageView
import com.zdy.testtheme.utils.TransitionUtil

class TransitionActivity : AppCompatActivity() {

    private lateinit var screenShotImageView: CircularRevealImageView
    private var theme = ThemesEnum.light
    private var centerPoint: Point? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(0,0)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transition)
        screenShotImageView = findViewById(R.id.screenshot_image_view)
        val themeOrdinal = intent.getIntExtra(EXTRA_THEME_ID, ThemesEnum.light.resID)
        theme = ThemesEnum.entries.getOrNull(themeOrdinal) ?: ThemesEnum.light
        centerPoint = intent.getParcelableExtra(EXTRA_CENTER_POINT) as? Point
        setUpImageView()
    }

    private fun setUpImageView() {
        if (TransitionUtil.transitionBitmap == null) {
            ThemeHelper.applyTheme(this, theme)
            finish()
            return
        }

        screenShotImageView.setImageBitmap(TransitionUtil.transitionBitmap)
        screenShotImageView.post {
            ThemeHelper.applyTheme(this, theme)
            screenShotImageView.startRevealAnimation(centerPoint) {
                finish()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        TransitionUtil.transitionBitmap = null
    }

    companion object {
        const val EXTRA_THEME_ID = "EXTRA_THEME_ID"
        const val EXTRA_CENTER_POINT = "center_point"
        fun getIntent(context: Context, theme: ThemesEnum, centerPoint: Point): Intent {
            return Intent(context, TransitionActivity::class.java).apply {
                putExtra(EXTRA_THEME_ID, theme.ordinal)
                putExtra(EXTRA_CENTER_POINT, centerPoint)
            }
        }
    }
}