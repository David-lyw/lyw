package com.lyw.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import java.text.SimpleDateFormat;

/**
 * Created by David on 16/9/21.
 * 倒计时的控件。可实现回调方法。
 */

public class TimerTextView extends TextView {
    private long mEndTime;
    private boolean isTimerAlive = false;
    Countdown countdown;
    private Thread mCountdownThread = new Thread(new Runnable() {
        @Override
        public void run() {
            long startTime = System.currentTimeMillis();
            while (isTimerAlive) {
                final long cost = mEndTime + startTime - System.currentTimeMillis();
                if (cost > 0) {
                    post(new Runnable() {
                        @Override
                        public void run() {
                            setText(new SimpleDateFormat("mm:ss:SS").format(cost));
                        }
                    });

                } else {
                    post(new Runnable() {
                        @Override
                        public void run() {
                            if (countdown != null)
                                countdown.getdown();//回调。
                        }
                    });
                }
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    });

    public TimerTextView(Context context) {
        this(context, null);
    }

    public TimerTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimerTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public void countdown(long endTimeMillis) {//方法。
        this.mEndTime = endTimeMillis;
    }

    public void destroyTimer() {
        isTimerAlive = false;
    }


    public interface Countdown {// 接口

        void getdown();
    }


    public void onStart() {//开始

        isTimerAlive = true;
        if (!mCountdownThread.isAlive()) {
            mCountdownThread.start();
            // mCountdownThread.
        }

    }

    public void onStop() {//停止
        //isTimerAlive = false;
        mCountdownThread.interrupt();
    }

    public void setCountdown(Countdown countdown) {
        this.countdown = countdown;
    }
}
