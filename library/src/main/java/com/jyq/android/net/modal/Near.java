package com.jyq.android.net.modal;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/5/25.
 */
public class Near implements Serializable {

    @SerializedName("lng")
    public double lng;
    @SerializedName("lat")
    public double lat;
    @SerializedName("distance")
    public double distance;
    public String type;


    private Near(Builder builder) {
        lng = builder.lng;
        lat = builder.lat;
        distance = builder.distance;
        type = builder.type;
    }


    public static final class Builder {
        private double lng;
        private double lat;
        private double distance;
        private String type;

        public Builder() {
        }

        public Builder lng(double val) {
            lng = val;
            return this;
        }

        public Builder lat(double val) {
            lat = val;
            return this;
        }

        public Builder distance(double val) {
            distance = val;
            return this;
        }

        public Builder type(String val) {
            type = val;
            return this;
        }

        public Near build() {
            return new Near(this);
        }
    }
}
