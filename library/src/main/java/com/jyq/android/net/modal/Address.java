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

/**
 * Created by Administrator on 2016/8/31.
 */
public class Address {
    @SerializedName("province")
    public int provinceId;
    @SerializedName("city")
    public int cityId;
    @SerializedName("area")
    public int areaId;
    @SerializedName("addr_text")
    public String fullAddress;
    @SerializedName("street")
    public String street;

    private Address() {
        //no instance
    }
    private Address(Builder builder) {
        provinceId = builder.provinceId;
        cityId = builder.cityId;
        areaId = builder.areaId;
        fullAddress = builder.fullAddress;
        street = builder.street;
    }

    public static final class Builder {
        private int provinceId;
        private int cityId;
        private int areaId;
        private String fullAddress;
        private String street;

        public Builder() {
        }

        public Builder provinceId(int val) {
            provinceId = val;
            return this;
        }

        public Builder cityId(int val) {
            cityId = val;
            return this;
        }

        public Builder areaId(int val) {
            areaId = val;
            return this;
        }

        public Builder fullAddress(String val) {
            fullAddress = val;
            return this;
        }

        public Builder street(String val) {
            street = val;
            return this;
        }

        public Address build() {
            return new Address(this);
        }
    }
}
