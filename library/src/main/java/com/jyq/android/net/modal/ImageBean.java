package com.jyq.android.net.modal;

import com.google.gson.annotations.SerializedName;
import com.jyq.android.net.cache.HttpCache;
import com.jyq.android.net.upload.image.UploadImageUtils;

import java.io.Serializable;

/**
 * Author : GuoL
 * Create at 2016-10-27 16:13
 */

public class ImageBean extends AppImage implements Serializable{
    //图片内容id(删除用)
    @SerializedName("id")
    public int id;
    //园所id
    @SerializedName("school_id")
    public int school_id;
    //七牛图片id
    @SerializedName("image_id")
    public  String image_id;

    public String getImageUrl() {
        return UploadImageUtils.contactFullImageUrl(HttpCache.getInstance().getImageHost(),image_id);
    }

    @Override
    String getFullImageUrl() {
        return getImageUrl();
    }
}
