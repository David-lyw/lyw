package com.lyw.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.LinearLayout;

import com.lyw.utils.DensityUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import business.esale.hivedrp.com.mylibrary.R;

/**
 * Created by David on 16/12/22.
 * Banner:横幅。
 * 可以加载ImageView ,也可以是自定义的GifView.
 */
public class BannerView extends LinearLayout {
    private ViewPager mViewPager;
    private LinearLayout dotsLayout;
    private List<View> mViews;
    private List<View> dots;
    private int currentItem;
    boolean nowAction;
    private ScheduledExecutorService scheduledExecutorService;//提供时间排程的功能
    private Handler handler;

    public BannerView(Context context) {
        this(context, (AttributeSet) null);
    }

    public BannerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BannerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.currentItem = 0;
        this.nowAction = false;
        this.handler = new Handler() {
            public void handleMessage(Message msg) {
                BannerView.this.mViewPager.setCurrentItem(BannerView.this.currentItem);
            }
        };

        this.init();
    }

    private void init() {
        View view = LayoutInflater.from(this.getContext()).inflate(R.layout.banner, this);
        this.mViewPager = (ViewPager) view.findViewById(R.id.vp);
        this.dotsLayout = (LinearLayout) view.findViewById(R.id.dots);
        this.dots = new ArrayList();

//        this.setClickable(true);
//        this.setFocusable(true);

    }

    /**
     * 为控件设置资源。
     *
     * @param imageViews
     */
    public void setViewPagerViews(List<View> imageViews) {
        this.mViews = imageViews;
        this.dots.clear();
        this.dotsLayout.removeAllViews();

        for (int i = 0; i < this.mViews.size(); ++i) {
            View dot = new View(this.getContext());
            LayoutParams params = new LayoutParams(DensityUtils.px2dip(this.getContext(), 20.0F), DensityUtils.px2dip(this.getContext(), 20.0F));
            params.leftMargin = DensityUtils.px2dip(this.getContext(), 8.0F);
            params.rightMargin = DensityUtils.px2dip(this.getContext(), 8.0F);
            if (i == 0) {
                dot.setBackgroundResource(R.drawable.dot_selected);
            } else {
                dot.setBackgroundResource(R.drawable.dot_normal);
            }

            dot.setLayoutParams(params);
            this.dotsLayout.addView(dot);
            this.dots.add(dot);
        }

        this.mViewPager.setAdapter(new BannerView.BannerAdapter());
        this.mViewPager.setCurrentItem(0);
        this.mViewPager.setOnPageChangeListener(new BannerView.BannerPageChangeListener());
    }

    /**
     * bannerView.start():自动滑动(scroll)。
     */
    public void start() {
        this.scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        this.scheduledExecutorService.scheduleAtFixedRate(new BannerView.ScrollTask(), 1L, 5L, TimeUnit.SECONDS);
    }

    public void shutdown() {
        this.scheduledExecutorService.shutdown();
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        return super.onTouchEvent(event);
//    }

    //屏幕
//    public boolean onTouchEvent(MotionEvent event) {
//
//        switch (event.getAction()) {
//            case 0:
//                Log.i("lyw","ACTION_DOWN");
//                //return false;//ACTION_DOWN
//                break;
//            case 1:
//                Log.i("lyw","ACTION_UP");
//                //return false;//ACTION_UP
//                break;
//            case 2:
//                Log.i("lyw","ACTION_MOVE");
//               // return false;//ACTION_MOVE
//                break;
//            case 3:
//                Log.i("lyw","ACTION_CANCEL");
//                //return false;//ACTION_CANCEL
//                break;
//            default:
//                Log.i("lyw","default");
//                //return false;
//                break;
//
//        }
//        return super.onTouchEvent(event);
//
//    }

    /**
     * 自定义adapter.
     */
    private class BannerAdapter extends PagerAdapter {
        private BannerAdapter() {
        }

        public int getCount() {
            return BannerView.this.mViews.size() < 2 ? BannerView.this.mViews.size() : 1073741823;
        }

        public Object instantiateItem(View arg0, int position) {
            if (position != BannerView.this.currentItem) {
                View view;
                if (position % BannerView.this.mViews.size() < 0) {
                    view = (View) BannerView.this.mViews.get(BannerView.this.mViews.size() + position);
                } else {
                    view = (View) BannerView.this.mViews.get(position % BannerView.this.mViews.size());
                }

                ViewParent vp = view.getParent();
                if (vp != null) {
                    ViewGroup parent = (ViewGroup) vp;
                    parent.removeView(view);
                }

                ((ViewPager) arg0).addView(view);
                return view;
            } else {
                ((ViewPager) arg0).addView((View) BannerView.this.mViews.get(0));
                return BannerView.this.mViews.get(0);
            }
        }

        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        public void destroyItem(ViewGroup container, int position, Object object) {
        }
    }

    /**
     * PageChangeListener.
     */
    private class BannerPageChangeListener implements ViewPager.OnPageChangeListener {
        private BannerPageChangeListener() {
        }

        public void onPageSelected(int position) {
            BannerView.this.currentItem = position;
            this.changeDotsBg(BannerView.this.currentItem % BannerView.this.mViews.size());
        }

        public void onPageScrollStateChanged(int arg0) {
            if (arg0 == 0) {
                BannerView.this.nowAction = false;
            }

            if (arg0 == 1) {
                BannerView.this.nowAction = true;
            }

            if (arg0 == 2) {
            }

        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        private void changeDotsBg(int currentitem) {
            for (int i = 0; i < BannerView.this.dots.size(); ++i) {
                ((View) BannerView.this.dots.get(i)).setBackgroundResource(R.drawable.dot_normal);
            }
            ((View) BannerView.this.dots.get(currentitem)).setBackgroundResource(R.drawable.dot_selected);
        }
    }

    /**
     * ScrollTask implements Runnable
     */
    private class ScrollTask implements Runnable {
        private ScrollTask() {
        }

        public void run() {
            synchronized (BannerView.this.mViewPager) {
                if (BannerView.this.mViews != null && BannerView.this.mViews.size() > 1 && !BannerView.this.nowAction) {
                    BannerView.this.currentItem = BannerView.this.currentItem + 1;
                    BannerView.this.handler.obtainMessage().sendToTarget();
                }

            }
        }
    }
}
