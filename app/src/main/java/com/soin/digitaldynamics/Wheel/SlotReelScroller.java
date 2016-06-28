package com.soin.digitaldynamics.Wheel;

import android.content.Context;
import android.os.Handler;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.widget.Scroller;

/**
 * Responsible for generating scroll values based on a distance & time given.
 * Uses 'Scroller' class to generate the scroll values.
 * 
 * @author maniselvaraj
 *
 */
public class SlotReelScroller implements Runnable {

    int delayVelocity = 45;
    /**
     * Scrolling listener interface
     */
    public interface ScrollingListener {
        /**
         * Scrolling callback called when scrolling is performed.
         * @param the distance to scroll
         */
        void onScroll(int distance);

        /**
         * Finishing callback.
         */
        void onFinished();        
    }

    private Handler mHandler;
    private Scroller mScroller;
    private ScrollingListener mScrollListener;
    int lastY = 0;
    private int distance;
    private int previousDistance;

    private int offsetY = 0;
    
    public SlotReelScroller(Context context, ScrollingListener listener) {
    	mHandler = new Handler();
    	mScroller = new Scroller(context, new AccelerateDecelerateInterpolator());
    	//mScroller = new Scroller(context, new AccelerateInterpolator());/
         //mScroller = new Scroller(context);
    	mScrollListener = listener;
    }
    
    public void scroll(int distance, int duration) {
    	this.distance = distance;
        mScroller.forceFinished(true);
    	mScroller.startScroll(0, 0, 0, distance, duration);
    	mHandler.post(this);
    }
    
    public void run() {
        mScroller.computeScrollOffset();
        int currY = mScroller.getCurrY();
        if (Math.abs(currY) != previousDistance && currY != 0) {
            mScrollListener.onScroll(currY);
        }
        if (mScroller.isFinished() == false) {
            //Post this runnable again on UI thread until all scroll values are read.
            mHandler.postDelayed(this,delayVelocity);
            delayVelocity += 1.5;
        } else {
            previousDistance = distance;
            delayVelocity= 22;
            mScrollListener.onFinished();
        }
    }
}
