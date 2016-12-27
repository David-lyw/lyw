package com.lyw.view.pulltorefresh;

import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;


/**
 * Created by David on 16/11/8.
 */
public class RectF implements Parcelable {
    public static final Creator<RectF> CREATOR = null;
    public float bottom;
    public float left;
    public float right;
    public float top;

    public RectF() {
        throw new RuntimeException("Stub");
    }

    public RectF(float left, float top, float right, float bottom) {
        throw new RuntimeException("Stub");
    }

    public RectF(RectF r) {
        throw new RuntimeException("Stub");
    }

    public RectF(Rect t) {
        throw new RuntimeException("Stub");
    }

    public boolean equals(Object o) {
        throw new RuntimeException("Stub");
    }

    public int hashCode() {
        throw new RuntimeException("Stub");
    }

    public String toString() {
        throw new RuntimeException("Stub");
    }

    public final boolean isEmpty() {
        throw new RuntimeException("Stub");
    }

    public final float width() {
        throw new RuntimeException("Stub");
    }

    public final float height() {
        throw new RuntimeException("Stub");
    }

    public final float centerX() {
        throw new RuntimeException("Stub");
    }

    public final float centerY() {
        throw new RuntimeException("Stub");
    }

    public void setEmpty() {
        throw new RuntimeException("Stub");
    }

    public void set(float left, float top, float right, float bottom) {
        throw new RuntimeException("Stub");
    }

    public void set(RectF src) {
        throw new RuntimeException("Stub");
    }

    public void set(Rect src) {
        throw new RuntimeException("Stub");
    }

    public void offset(float dx, float dy) {
        throw new RuntimeException("Stub");
    }

    public void offsetTo(float newLeft, float newTop) {
        throw new RuntimeException("Stub");
    }

    public void inset(float dx, float dy) {
        throw new RuntimeException("Stub");
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        throw new RuntimeException("Stub");
    }

    @Override
    public int describeContents() {
        throw new RuntimeException("Stub");
    }

}
