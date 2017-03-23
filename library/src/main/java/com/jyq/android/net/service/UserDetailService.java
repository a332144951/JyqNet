package com.jyq.android.net.service;



import com.jyq.android.net.HttpKit;
import com.jyq.android.net.modal.Area;
import com.jyq.android.net.modal.BaseResponse;
import com.jyq.android.net.modal.TeacherAssess;
import com.jyq.android.net.modal.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Author : GuoL
 * Create at 2016-10-26 14:58
 */

public class UserDetailService extends  BaseService{
    private interface Api{
        @POST("/api/district/get")
        Observable<BaseResponse<List <Area>>> getAreaList(@Body Map map);
        @POST("/api/user-role/profile/update")
        Observable<BaseResponse<Void>> updateUserInfo(@Body Map map);
        @GET("/api/user-role/profile")
        Observable<BaseResponse<User>> getUserInfo(@Query("user_role_id") int id);
        @POST("/api/teacher-rate/summary")
        Observable<BaseResponse<TeacherAssess>> getTeacherAssess(@Body Map map);
        @POST("/api/teacher-rate/summary-parent")
        Observable<BaseResponse<TeacherAssess>> getPatentAssess(@Body Map map);
        @POST("/api/teacher-rate/add")
        Observable<BaseResponse<Void>> addTeacherAssess(@Body Map map);


    }
    public static Observable<List <Area>> getAreaList(int id) {
        Map map = new HashMap();
        map.put("parent_id", id);
        return   toSubscribe(HttpKit.getInstance().getService(UserDetailService.Api.class).getAreaList(map));
    }
    public static Observable<Void> updateUserInfo(User userInfo) {
        Map map = new HashMap();
        map.put("name",userInfo.name);
        map.put("avatar",userInfo.getAvatarKey());
        map.put("description",userInfo.description);
        map.put("province",userInfo.getAddress().provinceId);
        map.put("city",userInfo.getAddress().cityId);
        map.put("area",userInfo.getAddress().areaId);
        map.put("street",userInfo.getAddress().street);
        map.put("addr_text",userInfo.getAddress().fullAddress);
        map.put("biye_school",userInfo.biye_school);
        return   toSubscribe(HttpKit.getInstance().getService(UserDetailService.Api.class).updateUserInfo(map));
    }
    public static Observable<User> getUserInfo(int  user_role_id) {
        return   toSubscribe(HttpKit.getInstance().getService(UserDetailService.Api.class).getUserInfo(user_role_id));
    }
    public static Observable<TeacherAssess> getTeacherAssess(String  month) {
        Map map = new HashMap();
        map.put("month",month);
        return   toSubscribe(HttpKit.getInstance().getService(UserDetailService.Api.class).getTeacherAssess(map));
    }
    public static Observable<TeacherAssess> getPatentAssess(String  month) {
        Map map = new HashMap();
        map.put("month",month);
        return   toSubscribe(HttpKit.getInstance().getService(UserDetailService.Api.class).getPatentAssess(map));
    }
    public static Observable<Void> masterAddAssess(int user_role_id,int rate4) {
        Map map = new HashMap();
        map.put("user_role_id",user_role_id);
        map.put("rate4",rate4);
        return   toSubscribe(HttpKit.getInstance().getService(UserDetailService.Api.class).addTeacherAssess(map));
    }
    public static Observable<Void> parentAddAssess(int user_role_id,int rate1,int rate2,int rate3) {
        Map map = new HashMap();
        map.put("user_role_id",user_role_id);
        map.put("rate1",rate1);
        map.put("rate2",rate2);
        map.put("rate3",rate3);
        return   toSubscribe(HttpKit.getInstance().getService(UserDetailService.Api.class).addTeacherAssess(map));
    }

}
