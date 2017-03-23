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

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/21.
 */

public class ImInfo implements Serializable{
    private String account;
    private String accessToken;

    private ImInfo(){

    }
    private ImInfo(Builder builder) {
        account = builder.account;
        accessToken = builder.accessToken;
    }

    public String getAccount() {
        return account;
    }

    public String getAccessToken() {
        return accessToken;
    }


    public static final class Builder {
        private String account;
        private String accessToken;

        public Builder() {
        }

        public Builder account(String val) {
            account = val;
            return this;
        }

        public Builder accessToken(String val) {
            accessToken = val;
            return this;
        }

        public ImInfo build() {
            return new ImInfo(this);
        }
    }
}
