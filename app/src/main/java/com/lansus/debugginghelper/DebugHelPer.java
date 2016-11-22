package com.lansus.debugginghelper;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.google.gson.Gson;
import com.lansus.debugginghelper.info.AppInformation;
import com.lansus.debugginghelper.util.SPUtils;

import org.json.JSONObject;

/**
 * Created by Lansus on 2016/11/21.
 * 邮箱：wangjun@win-sky.com.cn
 * register it in application  or activity.and initialize setting.
 */

public class DebugHelPer {

    public  static void  init(Context mContext){
        PackageManager packageManager = mContext.getPackageManager();
        try {
            PackageInfo packageInfo=packageManager.getPackageInfo(mContext.getPackageName(),0);
            ApplicationInfo applicationInfo=packageManager.getApplicationInfo(mContext.getPackageName(),0);
            AppInformation info=new AppInformation();
            info.setName((String) packageManager.getApplicationLabel(applicationInfo) );
            info.setVersionCode(packageInfo.versionName);
            Gson gson=new Gson();
            SPUtils.put(mContext,"AppInfo",gson.toJson(info));
            Log.i("log",gson.toJson(info));

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


    }









}
