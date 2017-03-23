package com.jyq.android.net.modal;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Administrator on 2016-10-13.
 */

public class Medicine implements Serializable{
    @SerializedName("id")
    public int medicine_id;
    @SerializedName("content")
    public String content;
    @SerializedName("finish_date")
    private long finish_date;
    @SerializedName("medicine_time")
    private long medicine_time;

    ///////////////////////////////////////////////////////////////////////////
    // 宝宝信息
    ///////////////////////////////////////////////////////////////////////////
    @SerializedName("name")
    public String name;
    @SerializedName("baby_id")
    public int baby_id;
    @SerializedName("class_name")
    public String className;
    public Baby getBaby(){
        return new Baby.Builder()
                .id(baby_id)
                .name(name)
                .className(className)
                .build();
    }


    public String getTime(){
        return android.text.format.DateFormat.format("yyyy-MM-dd HH:mm:ss",finish_date==0?medicine_time*1000:finish_date*1000).toString();
    }
}
