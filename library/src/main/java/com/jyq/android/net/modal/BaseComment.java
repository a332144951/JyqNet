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
 * Created by Administrator on 2016/10/20.
 */

public class BaseComment implements Serializable {
    @SerializedName("id")
    public int id;
    @SerializedName(value = "content",alternate = {"comments","contents"})
    public String comment;
    @SerializedName("images")
    private List<String> imageUrls;
    @SerializedName("can_add_score")
    private int scoreAdded;
    @SerializedName("score")
    public int score;
    @SerializedName("create_time")
    private long createTime;

    ///////////////////////////////////////////////////////////////////////////
    // 作者信息
    ///////////////////////////////////////////////////////////////////////////
    @SerializedName(value = "author_id",alternate = {"user_role_id"})
    private int authorId;
    @SerializedName(value = "name",alternate = {"author_name"})
    private String name;
    @SerializedName("avatar")
    private String avatar;
    @SerializedName("accid")
    private String imAccount;
    public User getAuthor() {
        return new User.Builder()
                .avatar(avatar)
                .IMAccid(imAccount)
                .logicId(authorId)
                .name(name)
                .build();
    }

    protected transient ArrayList<DynamicImage> images;
    protected transient ArrayList<String> Urls;

    public long getCreateTime(){
        return createTime*1000;
    }

    public void addScore(int score){
        this.score+=score;
        this.scoreAdded=0;
    }

    public ArrayList<DynamicImage> getImages(){
        if(images==null) {
            images=new ArrayList<>();
            if (imageUrls!=null) {
                for (String str : imageUrls) {
                    images.add(new DynamicImage(str));
                }
            }
        }
        return images;
    }
    public ArrayList<String> getImageUrls(){
            if (Urls==null){
                Urls=new ArrayList<>();
                for (DynamicImage image:getImages()){
                    Urls.add(image.getImageUrl());
                }
            }
        return Urls;
    }


    public boolean enableScoreAdded(){
        return score==1;
    }
}
