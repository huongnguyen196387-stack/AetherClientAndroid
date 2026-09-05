package com.aether.client

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.widget.*

class MainActivity : Activity() {

    private val mcPackage = "com.mojang.minecraftpe"

    private val bg = Color.rgb(10, 10, 15)
    private val panel = Color.rgb(20, 20, 28)
    private val purple = Color.rgb(135, 95, 255)
    private val white = Color.WHITE
    private val gray = Color.rgb(170, 170, 180)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showHome()
    }

    private fun base(): LinearLayout {
        return LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(24, 35, 24, 24)
            setBackgroundColor(bg)
        }
    }

    private fun title(text: String): TextView {
        return TextView(this).apply {
            this.text = text
            textSize = 26f
            setTextColor(white)
            typeface = Typeface.DEFAULT_BOLD
            gravity = Gravity.CENTER
            setPadding(0, 10, 0, 25)
        }
    }

    private fun button(text: String, action: () -> Unit): Button {
        return Button(this).apply {
            this.text = text
            textSize = 15f
            setTextColor(white)
            setOnClickListener { action() }

            val p = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            p.setMargins(0, 7, 0, 7)
            layoutParams = p
        }
    }

    private fun section(text: String): TextView {
        return TextView(this).apply {
            this.text = text
            textSize = 17f
            setTextColor(purple)
            typeface = Typeface.DEFAULT_BOLD
            setPadding(5, 18, 5, 8)
        }
    }

    private fun showHome() {

        val root = base()

        root.addView(title("AETHER CLIENT"))

        val version = TextView(this).apply {
            text = "Bedrock 1.26.0.2  •  Android"
            textSize = 14f
            setTextColor(gray)
            gravity = Gravity.CENTER
            setPadding(0, 0, 0, 20)
        }

        root.addView(version)

        root.addView(button("▶  PLAY MINECRAFT") {
            launchMinecraft()
        })

        root.addView(button("⚡  OPTIMIZATION") {
            showOptimization()
        })

        root.addView(button("⚔  PVP") {
            showPvP()
        })

        root.addView(button("▣  HUD") {
            showHud()
        })

        root.addView(button("⚙  SETTINGS") {
            showSettings()
        })

        val scroll = ScrollView(this)
        scroll.addView(root)

        setContentView(scroll)
    }

    private fun showOptimization() {

        val root = base()

        root.addView(title("OPTIMIZATION"))

        root.addView(section("PERFORMANCE PRESETS"))

        root.addView(button("🚀 EXTREME FPS") {
            Toast.makeText(
                this,
                "Extreme FPS preset selected",
                Toast.LENGTH_SHORT
            ).show()
        })

        root.addView(button("⚡ BALANCED") {
            Toast.makeText(
                this,
                "Balanced preset selected",
                Toast.LENGTH_SHORT
            ).show()
        })

        root.addView(button("🔋 BATTERY") {
            Toast.makeText(
                this,
                "Battery preset selected",
                Toast.LENGTH_SHORT
            ).show()
        })

        root.addView(section("RENDER"))

        addSwitch(root, "Max FPS", true)
        addSwitch(root, "Low Effects", true)
        addSwitch(root, "Clouds", false)
        addSwitch(root, "Smooth Lighting", false)
        addSwitch(root, "Anti-Aliasing", false)
        addSwitch(root, "Particles", false)
        addSwitch(root, "Animations", false)

        root.addView(section("DISTANCE"))

        addSwitch(root, "Low Entity Distance", true)
        addSwitch(root, "Low Render Distance", true)

        root.addView(button("← BACK") {
            showHome()
        })

        setContentView(ScrollView(this).apply {
            addView(root)
        })
    }

    private fun showPvP() {

        val root = base()

        root.addView(title("PVP"))

        root.addView(section("PVP HUD"))

        addSwitch(root, "CPS Counter", true)
        addSwitch(root, "FPS Counter", true)
        addSwitch(root, "Ping Counter", true)
        addSwitch(root, "Coordinates", false)

        root.addView(section("PVP OPTIONS"))

        addSwitch(root, "Hit Indicator", true)
        addSwitch(root, "Attack Indicator", true)
        addSwitch(root, "Keystrokes", false)

        root.addView(button("← BACK") {
            showHome()
        })

        setContentView(ScrollView(this).apply {
            addView(root)
        })
    }

    private fun showHud() {

        val root = base()

        root.addView(title("HUD"))

        root.addView(section("HUD MODULES"))

        addSwitch(root, "FPS", true)
        addSwitch(root, "Ping", true)
        addSwitch(root, "CPS", true)
        addSwitch(root, "Coordinates", false)
        addSwitch(root, "Keystrokes", false)

        root.addView(section("TOUCH"))

        addSwitch(root, "Touch HUD", true)
        addSwitch(root, "Large Buttons", false)

        root.addView(button("← BACK") {
            showHome()
        })

        setContentView(ScrollView(this).apply {
            addView(root)
        })
    }

    private fun showSettings() {

        val root = base()

        root.addView(title("SETTINGS"))

        root.addView(section("AETHER"))

        root.addView(button("Client Version: 0.2.0") {})

        root.addView(button("Minecraft Package") {
            Toast.makeText(
                this,
                mcPackage,
                Toast.LENGTH_SHORT
            ).show()
        })

        root.addView(button("Check Minecraft") {

            val installed =
                packageManager.getLaunchIntentForPackage(mcPackage) != null

            Toast.makeText(
                this,
                if (installed)
                    "Minecraft đã được cài"
                else
                    "Không tìm thấy Minecraft",
                Toast.LENGTH_LONG
            ).show()
        })

        root.addView(button("← BACK") {
            showHome()
        })

        setContentView(ScrollView(this).apply {
            addView(root)
        })
    }

    private fun addSwitch(
        root: LinearLayout,
        text: String,
        enabled: Boolean
    ) {

        val sw = Switch(this).apply {
            this.text = text
            isChecked = enabled
            setTextColor(white)

            val p = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            p.setMargins(0, 2, 0, 2)

            layoutParams = p
        }

        root.addView(sw)
    }

    private fun launchMinecraft() {

        val intent =
            packageManager.getLaunchIntentForPackage(mcPackage)

        if (intent != null) {

            startActivity(intent)

        } else {

            Toast.makeText(
                this,
                "Minecraft Bedrock chưa được cài.",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}
