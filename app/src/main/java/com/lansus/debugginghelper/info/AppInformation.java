package com.lansus.debugginghelper.info;

import java.util.HashMap;

/**
 * Created by Lansus on 2016/11/22.
 * 邮箱：wangjun@win-sky.com.cn
 */

public class AppInformation {

    private String name;
    private String versionCode;
    private HashMap<String,String> ips;

    public HashMap<String, String> getIps() {
        return ips;
    }

    public void setIps(HashMap<String, String> ips) {
        this.ips = ips;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String serverName;
}
