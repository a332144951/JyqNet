package com.jyq.android.net.modal;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Author : GuoL
 * Create at 2016-11-21 15:21
 */

public class RedFlower implements Serializable {
    /**
     *  "flower_count": 39,
     "baby_name": "宝宝1",
     "class_name": "xxx"

     "type": "0",
     "flower_time": "1476406166"

     2016-10-12": {
     "red": 11,
     "gold": 0
     */
    @SerializedName("flower_count")
    public int flower_count;
    @SerializedName("baby_name")
    public String baby_name;
    @SerializedName("class_name")
    public String class_name;
    //类型 0红花 1金花
    @SerializedName("type")
    public int type;
    @SerializedName("flower_time")
    public Long flower_time;
    @SerializedName("date")
    public String date;
    @SerializedName("red")
    public int red;
    @SerializedName("gold")
    public int gold;
}
