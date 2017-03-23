package com.jyq.android.net.modal;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Author : GuoL
 * Create at 2016-10-26 14:45
 */

public class Assess extends BaseComment{

    //日期
    @SerializedName("date")
    public String dateString;
    //月评价0，年总结1
    @SerializedName("type")
    public int type;
    //审核时间
    @SerializedName("audit_time")
    public Long audit_time;
//    //新创建的动态状态, 0待审核 1已审核
//    @SerializedName("passed")
//    public int passed;



    ///////////////////////////////////////////////////////////////////////////
    // 宝宝信息
    ///////////////////////////////////////////////////////////////////////////
    @SerializedName("baby_id")
    private int babyId;
    //宝宝姓名
    @SerializedName("baby_name")
    private String baby_name;
    public Baby getBaby(){
        return new Baby.Builder()
                .id(babyId)
                .name(baby_name)
                .build();
    }



//    public boolean passed(){
//        return passed==1;
//    }
}
