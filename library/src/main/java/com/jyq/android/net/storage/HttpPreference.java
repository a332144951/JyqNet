package com.jyq.android.net.storage;

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

import com.jyq.android.common.preferences.JPreference;

/**
 * Created by Administrator on 2017/3/20.
 */

public class HttpPreference extends JPreference {

    private final String KEY_AUTH_KEY="http_key_auth_key";
    private final String KEY_AUTH_VALUE="http_key_auth_value";

    private static final HttpPreference ourInstance = new HttpPreference();

   public static HttpPreference getInstance() {
        return ourInstance;
    }
    private HttpPreference(){

    }
    public String getAuthKey(){
        return getString(KEY_AUTH_KEY);
    }
    public String getAuthValue(){
        return getString(KEY_AUTH_VALUE);
    }
    public void updateAuthKey(String key){
        saveString(KEY_AUTH_KEY,key);
    }
    public void updateAuthValue(String value){
        saveString(KEY_AUTH_VALUE,value);
    }

}
