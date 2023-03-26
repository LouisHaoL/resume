package com.louis.resume;

import android.app.Notification;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.view.accessibility.AccessibilityManager;

public class GuardNotificationListenerService extends NotificationListenerService {
    public final static String TAG = GuardNotificationListenerService.class.getSimpleName();
    AccessibilityManager accessibilityManager;

    @Override
    public void onCreate() {
        super.onCreate();
        accessibilityManager = (AccessibilityManager) getSystemService(Service.ACCESSIBILITY_SERVICE);
    }

    @Override
    public void onListenerConnected() {
        super.onListenerConnected();
        Log.d(TAG, "onListenerConnected");
    }

    @Override
    public void onListenerDisconnected() {
        super.onListenerDisconnected();
        Log.d(TAG, "onListenerDisconnected");
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        Log.d(TAG, "onNotificationPosted");
        showMsg(sbn);
    }

    private void showMsg(StatusBarNotification sbn) {
        Bundle extras = sbn.getNotification().extras;

        String packageName = sbn.getPackageName();

        if (extras != null) {
            // 获取通知消息内容
            Object msgText = extras.getCharSequence(Notification.EXTRA_TEXT);
            //注意：获取的通知信息和短信的传递内容不一样 短信为SpannableString 这里容易造成转换异常
            String msg = String.valueOf(msgText);
            if (msg.contains("开机")) {// 执行无障碍操作
                Log.i(TAG, "打开ToDesk中");
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                intent.setComponent(new ComponentName("youqu.android.todesk", "youqu.android.todesk.activity.WelcomeActivity"));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {
            Log.d(TAG, "is null ...." + packageName);
        }

    }


    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        Log.d(TAG, "onNotificationRemoved");
        showMsg(sbn);
    }
}