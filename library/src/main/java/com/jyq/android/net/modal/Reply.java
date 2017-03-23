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

import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2016/10/15.
 */

public class Reply extends BaseComment {

    @SerializedName("refer_id")
    public int referId;

    @SerializedName("refered_count")
    public int referCount;

    @SerializedName("refer_short_text")
    public String refer_short_text;

    ///////////////////////////////////////////////////////////////////////////
    // 引用用户信息
    ///////////////////////////////////////////////////////////////////////////
    @SerializedName("refer_user_role_id")
    public int referAuthorID;
    @SerializedName(value = "refer_user_name",alternate = {"refer_user_role_name"})
    public String referAuthorName;
    public User getReferUser(){
        return new User.Builder()
                .logicId(referAuthorID)
                .name(referAuthorName)
                .build();
    }

    public SpannableStringBuilder getContactComment() {
        ForegroundColorSpan nameSpan = new ForegroundColorSpan(Color.parseColor("#295b9c"));
        SpannableStringBuilder builder = new SpannableStringBuilder();
        if (referId != 0) {
            builder.append(getAuthor().name);
            builder.setSpan(nameSpan, 0, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.append("回复");
            builder.append(referAuthorName)
                    .append(":");
            builder.setSpan(nameSpan, getAuthor().name.length(), builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//            return Html.fromHtml(CoreKit.getResources().getString(R.string.dynamic_replyWithReply,getAuthor().name,
//                    referAuthorName,comment)).toString();
        } else {
            builder.append(getAuthor().name)
                    .append(":");
            builder.setSpan(nameSpan, 0, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        builder.append(comment);
        return builder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reply reply = (Reply) o;
        if (id != reply.id) return false;
        if (referId != reply.referId) return false;
        if (referAuthorID != reply.referAuthorID) return false;
        if (referAuthorName != null ? !referAuthorName.equals(reply.referAuthorName) : reply.referAuthorName != null)
            return false;
        return refer_short_text != null ? refer_short_text.equals(reply.refer_short_text) : reply.refer_short_text == null;

    }

    @Override
    public int hashCode() {
        int result = referId;
        result = 31 * result + id;
        result = 31 * result + referAuthorID;
        result = 31 * result + (referAuthorName != null ? referAuthorName.hashCode() : 0);
        result = 31 * result + (refer_short_text != null ? refer_short_text.hashCode() : 0);
        return result;
    }
}
