package com.lyw;

import android.app.Activity;
import android.app.Application;

import java.util.ArrayList;

/**
 * Created by David on 16/12/21.
 */
public abstract class BaseApplication extends Application{
    private static int screenWidth;
    private static int screenHeight;
    private ArrayList<Activity> mActivities;
    private static BaseApplication INSTANCE;

    public BaseApplication(){}

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE=this;
        screenWidth = this.getResources().getDisplayMetrics().widthPixels;
        screenHeight = this.getResources().getDisplayMetrics().heightPixels;
        this.mActivities = new ArrayList();
    }

    public static BaseApplication getApplication() {
        return INSTANCE;
    }
    public void addActivity(Activity aty) {
        this.mActivities.add(aty);
    }
    public void removeActivity(Activity aty) {
        this.mActivities.remove(aty);
    }
    public void finishAllActivity() {
        for(int i = 0; i < this.mActivities.size(); ++i) {
            Activity activity = (Activity)this.mActivities.get(i);
            if(activity != null) {
                ((Activity)this.mActivities.get(i)).finish();
            }
        }
    }

    public void exitApp() {
        this.finishAllActivity();
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
        this.exitApp();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        this.exitApp();
    }

    public static int getScreenWidth() {
        return screenWidth;
    }

    public static int getScreenHeight() {
        return screenHeight;
    }
}
