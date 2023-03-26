package com.louis.resume;

import android.accessibilityservice.AccessibilityService;
import android.content.ComponentName;
import android.content.Intent;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
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
                break;
            }
        }
    }

    @Override
    public void onInterrupt() {

    }

}