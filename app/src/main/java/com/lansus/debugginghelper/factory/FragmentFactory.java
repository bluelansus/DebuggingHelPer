package com.lansus.debugginghelper.factory;

/**
 * Created by Lansus on 2016/11/18.
 * e-mail：wangjun@win-sky.com.cn
 * This is  factory for product fragment that model .,include  view .
 */

public class FragmentFactory {
    public static volatile FragmentFactory instance;

    /*TODO develop this library for app  to help debug simplified.this Inclide 3 models:
      TODO  Printing all log and add filter to query;
      TODO  Altering APP server adtress ，and auto add com number;
      TODO Setting
     * SinceTon
     * @return   instance    context
     */
    public FragmentFactory  getInstance(){
       if(instance==null){
           synchronized (FragmentFactory.this){
               if(instance==null){
                   instance=new FragmentFactory();
               }
           }

       }

        return instance;
    }



}
