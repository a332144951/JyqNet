package com.jyq.android.net.service;


import com.jyq.android.net.HttpKit;
import com.jyq.android.net.modal.Assess;
import com.jyq.android.net.modal.BaseResponse;
import com.jyq.android.net.modal.RedFlower;
import com.jyq.android.net.modal.Reply;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public class flowerService extends BaseService {
    private interface Api {
        @POST("/api/flower/sum")
        Observable<BaseResponse<RedFlower>> getFlowerNum(@Body Map map);
        @POST("/api/flower/today")
        Observable<BaseResponse<List<RedFlower>>> getTodayFlower(@Body Map map);
        @POST("/api/flower/before")
        Observable<BaseResponse<List<RedFlower>>> getHistoryFlower(@Body Map map);
        @POST("/api/baby-rate/rate-list")
        Observable<BaseResponse<List<Assess>>> getassessList(@Body Map map);
        @POST("/api/baby-rate/create")
        Observable<BaseResponse<Assess>> createAssess(@Body Map map);
        @POST("/api/baby-rate/reply-list")
        Observable<BaseResponse<List<Reply>>> getAssessReply(@Body Map map);
        @POST("/api/baby-rate/reply-add-score")
        Observable<BaseResponse<Void>> addReplyScore(@Body Map map);
        @POST("/api/baby-rate/reply")
        Observable<BaseResponse<Void>> addReply(@Body Map map);
        @POST("/api/baby-rate/delete-baby-rate")
        Observable<BaseResponse<Void>> deleteReply(@Body Map map);


        @POST("/api/flower/inc")
        Observable<BaseResponse<Void>> uploadFlower(@Body Map map);
    }
    public static Observable<Void> uploadFlower(int teacherId,int babyId,long time,int type){
        Map map=new HashMap();
        map.put("sender_user_role_id",teacherId);
        map.put("baby_id",babyId);
        map.put("flower_time", android.text.format.DateFormat.format("yyyy-MM-dd HH:mm:ss",time));
        map.put("type",type);
       return toSubscribe(HttpKit.getInstance().getService(Api.class).uploadFlower(map));
    }

    public static Observable<RedFlower> getFlowerNum(int baby_id) {
        Map map = new HashMap();
        map.put("baby_id",baby_id);
        return   toSubscribe(HttpKit.getInstance().getService(Api.class).getFlowerNum(map));
    }
    public static Observable<List<RedFlower>> getTodayFlower(int baby_id) {
        Map map = new HashMap();
        map.put("baby_id",baby_id);
        return   toSubscribe(HttpKit.getInstance().getService(Api.class).getTodayFlower(map));
    }
    public static Observable<List<RedFlower>> getHistoryFlower(int baby_id) {
        Map map = new HashMap();
        map.put("baby_id",baby_id);
        return   toSubscribe(HttpKit.getInstance().getService(Api.class).getHistoryFlower(map));
    }
    public static Observable<List<Assess>> getassessList(int page,int baby_id,int type) {
        Map map = new HashMap();
        map.put("page",page);
        map.put("type",type);
        map.put("baby_id",baby_id);
        return   toSubscribe(HttpKit.getInstance().getService(Api.class).getassessList(map));
    }
    public static Observable<Assess> createAssess(int baby_id,int type,String contents) {
        Map map = new HashMap();
        map.put("baby_id",baby_id);
        map.put("type",type);
        map.put("contents",contents);
        return   toSubscribe(HttpKit.getInstance().getService(Api.class).createAssess(map));
    }
    public static Observable<List<Reply>> getAssessReply(int page, int rate_id) {
        Map map = new HashMap();
        map.put("page",page);
        map.put("rate_id",rate_id);
        return   toSubscribe(HttpKit.getInstance().getService(Api.class).getAssessReply(map));
    }
    public static Observable<Void> addReplyScore(int reply,int score) {
        Map map = new HashMap();
        map.put("reply_id",reply);
        map.put("score",score);
        return   toSubscribe(HttpKit.getInstance().getService(Api.class).addReplyScore(map));
    }
    public static Observable<Void> addReply(int rate_id,int refer_id,String comments) {
        Map map = new HashMap();
        map.put("rate_id",rate_id);
        if(refer_id!=0){
            map.put("refer_id",refer_id);
        }
        map.put("comments",comments);
        return   toSubscribe(HttpKit.getInstance().getService(Api.class).addReply(map));
    }
    public static Observable<Void> deleteReply(int rate_id) {
        Map map = new HashMap();
        map.put("rate_id",rate_id);
        return   toSubscribe(HttpKit.getInstance().getService(Api.class).deleteReply(map));
    }

}
