package com.lansus.debugginghelper.test;


import com.lansus.debugginghelper.DebugHelPer;

/**
 * Created by Lansus on 2016/11/22.
 * 邮箱：wangjun@win-sky.com.cn
 */

public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DebugHelPer.init(getApplicationContext());
    }
}
