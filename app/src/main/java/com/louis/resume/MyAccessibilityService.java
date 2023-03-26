package com.louis.resume;

import android.accessibilityservice.AccessibilityService;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

public class MyAccessibilityService extends AccessibilityService {
    public MyAccessibilityService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Log.e("resume", "接到通知，包名:" + event.getPackageName());
        Log.e("resume", "接到通知，信息:" + event.getText());
    }

    @Override
    public void onInterrupt() {

    }

}