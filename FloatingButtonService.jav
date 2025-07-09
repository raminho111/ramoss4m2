package com.ramoss4m.panel;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.*;
import android.widget.*;

public class FloatingButtonService extends Service {
    private WindowManager windowManager;
    private View floatingButton;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        floatingButton = new Button(this);
        ((Button) floatingButton).setText("@ramoss4m");
        floatingButton.setOnClickListener(v -> showPanel());

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT
        );
        params.gravity = Gravity.TOP | Gravity.START;
        params.x = 20; params.y = 200;

        windowManager.addView(floatingButton, params);
    }

    private void showPanel() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Painel @ramoss4m")
               .setItems(new CharSequence[]{"ESP Nome", "ESP Caixa", "ESP Linha", "Aimbot Headshot", "Aimbot Pescoço", "Aim FOV"}, null)
               .setNegativeButton("Fechar", null)
               .show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (floatingButton != null) windowManager.removeView(floatingButton);
    }
}
