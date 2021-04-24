package com.example.qlvt.QLTuyen;

import android.os.Handler;

class Utils {
    public interface DelayCallback {
        void afterDelay();
    }

    public static void delay(int secs, final com.example.qlvt.QLTuyen.Utils.DelayCallback delayCallback) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                delayCallback.afterDelay();
            }
        }, secs * 1000); // afterDelay will be executed after (secs*1000) milliseconds.
    }
}

public class QuanLyTuyen {
}
