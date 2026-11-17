
package com.universal.gamebooster

import android.app.Activity
import android.app.ActivityManager
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.*

class MainActivity : Activity() {

    private lateinit var ramInfo: TextView

    private fun makeText(
        value: String,
        size: Float,
        color: Int
    ): TextView {
        return TextView(this).apply {
            text = value
            textSize = size
            setTextColor(color)
            setPadding(12, 12, 12, 12)
        }
    }

    private fun refreshRam() {
        val manager =
            getSystemService(ACTIVITY_SERVICE) as ActivityManager

        val info = ActivityManager.MemoryInfo()
        manager.getMemoryInfo(info)

        val total = info.totalMem / (1024 * 1024)
        val available = info.availMem / (1024 * 1024)
        val used = total - available

        ramInfo.text = """
            Total RAM: ${total} MB
            Used RAM: ${used} MB
            Available RAM: ${available} MB
        """.trimIndent()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = Color.BLACK
        window.navigationBarColor = Color.BLACK

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(20, 24, 20, 20)
            setBackgroundColor(Color.rgb(10, 10, 10))
        }

        val scroll = ScrollView(this)
        val content = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
        }

        val title = makeText(
            "UNIVERSAL GAME BOOSTER",
            23f,
            Color.WHITE
        )
        title.setTypeface(null, Typeface.BOLD)
        content.addView(title)

        content.addView(
            makeText(
                "Your gaming companion",
                14f,
                Color.LTGRAY
            )
        )

        val ramTitle = makeText(
            "DEVICE RAM MONITOR",
            18f,
            Color.CYAN
        )
        content.addView(ramTitle)

        ramInfo = makeText(
            "Loading RAM information...",
            16f,
            Color.WHITE
        )

        content.addView(ramInfo)

        val refreshButton = Button(this).apply {
            text = "REFRESH RAM"
            setOnClickListener {
                refreshRam()
            }
        }

        content.addView(refreshButton)

        content.addView(
            makeText(
                "GAMING PERFORMANCE",
                18f,
                Color.CYAN
            )
        )

        content.addView(
            makeText(
                "Optimization mode: RAM + Performance",
                15f,
                Color.WHITE
            )
        )

        content.addView(
            makeText(
                "BEFORE GAMING",
                18f,
                Color.CYAN
            )
        )

        val tips = """
            1. Close unnecessary apps.
            2. Keep enough free storage.
            3. Use a stable internet connection.
            4. Avoid playing while your phone is overheating.
            5. Use suitable graphics settings.
        """.trimIndent()

        content.addView(
            makeText(tips, 15f, Color.WHITE)
        )

        content.addView(
            makeText(
                "Universal Game Booster v1.0",
                13f,
                Color.GRAY
            )
        )

        scroll.addView(content)
        root.addView(
            scroll,
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        )

        setContentView(root)
        refreshRam()
    }
}
