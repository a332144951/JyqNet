package com.jyq.android.net;

/*
                   _ooOoo_
                  o8888888o
                  88" . "88
                  (| -_- |)
                  O\  =  /O
               ____/`---'\____
             .'  \\|     |//  `.
            /  \\|||  :  |||//  \
           /  _||||| -:- |||||-  \
           |   | \\\  -  /// |   |
           | \_|  ''\---/''  |   |
           \  .-\__  `-`  ___/-. /
         ___`. .'  /--.--\  `. . __
      ."" '<  `.___\_<|>_/___.'  >'"".
     | | :  `- \`.;`\ _ /`;.`/ - ` : | |
     \  \ `-.   \_ __\ /__ _/   .-` /  /
======`-.____`-.___\_____/___.-`____.-'======
                   `=---='
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
           佛祖保佑       永无BUG
 */


import android.util.Pair;

import com.jyq.android.net.cache.HttpCache;
import com.jyq.android.net.storage.HttpPreference;


/**
 * Created by Administrator on 2017/3/20.
 */

public class HttpKit {
    private static final HttpKit ourInstance = new HttpKit();

    public static HttpKit getInstance() {
        return ourInstance;
    }


    public static void init(String baseUrl) {
        HttpCache.getInstance().updateAppHost(baseUrl);
    }

    public String getBaseUrl(){
        return HttpCache.getInstance().getAppHost();
    }

    private HttpKit() {
        HttpPreference.getInstance().updateAuthKey("Authorization");
//        manager = new HttpManager(getBaseUrl());
    }
    public Pair<String, String> getAuthenticator() {
        return Pair.create(HttpPreference.getInstance().getAuthKey(), HttpPreference.getInstance().getAuthValue());
    }

    public void updateAuthenticator(String token) {
        HttpPreference.getInstance().updateAuthValue(String.format("Bearer %s", token));
    }

    public <S> S getService(Class<S> serviceClass) {
        return HttpManager.getInstance().getRetrofit().create(serviceClass);
    }
}
