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
import com.jyq.android.net.modal.CommonContent;
import com.jyq.android.net.modal.CommonId;
import com.jyq.android.net.modal.School;
import com.jyq.android.net.modal.Share;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2016/9/2.
 */
public class SchoolService extends BaseService{
    private interface Api{
        @POST("/api/school/create")
        Observable<BaseResponse<CommonId>> createSchool(@Body School school);
        @POST("/api/school/update")
        Observable<BaseResponse<Void>> updateSchoolInfo(@Body School school);
        @GET("/api/school/info")
        Observable<BaseResponse<School>> getSchoolInfo();
        @POST("/api/school/add-image")
        Observable<BaseResponse<Void>> uploadSchoolImage(@Body Map map);
        @GET("/api/enrol/get-url")
        Observable<BaseResponse<CommonContent>> getRecruitStudentUrl();
        @GET("/api/recruit/get-url")
        Observable<BaseResponse<CommonContent>> getRecruitTeacherUrl();

        @POST("/api/recruit/create-share")
        Observable<BaseResponse<Share>> getShareTeacherUrl();

        @POST("/api/recruit/share-success")
        Observable<BaseResponse<Void>> postShareTeacher(@Body Map map);

        @POST("/api/enrol/create-share")
        Observable<BaseResponse<Share>> getShareStudentUrl();

        @POST("/api/enrol/share-success")
        Observable<BaseResponse<Void>> postShareStudent(@Body Map map);

    }
    public static Observable<Void> postShareStudent(int shareId){
        Map map=new HashMap();
        map.put("share_id",shareId);
        return toSubscribe(HttpKit.getInstance().getService(Api.class).postShareStudent(map));
    }
    public static Observable<Share> getShareStudentUrl(){
        return toSubscribe(HttpKit.getInstance().getService(Api.class).getShareStudentUrl());
    }
    public static Observable<Void> postShareTeacher(int shareId){
        Map map=new HashMap();
        map.put("share_id",shareId);
        return toSubscribe(HttpKit.getInstance().getService(Api.class).postShareTeacher(map));
    }

    public static Observable<Share> getShareTeacherUrl(){
        return toSubscribe(HttpKit.getInstance().getService(Api.class).getShareTeacherUrl());
    }

    public static Observable<Void> uploadSchoolImage(List<String> imageKeys){
        Map map=new HashMap();
        map.put("image_id",imageKeys.toArray(new String[]{}));
      return toSubscribe( HttpKit.getInstance().getService(Api.class).uploadSchoolImage(map));
    }
    public static Observable<School> getSchoolInfo(){
       return toSubscribe( HttpKit.getInstance().getService(Api.class).getSchoolInfo());
    }
    public static Observable<Void> updateSchoolInfo(School school){
        return toSubscribe(HttpKit.getInstance().getService(Api.class).updateSchoolInfo(school));
    }
    public static Observable<CommonId> crateSchool(School school){
       return toSubscribe( HttpKit.getInstance().getService(Api.class).createSchool(school));
    }
    public static Observable<CommonContent> getRecruitSUrl(){
        return toSubscribe(HttpKit.getInstance().getService(Api.class).getRecruitStudentUrl());
    }
    public static Observable<CommonContent> getRecruitTUrl(){
        return toSubscribe( HttpKit.getInstance().getService(Api.class).getRecruitTeacherUrl());
    }

}
