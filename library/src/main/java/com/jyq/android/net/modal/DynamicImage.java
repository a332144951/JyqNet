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

import android.util.Log;


import com.jyq.android.net.cache.HttpCache;
import com.jyq.android.net.upload.image.UploadImageUtils;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/10/19.
 */

public class DynamicImage extends AppImage implements Serializable {
    private String imageUrl;

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public DynamicImage(String strings){
        this.imageUrl=strings;
    }

    private static final String TAG = "DynamicImage";
    public String getImageUrl() {
        String url= UploadImageUtils.contactWaterMarker(UploadImageUtils.contactFullImageUrl(HttpCache.getInstance().getImageHost(),imageUrl),
                HttpCache.getInstance().getLoginUser().getSchool().name);
        Log.e(TAG, "getImageUrl: "+url );
        return url;
    }
    public String getImgKey(){
        return imageUrl;
    }

    @Override
    String getFullImageUrl() {
        return getImageUrl();
    }
}
