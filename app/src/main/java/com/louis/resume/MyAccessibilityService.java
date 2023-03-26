package com.louis.resume;

import android.accessibilityservice.AccessibilityService;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import com.louis.resume.util.CollectionUtils;
import com.louis.resume.util.ResumeUtil;

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
            if (msg.toString().contains("开机")) {
                Log.i(TAG, "接收到开机指令！");
                resume();
                break;
            }
        }
    }

    private void resume() {
        boolean res = ResumeUtil.wake("192.168.xx.xx", "xx-xx-xx-xx-xx-xx", 9);
        Log.i(TAG, "唤醒电脑:" + res);
    }

    @Override
    public void onInterrupt() {

    }

}