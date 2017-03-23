package com.jyq.android.net.modal;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2015/5/25.
 */
public class RankOfMaster implements Serializable {
    @SerializedName("user_role_info")
    public Rank userInfo;
    @SerializedName("list")
    public List<User> rankList;

    
}
