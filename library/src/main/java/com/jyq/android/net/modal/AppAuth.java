package com.jyq.android.net.modal;

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

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/3/20.
 */

public class AppAuth {
    @SerializedName(value = "token")
    public String accessToken;
    @SerializedName(value = "token_ttl")
    public long avaliable;
    @SerializedName(value = "refresh_ttl")
    public long refreshAvaliable;
    @SerializedName(value = "init")
    public boolean init;

    @SerializedName("yx_accid")
    private String imAccount;
    @SerializedName("yx_token")
    private String imAccessToken;

    public ImInfo getImInfo(){
        return new ImInfo.Builder()
                .accessToken(imAccessToken)
                .account(imAccount)
                .build();
    }
}
