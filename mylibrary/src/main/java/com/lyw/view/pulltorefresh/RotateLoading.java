package com.lyw.view.pulltorefresh;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by David on 16/11/8.
 */
public class RotateLoading extends View {
    private static final int DEFAULT_WIDTH = 6;
    private Paint paint;
    private RectF loadingRectF;

    private int topDegree = 10;
    private int bottomDegree = 190;
    private float arc;
    private float width;
    private boolean changeBigger = true;
    private boolean isStart = false;
    private int color;


    public RotateLoading(Context context) {
        super(context);
        this.initView(context, (AttributeSet) null);
    }

    public RotateLoading(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.initView(context, attrs);
    }

    public RotateLoading(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.initView(context, attrs);
    }


    private void initView(Context context, AttributeSet attrs) {
        this.color = -1;
        this.width = this.dpToPx(context, 6.0F);
        this.paint = new Paint();
        this.paint.setColor(this.color);
        this.paint.setAntiAlias(true);//抗锯齿效果
        this.paint.setStyle(Style.STROKE);//画笔类型 STROKE空心
        this.paint.setStrokeWidth((float) this.width);//设置空心线宽
        this.paint.setStrokeCap(Paint.Cap.ROUND);//画笔笔刷类型
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.arc = 10.0F;
        this.loadingRectF = new RectF((float) (2 * this.width), (float) (2 * this.width), (float) (w - 2 * this.width), (float) (h - 2 * this.width));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.isStart) {
            this.paint.setColor(this.color);
            canvas.drawArc(this.loadingRectF, (float) this.topDegree, (float) this.arc, false, this.paint);
            canvas.drawArc(this.loadingRectF, (float) this.bottomDegree, (float) this.arc, false, this.paint);
            this.topDegree += 10;
            this.bottomDegree += 10;

            if (this.topDegree > 360) {
                this.topDegree -= 360;
            }
            if (this.bottomDegree > 360) {
                this.bottomDegree -= 360;
            }
            if (this.changeBigger) {
                if (this.arc < 160.0F) {
                    this.arc = (float) ((double) this.arc + 2.5D);
                    this.invalidate();//刷新view
                }
            } else if (this.arc > 10.0F) {
                this.arc -= 5.0F;
                this.invalidate();
            }
            if (this.arc == 160.0F || this.arc == 10.0F) {
                this.changeBigger = !this.changeBigger;
                this.invalidate();
            }

        }
    }

    public void start() {
        this.startAnimator();
        this.isStart=true;
        this.invalidate();

    }

    public void stop(){
        this.stopAnimator();
        this.invalidate();
    }

    public boolean isStart() {
        return this.isStart;
    }



    private void startAnimator() {
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(this, "scaleX", new float[]{0.0F, 1.0F});
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(this, "scaleY", new float[]{0.0F, 1.0F});
        scaleXAnimator.setDuration(300L);
        scaleXAnimator.setInterpolator(new LinearInterpolator());//动画以匀速的速率改变。
        scaleYAnimator.setDuration(300L);
        scaleYAnimator.setInterpolator(new LinearInterpolator());

        AnimatorSet animatorSet = new AnimatorSet();//动画集合
        animatorSet.playTogether(new Animator[]{scaleXAnimator, scaleYAnimator});
        animatorSet.start();

    }

    private void stopAnimator(){
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(this, "scaleX", new float[]{0.0F, 1.0F});
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(this, "scaleY", new float[]{0.0F, 1.0F});
        scaleXAnimator.setDuration(300L);
        scaleXAnimator.setInterpolator(new LinearInterpolator());//动画以匀速的速率改变。
        scaleYAnimator.setDuration(300L);
        scaleYAnimator.setInterpolator(new LinearInterpolator());

        AnimatorSet animatorSet = new AnimatorSet();//动画集合
        animatorSet.playTogether(new Animator[]{scaleXAnimator, scaleYAnimator});
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

                RotateLoading.this.isStart=false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorSet.start();
    }

    public int dpToPx(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(1, dpVal, context.getResources().getDisplayMetrics());
    }

    public void setColor(int colorId) {
        this.color = this.getResources().getColor(colorId);
    }
}
