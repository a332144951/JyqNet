package com.jyq.android.net.modal;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Author : GuoL
 * Create at 2016-12-17 10:22
 */

public class ReturnObject implements Serializable{
    @SerializedName(value = "moment_id",alternate = {"rate_id"})
    public int logicId;
    @SerializedName("passed")
    public int passed;
    @SerializedName("score")
    public int score;
}
