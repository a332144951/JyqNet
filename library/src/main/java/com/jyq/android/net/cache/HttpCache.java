package com.jyq.android.net.cache;

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

import com.google.gson.Gson;
import com.jyq.android.common.cache.CacheKit;
import com.jyq.android.common.cache.CommonCache;
import com.jyq.android.net.modal.ImInfo;
import com.jyq.android.net.modal.User;

/**
 * Created by Administrator on 2017/3/20.
 */

public class HttpCache {
    private static final HttpCache ourInstance = new HttpCache();

    public static HttpCache getInstance() {
        return ourInstance;
    }

    private HttpCache() {

    }

    private final String KEY_CACHE_USER = "cache_user";
    private final String KEY_CACHE_IM_ACCOUNT="cache_im_account";
    private final String KEY_CACHE_APP_HOST="cache_app_host";
    public String getAppHost(){
        return CacheKit.getInstance().getInternalCache().getAsString(KEY_CACHE_APP_HOST);
    }
    public void updateAppHost(String host){
        CacheKit.getInstance().getInternalCache().put(KEY_CACHE_APP_HOST,host);
    }
    public void updateImInfo(ImInfo info){
        CacheKit.getInstance().getInternalCache().put(KEY_CACHE_IM_ACCOUNT,info);
    }
    public ImInfo getLoginImInfo(){
        return (ImInfo) CacheKit.getInstance().getInternalCache().getAsObject(KEY_CACHE_IM_ACCOUNT);
    }
    public User getLoginUser() {
        return new Gson().fromJson(CacheKit.getInstance().getInternalCache().getAsJSONObject(KEY_CACHE_USER), User.class);
    }

    public void updateLoginUser(User user) {
        CacheKit.getInstance().getInternalCache().put(KEY_CACHE_USER, new Gson().toJson(user));
    }

    public String getImageHost() {
        return CommonCache.getInstance().getImageHost();
    }

    public void updateImageHost(String host) {
        CommonCache.getInstance().updateImageHost(host);
    }

    public void clear() {
        CacheKit.getInstance().getInternalCache().remove(KEY_CACHE_USER);
    }

}
