package com.aether.client;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.Typeface;
import android.os.IBinder;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

public class OverlayService extends Service {

    private WindowManager windowManager;
    private TextView button;

    @Override
    public void onCreate() {
        super.onCreate();

        windowManager =
                (WindowManager) getSystemService(WINDOW_SERVICE);

        button = new TextView(this);

        button.setText("A");
        button.setTextColor(Color.WHITE);
        button.setTextSize(18);
        button.setGravity(Gravity.CENTER);
        button.setTypeface(Typeface.DEFAULT, Typeface.BOLD);

        button.setBackgroundColor(Color.rgb(35, 100, 220));

        WindowManager.LayoutParams params =
                new WindowManager.LayoutParams(
                        60,
                        60,
                        WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                        PixelFormat.TRANSLUCENT);

        params.gravity = Gravity.TOP | Gravity.START;
        params.x = 30;
        params.y = 200;

        windowManager.addView(button, params);

        button.setOnClickListener(v -> {
            Intent intent =
                    new Intent(this, MainActivity.class);

            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(intent);
        });

        button.setOnTouchListener(
                new View.OnTouchListener() {

                    private int startX;
                    private int startY;
                    private float downX;
                    private float downY;

                    @Override
                    public boolean onTouch(
                            View v,
                            MotionEvent event) {

                        switch (event.getAction()) {

                            case MotionEvent.ACTION_DOWN:
                                startX = params.x;
                                startY = params.y;
                                downX = event.getRawX();
                                downY = event.getRawY();
                                return false;

                            case MotionEvent.ACTION_MOVE:
                                params.x =
                                        startX +
                                        (int)(event.getRawX() - downX);

                                params.y =
                                        startY +
                                        (int)(event.getRawY() - downY);

                                windowManager.updateViewLayout(
                                        button,
                                        params);

                                return true;
                        }

                        return false;
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (button != null) {
            windowManager.removeView(button);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
          }
