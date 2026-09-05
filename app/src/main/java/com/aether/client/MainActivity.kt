package com.aether.client

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.widget.*

class MainActivity : Activity() {

    private val minecraftPackage = "com.mojang.minecraftpe"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = LinearLayout(this)

        root.orientation = LinearLayout.VERTICAL
        root.gravity = Gravity.CENTER_HORIZONTAL

        root.setPadding(32, 50, 32, 32)
        root.setBackgroundColor(Color.rgb(11, 11, 16))

        val title = TextView(this)

        title.text = "AETHER CLIENT"
        title.textSize = 28f
        title.setTextColor(Color.WHITE)
        title.gravity = Gravity.CENTER

        root.addView(title)

        val version = TextView(this)

        version.text = "Bedrock 1.26.0.2"
        version.textSize = 14f
        version.setTextColor(Color.LTGRAY)
        version.gravity = Gravity.CENTER

        root.addView(version)

        addOption(root, "MAX FPS", true)
        addOption(root, "LOW EFFECTS", true)
        addOption(root, "PvP HUD", true)
        addOption(root, "TOUCH HUD", true)

        val launch = Button(this)

        launch.text = "LAUNCH MINECRAFT"

        launch.setOnClickListener {

            val intent =
                packageManager.getLaunchIntentForPackage(
                    minecraftPackage
                )

            if (intent != null) {
                startActivity(intent)
            } else {

                Toast.makeText(
                    this,
                    "Minecraft chưa được cài",
                    Toast.LENGTH_LONG
                ).show()

            }
        }

        val params =
            LinearLayout.LayoutParams(
                -1,
                -2
            )

        params.setMargins(0, 40, 0, 0)

        root.addView(launch, params)

        setContentView(root)
    }

    private fun addOption(
        root: LinearLayout,
        name: String,
        enabled: Boolean
    ) {

        val check = CheckBox(this)

        check.text = name
        check.isChecked = enabled
        check.setTextColor(Color.WHITE)

        root.addView(check)
    }
}
