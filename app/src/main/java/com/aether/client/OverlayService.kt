package com.aether.client

import android.app.Service
import android.content.Intent
import android.graphics.Color
import android.graphics.PixelFormat
import android.graphics.Typeface
import android.os.IBinder
import android.provider.Settings
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.TextView

class OverlayService : Service() {

    private lateinit var windowManager: WindowManager
    private lateinit var button: TextView

    override fun onCreate() {
        super.onCreate()

        windowManager =
            getSystemService(WINDOW_SERVICE) as WindowManager

        button = TextView(this).apply {
            text = "A"
            textSize = 18f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
            typeface = Typeface.DEFAULT_BOLD
            setBackgroundColor(Color.rgb(135, 95, 255))
        }

        val params = WindowManager.LayoutParams(
            65,
            65,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        )

        params.gravity = Gravity.TOP or Gravity.START
        params.x = 25
        params.y = 250

        windowManager.addView(button, params)

        button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

        button.setOnTouchListener(
            object : View.OnTouchListener {

                private var startX = 0
                private var startY = 0
                private var downX = 0f
                private var downY = 0f

                override fun onTouch(
                    view: View,
                    event: MotionEvent
                ): Boolean {

                    when (event.action) {

                        MotionEvent.ACTION_DOWN -> {
                            startX = params.x
                            startY = params.y
                            downX = event.rawX
                            downY = event.rawY
                            return false
                        }

                        MotionEvent.ACTION_MOVE -> {

                            params.x =
                                startX +
                                (event.rawX - downX).toInt()

                            params.y =
                                startY +
                                (event.rawY - downY).toInt()

                            windowManager.updateViewLayout(
                                button,
                                params
                            )

                            return true
                        }
                    }

                    return false
                }
            }
        )
    }

    override fun onDestroy() {
        if (::button.isInitialized) {
            windowManager.removeView(button)
        }

        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}
