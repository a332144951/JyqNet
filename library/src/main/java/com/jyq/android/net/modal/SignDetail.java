package com.jyq.android.net.modal;

import com.google.gson.annotations.SerializedName;
import com.jyq.android.net.R;

import java.util.List;

/**
 * Created by Administrator on 2016-10-21.
 */

public class SignDetail {
    //签到日期
    @SerializedName(value="day")
    public String day;
    //签到时间
    @SerializedName(value="checkin_time")
    public String checkin_time;
    //签到状态
    @SerializedName("status")
    public int status;

    public int getBackgroundRes() {
        switch (status){
            case 0:
                backgroundRes=android.R.color.transparent;
                break;
            case 1:
            case 2:
                backgroundRes= R.drawable.signin;
                break;
//            case 2:
//                backgroundRes=R.drawable.handsignin;
//                break;
            case 3:
                backgroundRes=R.drawable.nosignin;
                break;
        }
        return backgroundRes;
    }


    public int backgroundRes;
}
