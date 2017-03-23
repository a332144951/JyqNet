package com.jyq.android.net.modal;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/5/25.
 */
public class Rank implements Serializable {

    @SerializedName("rank")
    public int rank;
    @SerializedName("today_score_sum")
    public int today;
    @SerializedName("yesterday_score_sum")
    public int yesterday;
    @SerializedName("week_score_sum")
    public int week;
    ///////////////////////////////////////////////////////////////////////////
    // 用户或班级信息
    ///////////////////////////////////////////////////////////////////////////
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("school_name")
    private String school_name;
    @SerializedName(value = "score",alternate = {"sum_score"})
    private int score;
    @SerializedName("class_name")
    private String gradeName;
    @SerializedName("avatar")
    private String avatar;
    @SerializedName("title")
    private String titleName;

    public User getUser(){
        return new User.Builder()
                .logicId(id)
                .name(name)
                .avatar(avatar)
                .titleName(titleName)
                .className(gradeName)
                .schoolName(school_name)
                .score(score)
                .build();
    }


}
