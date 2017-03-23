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

import android.support.annotation.IntDef;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/9/2.
 */
public class Grade implements Serializable {
    public final static int STATUS_NORMAL=0;
    public final static int STATUS_GRADUATED=1;

    private Grade(Builder builder) {
        id = builder.id;
        name = builder.name;
        imId = builder.imId;
        status = builder.status;
        checked_num = builder.checked_num;
        should_num = builder.should_num;
    }

    @IntDef({STATUS_GRADUATED,STATUS_NORMAL})
    public @interface Status{}


    @SerializedName("id")
    public int id;
    @SerializedName("name")
    public String name;
    @SerializedName("yx_group_id")
    public String imId;
    @SerializedName("status")
    public @Status int status;
    //班内已签到总人数
    @SerializedName("checked_num")
    public int checked_num;
    //班内应该签到总人数
    @SerializedName("should_num")
    public int should_num;


    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imId='" + imId + '\'' +
                ", status=" + status +
                ", checked_num=" + checked_num +
                ", should_num=" + should_num +
                '}';
    }


    public static final class Builder {
        private int id;
        private String name;
        private String imId;
        private int status;
        private int checked_num;
        private int should_num;

        public Builder() {
        }

        public Builder id(int val) {
            id = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder imId(String val) {
            imId = val;
            return this;
        }

        public Builder status(int val) {
            status = val;
            return this;
        }

        public Builder checked_num(int val) {
            checked_num = val;
            return this;
        }

        public Builder should_num(int val) {
            should_num = val;
            return this;
        }

        public Grade build() {
            return new Grade(this);
        }
    }
}
