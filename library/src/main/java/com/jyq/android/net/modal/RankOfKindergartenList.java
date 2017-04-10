package com.jyq.android.net.modal;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/2/26.
 */
public class RankOfKindergartenList implements Serializable {
    @SerializedName("list")
    public List<Rank> list = new ArrayList<Rank>();
    @SerializedName("className")
    public String ClassName;
}
