package com.jyq.android.net.service;

import android.text.format.DateFormat;


import com.jyq.android.net.HttpKit;
import com.jyq.android.net.modal.BaseResponse;
import com.jyq.android.net.modal.SignGrade;
import com.jyq.android.net.modal.SignDetail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Author : GuoL
 * Create at 2016-10-26 9:20
 */

public class SignInService extends BaseService{
    private interface Api{
        @POST("/api/checkin/school-status")
        Observable<BaseResponse<SignGrade>> getSiginClassList();
        @POST("/api/checkin/class-status")
        Observable<BaseResponse<SignGrade>> getSiginPersonList(@Body Map classId);
        @POST("/api/checkin/month")
        Observable<BaseResponse<List<SignDetail>>> getSiginPersonDeatil(@Body Map map);
        @POST("/api/checkin/checkin")
        Observable<BaseResponse<Void>> getUpdateSignIn(@Body Map map);
        @POST("/api/checkin/checkin")
        Observable<BaseResponse<Void>> addSignIn(@Body Map map);
    }
    public static Observable<Void> addSignIn(int baybyId,long time){
        Map map=new HashMap();
        map.put("baby_id",baybyId);
        map.put("checkin_time", DateFormat.format("yyyy-MM-dd HH:mm:ss",time));
        return toSubscribe(HttpKit.getInstance().getService(Api.class).addSignIn(map));
    }
    /*
    获取考勤班级列表
     */
    public static Observable<SignGrade> getSiginClassList() {
        return   toSubscribe(HttpKit.getInstance().getService(SignInService.Api.class).getSiginClassList());
    }
    /*
    获取考勤学生列表
    */
    public static Observable<SignGrade> getSiginPersonList(int classId) {
        Map map = new HashMap();
        map.put("class_id", classId);
        return   toSubscribe(HttpKit.getInstance().getService(SignInService.Api.class).getSiginPersonList(map));

    }
    /*
    获取人员签到详情
     */
    public static Observable<List<SignDetail>> getSiginPersonDeatil(int baby_id, String month) {
        Map map = new HashMap();
        map.put("baby_id", baby_id);
        map.put("month", month);
        return   toSubscribe(HttpKit.getInstance().getService(SignInService.Api.class).getSiginPersonDeatil(map));
    }
    /*
    更改人员签到
     */
    public static Observable<Void> UpdateSignIn(int baby_id,String checkin_time,int edit_status) {
        Map map = new HashMap();
        map.put("checkin_time", checkin_time);
        map.put("baby_id", baby_id);
        map.put("edit_status", edit_status);
        return   toSubscribe(HttpKit.getInstance().getService(SignInService.Api.class).getUpdateSignIn(map));
    }
}
