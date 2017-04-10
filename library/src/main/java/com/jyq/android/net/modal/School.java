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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/2.
 */
public class School  implements Serializable{
    public int id;
    @SerializedName("name")
    public String name;
    @SerializedName("build_date")
    public String buildDate;
    @SerializedName("contact")
    public String contact;
    @SerializedName("phone")
    public String phone;
    @SerializedName("school_image_id")
    public String imageId;
    @SerializedName("images")
    public List<ImageBean> images;
    @SerializedName("url")
    public String targetUrl;

    ///////////////////////////////////////////////////////////////////////////
    // 位置信息
    ///////////////////////////////////////////////////////////////////////////
    @SerializedName("distance")
    private double distance;
    @SerializedName("lng")
    private double longitude;
    @SerializedName("lat")
    private double latitude;
    public Near getLocation(){
        return new Near.Builder()
                .lng(longitude)
                .lat(latitude)
                .distance(distance)
                .build();
    }


    ///////////////////////////////////////////////////////////////////////////
    // 地址信息
    ///////////////////////////////////////////////////////////////////////////
    @SerializedName("province")
    private int province;
    @SerializedName("city")
    private int city;
    @SerializedName("area")
    private int area;
    @SerializedName("street")
    private String street;
    @SerializedName("addr_text")
    private String addr_text;
    public Address getAddress(){
        return new Address.Builder()
                .street(street)
                .provinceId(province)
                .cityId(city)
                .areaId(area)
                .fullAddress(addr_text)
                .build();
    }


    public School(){};



    public List<String> getImageUrls(){
        List<String> urls=new ArrayList<>();
        if (images!=null&&images.size()!=0){
            for (ImageBean image:images){
                urls.add(image.getImageUrl());
            }
        }
        return urls;
    }

    private School(Builder builder) {
        id = builder.id;
        name = builder.name;
        buildDate = builder.buildDate;
        contact = builder.contact;
        phone = builder.phone;
        imageId = builder.imageId;
        distance = builder.distance;
        longitude = builder.longitude;
        latitude = builder.latitude;
        province = builder.province;
        city = builder.city;
        area = builder.area;
        street = builder.street;
        addr_text = builder.addr_text;
        images = builder.images;
    }


    public static final class Builder {
        private String name;
        private String buildDate;
        private String contact;
        private String phone;
        private String imageId;
        private double distance;
        private double longitude;
        private double latitude;
        private int province;
        private int city;
        private int area;
        private String street;
        private String addr_text;
        private List<ImageBean> images;
        private int id;

        public Builder() {
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder buildDate(String val) {
            buildDate = val;
            return this;
        }

        public Builder contact(String val) {
            contact = val;
            return this;
        }

        public Builder phone(String val) {
            phone = val;
            return this;
        }

        public Builder imageId(String val) {
            imageId = val;
            return this;
        }

        public Builder distance(double val) {
            distance = val;
            return this;
        }

        public Builder longitude(double val) {
            longitude = val;
            return this;
        }

        public Builder latitude(double val) {
            latitude = val;
            return this;
        }

        public Builder province(int val) {
            province = val;
            return this;
        }

        public Builder city(int val) {
            city = val;
            return this;
        }

        public Builder area(int val) {
            area = val;
            return this;
        }

        public Builder street(String val) {
            street = val;
            return this;
        }

        public Builder addr_text(String val) {
            addr_text = val;
            return this;
        }

        public Builder images(List<ImageBean> val) {
            images = val;
            return this;
        }

        public School build() {
            return new School(this);
        }

        public Builder id(int val) {
            id = val;
            return this;
        }
    }
}
