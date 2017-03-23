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

import android.text.format.DateFormat;

import com.google.gson.annotations.SerializedName;
import com.jyq.android.net.HttpKit;

import java.io.Serializable;


/**
 * Created by Administrator on 2016/11/12.
 */

public class Article implements Serializable {

    @SerializedName("id")
    public int id;
    @SerializedName("title")
    public String title;
    @SerializedName("publish_time")
    private long publicTime;

    @SerializedName("update_time")
    private long updateTime;

    @SerializedName("summary")
    public String summary;

    @SerializedName("image_path")
    private String album;

    ///////////////////////////////////////////////////////////////////////////
    // 作者信息
    ///////////////////////////////////////////////////////////////////////////
    @SerializedName("user_role_id")
    private int authorId;

    @SerializedName("name")
    private String authorName;
    public User getAuthor(){
        return new User.Builder()
                .logicId(authorId)
                .name(authorName)
                .build();
    }


    public String getDate() {
        return DateFormat.format("M月d日", publicTime).toString();
    }

    public String getDetailUrl() {
        return new StringBuffer(HttpKit.getInstance().getBaseUrl()).append("/api/article/detail?article_id=")
                .append(id).toString();
    }

    public String getShareUrl() {
        return new StringBuffer(HttpKit.getInstance().getBaseUrl()).append("/share/article/detail?article_id=")
                .append(id).toString();
    }

    public String getAlbum() {
        return album;
    }

}
