package com.lansus.debugginghelper.service;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.IBinder;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewAnimationUtils;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

import com.lansus.debugginghelper.R;


/**
 * @author lansus
 *  2016.11.18 14:24
 * This   service can  build a suspended view,and don't need permission .
 *
 */

public class FloatService extends Service {

    WindowManager wm = null;
    WindowManager.LayoutParams wmParams = null;
    View view;
    private float mTouchStartX;
    private float mTouchStartY;
    private float x;
    private float y;
    int state;

    ImageView ivClose;
    private float StartX;
    private float StartY;
    int delayTime=1000;
    @Override
    public void onCreate() {

        Log.d("FloatService", "onCreate");
        super.onCreate();

        view = LayoutInflater.from(this).inflate(R.layout.panel_float, null);

        ivClose = (ImageView) view.findViewById(R.id.iv_panel_float_close);
        ivClose.setVisibility(View.GONE);


        createView();
        handler.postDelayed(task, delayTime);

    }

    private void createView() {
        SharedPreferences shared = getSharedPreferences("float_flag",
                Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        editor.putInt("float", 1);
        editor.apply();
        wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
         wmParams = new WindowManager.LayoutParams();
        wmParams.type =WindowManager.LayoutParams.TYPE_TOAST;
        wmParams.flags |= 8;
        wmParams.gravity = Gravity.START| Gravity.TOP;
        wmParams.x = 600;
        wmParams.y = 300;
        wmParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        wmParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        wmParams.format = 1;

        wm.addView(view, wmParams);

        view.setOnLongClickListener(new OnLongClickListener() {

            @Override
            public boolean onLongClick(View arg0) {

                showImg();
                return false;
            }
        });


        view.setOnTouchListener(new OnTouchListener() {
            long downTime;
            public boolean onTouch(View v, MotionEvent event) {
                x = event.getRawX();
                y = event.getRawY();
                Log.i("currP", "currX" + x + "====currY" + y);
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        state = MotionEvent.ACTION_DOWN;
                        downTime=System.currentTimeMillis();
                        StartX = x;
                        StartY = y;
                        mTouchStartX = event.getX();
                        mTouchStartY = event.getY()+65;
                        updateViewPosition();


                        break;
                    case MotionEvent.ACTION_MOVE:
                        state = MotionEvent.ACTION_MOVE;
                        updateViewPosition();
                        break;

                    case MotionEvent.ACTION_UP:
                        state = MotionEvent.ACTION_UP;
                        if(System.currentTimeMillis()-downTime<80) {
                            //过滤为单击事件




                        }
                        updateViewPosition();

                        mTouchStartX = mTouchStartY = 0;
                        break;

                }
                return false;
            }
        });

        ivClose.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 关闭服务
                Intent serviceStop = new Intent();
                serviceStop.setClass(FloatService.this, FloatService.class);
                stopService(serviceStop);
            }
        });

    }

    public void showImg() {
        if (Math.abs(x - StartX) < 1.5 && Math.abs(y - StartY) < 1.5
                && !ivClose.isShown()) {
            ivClose.setVisibility(View.VISIBLE);
        } else if (ivClose.isShown()) {
            ivClose.setVisibility(View.GONE);
        }
    }

    private Handler handler = new Handler();
    private Runnable task = new Runnable() {
        public void run() {


            handler.postDelayed(this, delayTime);
            wm.updateViewLayout(view, wmParams);
        }
    };


    private void updateViewPosition() {
        wmParams.x = (int) (x - mTouchStartX);
        wmParams.y = (int) (y - mTouchStartY);
        wm.updateViewLayout(view, wmParams);
    }


    @Override
    public void onDestroy() {
        handler.removeCallbacks(task);
        Log.d("FloatService", "onDestroy");
        wm.removeView(view);
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

  
    public void  roundLoad(View myView){
        int cx = (myView.getLeft() + myView.getRight()) / 2;
        int cy = (myView.getTop() + myView.getBottom()) / 2;

        // get the final radius for the clipping circle  
        int finalRadius = Math.max(myView.getWidth(), myView.getHeight());
        AnimatorSet animatorSet = new AnimatorSet();
        // create the animator for this view (the start radius is zero)  
        Animator anim =
                ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0, finalRadius);
        anim.setDuration(1000);
        anim.setInterpolator(new AccelerateInterpolator());

        Animator anim1 = ObjectAnimator.ofFloat(myView, "translationZ", 0f,50f);
        anim1.setDuration(1500);
        anim1.setInterpolator(new AccelerateInterpolator());

        animatorSet.play(anim).with(anim1);
        // make the view visible and start the animation  
        myView.setVisibility(View.VISIBLE);
        animatorSet.start();
    }
}