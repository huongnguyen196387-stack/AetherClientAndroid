package com.aether.client

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.Gravity
import android.view.View
import android.widget.*

class MainActivity : Activity() {

    private val mcPackage = "com.mojang.minecraftpe"

    private val bg = Color.rgb(10, 10, 15)
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

    private fun section(text: String): TextView {
        return TextView(this).apply {
            this.text = text
            textSize = 17f
            setTextColor(purple)
            typeface = Typeface.DEFAULT_BOLD
            setPadding(5, 18, 5, 8)
        }
    }

    private fun button(
        text: String,
        action: () -> Unit
    ): Button {
        return Button(this).apply {
            this.text = text
            textSize = 15f
            setTextColor(white)

            setOnClickListener {
                action()
            }

            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 7, 0, 7)
            }
        }
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

            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 2, 0, 2)
            }
        }

        root.addView(sw)
    }

    private fun showHome() {

        val root = base()

        root.addView(title("AETHER CLIENT"))

        root.addView(
            TextView(this).apply {
                text = "Bedrock 1.26.0.2  •  Android"
                textSize = 14f
                setTextColor(gray)
                gravity = Gravity.CENTER
                setPadding(0, 0, 0, 20)
            }
        )

        // MỞ MINECRAFT
        root.addView(button("▶  PLAY MINECRAFT") {
            launchMinecraft()
        })

        // AETHER OVERLAY
        root.addView(button("◉  START AETHER OVERLAY") {
            startAetherOverlay()
        })

        // TỐI ƯU
        root.addView(button("⚡  OPTIMIZATION") {
            showOptimization()
        })

        // PVP
        root.addView(button("⚔  PVP") {
            showPvP()
        })

        // HUD
        root.addView(button("▣  HUD") {
            showHud()
        })

        // SETTINGS
        root.addView(button("⚙  SETTINGS") {
            showSettings()
        })

        setContentView(
            ScrollView(this).apply {
                setBackgroundColor(bg)
                addView(root)
            }
        )
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

        root.addView(section("RENDER OPTIMIZATION"))

        addSwitch(root, "Render Culling", false)
        addSwitch(root, "Entity Culling", false)
        addSwitch(root, "Particle Optimization", true)
        addSwitch(root, "Animation Optimization", true)
        addSwitch(root, "Smart FPS", true)
        addSwitch(root, "FPS Stabilizer", true)

        root.addView(section("GRAPHICS"))

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

        setContentView(
            ScrollView(this).apply {
                setBackgroundColor(bg)
                addView(root)
            }
        )
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

        setContentView(
            ScrollView(this).apply {
                setBackgroundColor(bg)
                addView(root)
            }
        )
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

        setContentView(
            ScrollView(this).apply {
                setBackgroundColor(bg)
                addView(root)
            }
        )
    }

    private fun showSettings() {

        val root = base()

        root.addView(title("SETTINGS"))

        root.addView(section("AETHER"))

        root.addView(
            button("Client Version: 3.0") {}
        )

        root.addView(
            button("Minecraft Package") {
                Toast.makeText(
                    this,
                    mcPackage,
                    Toast.LENGTH_SHORT
                ).show()
            }
        )

        root.addView(
            button("CHECK MINECRAFT") {

                val installed =
                    packageManager.getLaunchIntentForPackage(
                        mcPackage
                    ) != null

                Toast.makeText(
                    this,
                    if (installed)
                        "Minecraft đã được cài"
                    else
                        "Không tìm thấy Minecraft",
                    Toast.LENGTH_LONG
                ).show()
            }
        )

        root.addView(button("← BACK") {
            showHome()
        })

        setContentView(
            ScrollView(this).apply {
                setBackgroundColor(bg)
                addView(root)
            }
        )
    }

    private fun launchMinecraft() {

        val intent =
            packageManager.getLaunchIntentForPackage(
                mcPackage
            )

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

    private fun startAetherOverlay() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (!Settings.canDrawOverlays(this)) {

                val intent = Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:$packageName")
                )

                startActivity(intent)
                return
            }
        }

        val serviceIntent =
            Intent(this, OverlayService::class.java)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(serviceIntent)
        } else {
            startService(serviceIntent)
        }

        Toast.makeText(
            this,
            "Aether Overlay đã bật",
            Toast.LENGTH_SHORT
        ).show()
    }
}
