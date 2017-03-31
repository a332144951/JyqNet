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
 * Created by Administrator on 2017/3/21.
 */

public class UserTitle {

    @SerializedName("id")
    private int titleId;
    @SerializedName("text")
    private String titleName;
    @SerializedName("role")
    public @User.UserRole String userType;
    private UserTitle() {
        //no instance
    }

    private UserTitle(Builder builder) {
        titleId = builder.titleId;
        titleName = builder.titleName;
    }

    public int getTitleId() {
        return titleId;
    }

    public String getTitleName() {
        return titleName;
    }

    public static final class Builder {
        private int titleId;
        private String titleName;

        public Builder() {
        }

        public Builder titleId(int val) {
            titleId = val;
            return this;
        }

        public Builder titleName(String val) {
            titleName = val;
            return this;
        }

        public UserTitle build() {
            return new UserTitle(this);
        }
    }
}
