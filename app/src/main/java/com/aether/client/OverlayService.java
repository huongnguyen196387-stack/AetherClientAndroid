package com.aether.client;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.provider.Settings;
import android.net.Uri;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Switch;

public class MainActivity extends Activity {

    private final int BG = Color.rgb(13, 14, 18);
    private final int CARD = Color.rgb(25, 27, 33);
    private final int WHITE = Color.WHITE;
    private final int GRAY = Color.rgb(160, 165, 175);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ModuleManager.init();

        showAetherMenu();
    }

    private GradientDrawable background(int color) {
        GradientDrawable drawable =
                new GradientDrawable();

        drawable.setColor(color);
        drawable.setCornerRadius(28);

        return drawable;
    }

    private TextView text(
            String value,
            float size) {

        TextView view = new TextView(this);

        view.setText(value);
        view.setTextColor(WHITE);
        view.setTextSize(size);
        view.setPadding(20, 12, 20, 12);

        return view;
    }

    private LinearLayout module(
            Module module) {

        LinearLayout card =
                new LinearLayout(this);

        card.setOrientation(
                LinearLayout.VERTICAL);

        card.setPadding(
                12, 10, 12, 10);

        card.setBackground(
                background(CARD));

        LinearLayout.LayoutParams cardParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);

        cardParams.setMargins(
                8, 6, 8, 6);

        card.setLayoutParams(cardParams);

        LinearLayout row =
                new LinearLayout(this);

        row.setGravity(
                Gravity.CENTER_VERTICAL);

        TextView title =
                text(module.getName(), 17);

        title.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD);

        Switch toggle =
                new Switch(this);

        toggle.setChecked(
                module.isEnabled());

        toggle.setOnCheckedChangeListener(
                (buttonView, checked) -> {

                    module.setEnabled(
                            checked);

                });

        row.addView(
                title,
                new LinearLayout.LayoutParams(
                        0,
                        -2,
                        1));

        row.addView(toggle);

        card.addView(row);

        return card;
    }

    private void showAetherMenu() {

        LinearLayout root =
                new LinearLayout(this);

        root.setOrientation(
                LinearLayout.VERTICAL);

        root.setPadding(
                14, 18, 14, 18);

        root.setBackgroundColor(BG);

        TextView title =
                text("AETHER", 30);

        title.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD);

        root.addView(title);

        TextView version =
                text(
                        "CLIENT 3.0 • ANDROID",
                        12);

        version.setTextColor(GRAY);

        root.addView(version);

        /*
         * OVERLAY BUTTON
         */

        TextView overlayButton =
                text(
                        "◉  START AETHER OVERLAY",
                        15);

        overlayButton.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD);

        overlayButton.setBackground(
                background(
                        Color.rgb(35, 90, 190)));

        overlayButton.setGravity(
                Gravity.CENTER);

        overlayButton.setOnClickListener(
                v -> startOverlay());

        LinearLayout.LayoutParams overlayParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);

        overlayParams.setMargins(
                8, 12, 8, 12);

        root.addView(
                overlayButton,
                overlayParams);

        /*
         * SCROLL AREA
         */

        ScrollView scroll =
                new ScrollView(this);

        LinearLayout list =
                new LinearLayout(this);

        list.setOrientation(
                LinearLayout.VERTICAL);

        TextView performance =
                text(
                        "⚡  PERFORMANCE",
                        16);

        performance.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD);

        list.addView(performance);

        for (Module module :
                ModuleManager.getModules()) {

            list.addView(
                    module(module));
        }

        TextView hud =
                text(
                        "📊  HUD",
                        16);

        hud.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD);

        list.addView(hud);

        list.addView(
                module(
                        new Module(
                                "FPS Counter")));

        list.addView(
                module(
                        new Module(
                                "Ping")));

        list.addView(
                module(
                        new Module(
                                "CPS")));

        TextView settings =
                text(
                        "⚙  SETTINGS",
                        16);

        settings.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD);

        list.addView(settings);

        list.addView(
                module(
                        new Module(
                                "Dark Theme")));

        scroll.addView(list);

        root.addView(
                scroll,
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        0,
                        1));

        setContentView(root);
    }

    /*
     * START OVERLAY
     */

    private void startOverlay() {

        if (android.os.Build.VERSION.SDK_INT >= 23) {

            if (!Settings.canDrawOverlays(this)) {

                Intent intent =
                        new Intent(
                                Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                                Uri.parse(
                                        "package:" +
                                        getPackageName()));

                startActivity(intent);

                return;
            }
        }

        Intent service =
                new Intent(
                        this,
                        OverlayService.class);

        startService(service);
    }
            }
