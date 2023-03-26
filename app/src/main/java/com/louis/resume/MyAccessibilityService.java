package com.louis.resume;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Path;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import androidx.annotation.RequiresApi;
import com.louis.resume.util.CollectionUtils;

import java.util.List;

public class MyAccessibilityService extends AccessibilityService {
    public final static String TAG = MyAccessibilityService.class.getSimpleName();

    public MyAccessibilityService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        List<CharSequence> texts = event.getText();
        if (CollectionUtils.isEmpty(texts)) {
            return;
        }
        for (CharSequence msg : texts) {
            if (msg.toString().contains("1")) {
                Log.i(TAG, "打开ToDesk中");
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                intent.setComponent(new ComponentName("youqu.android.todesk", "youqu.android.todesk.activity.WelcomeActivity"));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    Log.i(TAG, "onAccessibilityEvent: " + e.getMessage());
                }
                dispatchGestureClick(960, 750);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Log.i(TAG, "onAccessibilityEvent: " + e.getMessage());
                }
                dispatchGestureClick(560, 2280);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Log.i(TAG, "onAccessibilityEvent: " + e.getMessage());
                }
                dispatchGestureClick(550, 750);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Log.i(TAG, "onAccessibilityEvent: " + e.getMessage());
                }
                dispatchGestureClick(550, 1430);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Log.i(TAG, "onAccessibilityEvent: " + e.getMessage());
                }
                dispatchGestureClick(770, 1300);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Log.i(TAG, "onAccessibilityEvent: " + e.getMessage());
                }
                Log.i(TAG, "返回APP");
                Intent app = new Intent(Intent.ACTION_MAIN);
                app.addCategory(Intent.CATEGORY_LAUNCHER);
                app.setComponent(new ComponentName("com.louis.resume", "com.louis.resume.MainActivity"));
                app.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(app);
                break;
            }
        }
    }

    @Override
    public void onInterrupt() {

    }

    @RequiresApi(24)
    public void dispatchGestureClick(int x, int y) {
        Path path = new Path();
        path.moveTo(x, y);
        boolean click = dispatchGesture(new GestureDescription
                .Builder()
                .addStroke(new GestureDescription.StrokeDescription(path, 0, 100)).build(),null, null);
        Log.i(TAG, "dispatchGestureClick: " + click);
    }

}