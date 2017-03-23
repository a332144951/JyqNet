package com.jyq.android.net.modal;

import com.google.gson.annotations.SerializedName;

/**
 * Author : GuoL
 * Create at 2016-10-26 14:45
 */

public class Area {
    public Area(int id, String name){
        this.id=id;
        this.name=name;
    }
    //内容id
    @SerializedName("id")
    public int id;
    //名称
    @SerializedName("name")
    public String name;
    //父id
    @SerializedName("parentid")
    public  String parentId;
    //拼音首字母(首个)
    @SerializedName("initial")
    public String initial;
    //拼音首字母(全)
    @SerializedName("initials")
    public String initials;
    //拼音
    @SerializedName("pinyin")
    public String pinyin;
    //后缀,有 省 市 区
    @SerializedName("suffix")
    public String suffix;
    //地区代码
    @SerializedName("code")
    public String code;
    //排序字段
    @SerializedName("order")
    public String order;
}
