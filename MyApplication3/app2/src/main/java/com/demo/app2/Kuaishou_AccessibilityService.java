package com.demo.app2;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.content.Intent;
import android.graphics.Path;
import android.os.Build;
import android.provider.Settings;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import androidx.annotation.RequiresApi;

import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.N)
public class Kuaishou_AccessibilityService extends AccessibilityService {
    @Override
    public void onInterrupt() {
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        try {
            //拿到根节点
            AccessibilityNodeInfo rootInfo = getRootInActiveWindow();
            if (rootInfo == null) {
                return;
            }
            MyGesture();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void MyGesture() {//仿滑动
        Path path = new Path();
        path.moveTo(300, 800);//设置Path的起点
        path.lineTo(300,0);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        GestureDescription.Builder builder = new GestureDescription.Builder();
        GestureDescription description = builder.addStroke(new GestureDescription.StrokeDescription(path, 500L, 37L)).build();
        //100L 第一个是开始的时间，第二个是持续时间
        dispatchGesture(description, new MyCallBack(), null);
    }

    class MyCallBack extends GestureResultCallback {

        public MyCallBack() {
            super();
        }

        @Override
        public void onCompleted(GestureDescription gestureDescription) {
            super.onCompleted(gestureDescription);

        }

        @Override
        public void onCancelled(GestureDescription gestureDescription) {
            super.onCancelled(gestureDescription);

        }
    }

}
