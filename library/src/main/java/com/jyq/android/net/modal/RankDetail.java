package com.jyq.android.net.modal;

import android.text.format.DateFormat;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/8/17.
 */
public class RankDetail implements Serializable {
    @SerializedName("user_role_id")
    public int user_role_id;
    @SerializedName("score")
    public int score;
    //1发布动态 2回复动态 3回复文章 (还有更多，随后补全)
    @SerializedName("type")
    private int type;
    @SerializedName("content")
    public String content;
    @SerializedName("create_time")
    private long create_time;
    public String getTime(){
        return DateFormat.format("yyyy-MM-dd HH:mm:ss",create_time*1000).toString();
    }
    public String getReason(){
        String reason="";
        switch (type){
            case 1:
                reason="发布动态";
                break;
            case 2:
                reason="回复动态";
                break;
            case 3:
                reason="回复文章";
                break;
            case 4:
                reason="分享文章";
                break;
            case 5:
                reason="发宝宝评价";
                break;
            case 6:
                reason="回复宝宝评价";
                break;
            case 7:
                reason="分享照片";
                break;
            case 8:
                reason="参与投票";
                break;
            case 9:
                reason="发红花";
                break;
            case 10:
                reason="分享招生";
                break;
            case 11:
                reason="分享招聘";
                break;
            case 12:
                reason="发教师评价";
                break;
        }
        return reason;
    }
}
