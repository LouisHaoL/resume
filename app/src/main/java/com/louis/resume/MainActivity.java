package com.louis.resume;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View view = findViewById(R.id.acquire_access);
        view.setOnClickListener(v -> {
            Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
            startActivity(intent);
        });
        View sendBtn = findViewById(R.id.send_noti);
        sendBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            ComponentName cn = new ComponentName("youqu.android.todesk", "youqu.android.todesk.MainActivity");
            intent.setComponent(cn);
            startActivity(intent);
        });

        // 判断是否开启监听通知权限
        if (NotificationManagerCompat.getEnabledListenerPackages(this).contains(getPackageName())) {
            Intent serviceIntent = new Intent(this, GuardNotificationListenerService.class);
            startService(serviceIntent);
        } else {
            // 去开启 监听通知权限
            startActivity(new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"));
        }
    }
}