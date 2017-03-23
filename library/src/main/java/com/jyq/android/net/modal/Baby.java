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
import com.jyq.android.net.cache.HttpCache;
import com.jyq.android.net.upload.image.UploadImageUtils;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/9/1.
 */
public class Baby implements Serializable{
    @SerializedName(value = "id",alternate = {"baby_id"})
    public int id;
    @SerializedName(value = "name",alternate = {"baby_name"})
    public String name;
    @SerializedName("bind_count")
    public int bindCount;
    @SerializedName("bind_card")
    private int bind_card;
    @SerializedName("gold")
    public int gold;
    @SerializedName("red")
    public int red;
    @SerializedName(value = "avatar",alternate = {"baby_avatar"})
    private String avatar;
    @SerializedName(value = "show_pic",alternate = {"baby_show_pic"})
    private String artAvatar;
    @SerializedName("py")
    public String py;




    ///////////////////////////////////////////////////////////////////////////
    // 园所信息
    ///////////////////////////////////////////////////////////////////////////
    @SerializedName("school_id")
    private int schoolId;
    @SerializedName("school_name")
    private String schoolName;
    public School getSchool(){
        return new School.Builder()
                .id(schoolId)
                .name(schoolName)
                .build();
    }
    ///////////////////////////////////////////////////////////////////////////
    // 园所信息
    ///////////////////////////////////////////////////////////////////////////
    @SerializedName("class_name")
    private String className;
    @SerializedName("class_id")
    private int classId;
    public Grade getGrade(){
        return new Grade.Builder()
                .id(classId)
                .name(className)
                .build();
    }



    private Baby() {
        //no instance
    }

    private Baby(Builder builder) {
        id = builder.id;
        classId = builder.classId;
        schoolId = builder.schoolId;
        name = builder.name;
        bindCount = builder.bindCount;
        bind_card = builder.bind_card;
        gold = builder.gold;
        red = builder.red;
        className = builder.className;
        avatar = builder.avatar;
    }

    public String getAvatar() {
        return UploadImageUtils.contactFullImageUrl(HttpCache.getInstance().getImageHost(),avatar);
    }

    public Boolean isHasCard(){
        return bind_card==1;
    }


    public static final class Builder {
        private int id;
        private int classId;
        private int schoolId;
        private String name;
        private int bindCount;
        private int bind_card;
        private int gold;
        private int red;
        private String className;
        private String avatar;

        public Builder() {
        }

        public Builder id(int val) {
            id = val;
            return this;
        }

        public Builder classId(int val) {
            classId = val;
            return this;
        }

        public Builder schoolId(int val) {
            schoolId = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder bindCount(int val) {
            bindCount = val;
            return this;
        }

        public Builder bind_card(int val) {
            bind_card = val;
            return this;
        }

        public Builder gold(int val) {
            gold = val;
            return this;
        }

        public Builder red(int val) {
            red = val;
            return this;
        }

        public Builder className(String val) {
            className = val;
            return this;
        }

        public Builder avatar(String val) {
            avatar = val;
            return this;
        }

        public Baby build() {
            return new Baby(this);
        }
    }
}
