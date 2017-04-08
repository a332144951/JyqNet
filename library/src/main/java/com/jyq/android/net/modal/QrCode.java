package com.jyq.android.net.modal;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/5/25.
 */
public class QrCode implements Serializable {
    @SerializedName("data")
    public String data;
}
