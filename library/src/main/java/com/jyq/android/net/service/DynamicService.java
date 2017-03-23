package com.jyq.android.net.service;

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


import com.jyq.android.net.HttpKit;
import com.jyq.android.net.modal.BaseResponse;
import com.jyq.android.net.modal.Dynamic;
import com.jyq.android.net.modal.Reply;
import com.jyq.android.net.modal.ReturnObject;
import com.jyq.android.net.modal.Share;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2016/10/15.
 */

public class DynamicService extends BaseService{
    private interface Api{
        @POST("/api/moment/create")
        Observable<BaseResponse<ReturnObject>> create(@Body Map map);

        @POST("/api/moment/reply")
        Observable<BaseResponse<ReturnObject>> reply(@Body Map map);

        @POST("/api/moment/list")
        Observable<BaseResponse<List<Dynamic>>> dynamicList(@Body Map map);

        @POST("/api/moment/delete-reply")
        Observable<BaseResponse<Void>> delteReply(@Body Map map);

        @POST("/api/moment/delete-moment")
        Observable<BaseResponse<Void>> deleteDyanmic(@Body Map map);

        @POST("/api/moment/more-reply")
        Observable<BaseResponse<List<Reply>>> replyList(@Body Map map);

        @POST("/api/moment/moment-add-score")
        Observable<BaseResponse<Void>> addDynamicScore(@Body Map map);

        @POST("/api/moment/reply-add-score")
        Observable<BaseResponse<Void>> addReplyScore(@Body Map map);


        @POST("/api/moment/create-share")
        Observable<BaseResponse<Share>> getShareUrl(@Body Map map);

        @POST("/api/moment/share-success")
        Observable<BaseResponse<Void>> postShareSuccess(@Body Map map);
    }
    public static Observable<Void> postShareSuccess(int shareId){
        HashMap map=new HashMap();
        map.put("share_id",shareId);
        return toSubscribe(HttpKit.getInstance().getService(Api.class).postShareSuccess(map));
    }
    public static Observable<Share> getShareUrl(int commentId, String imgKey){
        HashMap map=new HashMap();
        map.put("moment_id",commentId);
        map.put("image_id",imgKey);
        return toSubscribe(HttpKit.getInstance().getService(Api.class).getShareUrl(map));
    }

    public static Observable<ReturnObject> replyDynamic(int dynamicId,int referId,String[] images,String comment){
        HashMap map=new HashMap();
        map.put("comments",comment);
        map.put("moment_id",dynamicId);
        map.put("refer_id",referId);
        map.put("images",images);
        return toSubscribe(HttpKit.getInstance().getService(Api.class).reply(map));
    }
    public static Observable<Void> deleteReply(int replyId){
        HashMap map=new HashMap();
        map.put("reply_id",replyId);
        return  toSubscribe(HttpKit.getInstance().getService(Api.class).delteReply(map));
    }
    public static Observable<Void> addReplyScore(int replyId,int point){
        HashMap map=new HashMap();
        map.put("reply_id",replyId);
        map.put("score",point);
        return toSubscribe(HttpKit.getInstance().getService(Api.class).addReplyScore(map));
    }
    public  static Observable<Void> deleteDynamic(int dynamicId){
        HashMap map=new HashMap();
        map.put("moment_id",dynamicId);
        return toSubscribe(HttpKit.getInstance().getService(Api.class).deleteDyanmic(map));
    }

    public static Observable<Void> addDynamicScore(int dynamicId, int score){
        HashMap map=new HashMap();
        map.put("moment_id",dynamicId);
        map.put("score",score);
        return toSubscribe(HttpKit.getInstance().getService(Api.class).addDynamicScore(map));
    }
    public static Observable<List<Dynamic>> getDynamicList(int page,int classId,int type){
        HashMap map=new HashMap();
        map.put("page",page);
        map.put("class_id",classId);
        map.put("type",type);
        return toSubscribe(HttpKit.getInstance().getService(Api.class).dynamicList(map));
    }

    public static Observable<ReturnObject> createDynamic(String comment, String[] images, int type, int display, int[] classId){
        HashMap map=new HashMap();
        map.put("content",comment);
        map.put("type",type);
        map.put("display",display);
        map.put("class_id",classId);
        map.put("images",images);
        return toSubscribe(HttpKit.getInstance().getService(Api.class).create(map));
    }

    public static Observable<List<Reply>> getReplyList(int dynamicId){
        HashMap map=new HashMap();
        map.put("moment_id",dynamicId);
        return toSubscribe(HttpKit.getInstance().getService(Api.class).replyList(map));
    }
}
