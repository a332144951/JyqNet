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
 * Created by Administrator on 2016/11/30.
 */

public class Invite {
    @SerializedName("invite_id")
    public int id;
    @SerializedName("from_user_role_id")
    private int fromUserId;
    @SerializedName("title")
    private String fromUserTitle;
    @SerializedName("text")
    public String content;
    @SerializedName("invite_type")
    public int inviteType;
    @SerializedName("created_at")
    private long time;
    @SerializedName("from_user_role_name")
    private String fromUserName;

    public User fromUser(){
        return new User.Builder()
                .logicId(fromUserId)
                .name(fromUserName)
                .titleName(fromUserTitle).build();
    }

}
