package com.jyq.android.net.modal;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2016-10-21.
 */

public class SignGrade {
    //签到人数
    @SerializedName(value="checked_num")
    public int checked_num;
    //应到人数
    @SerializedName(value="should_num",alternate = {"sum"})
    public String should_num;
    //班级列表
    @SerializedName("class_list")
    public List<Grade> class_list;
    //签到人员列表
    @SerializedName("checked")
    public SignList checkedPersonList;
    //未签到人员列表
    @SerializedName("unchecked")
    public SignList unPersonList;

    public class SignList{
        @SerializedName("count")
        private int totalNum;
        @SerializedName("list")
        private List<Baby> babyList;
    }

}
