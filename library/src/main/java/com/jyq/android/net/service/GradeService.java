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
import com.jyq.android.net.modal.Baby;
import com.jyq.android.net.modal.BaseResponse;
import com.jyq.android.net.modal.CommonId;
import com.jyq.android.net.modal.Grade;
import com.jyq.android.net.modal.User;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;
import rx.functions.Action1;

/**
 * Created by Administrator on 2016/9/2.
 */
public class GradeService extends BaseService{
    private interface Api{
        @POST("/api/class/create")
        Observable<BaseResponse<CommonId>> createGrade(@Body Map map);

        @POST("/api/class/update")
        Observable<BaseResponse<Void>> updateGradeInfo(@Body Grade grade);

        @GET("/api/class/info")
        Observable<BaseResponse<Grade>> getGradeInfo(@Query("id") int id);

        @GET("/api/class/list")
        Observable<BaseResponse<List<Grade>>> getGradeList();

        @POST("/api/chat/mute-user")
        Observable<BaseResponse<Void>> muteGroupUser(@Body Map map);
        @POST("/api/baby/list")
        Observable<BaseResponse<List<Baby>>> getBabyList(@Body Map map);

        @POST("/api/chat/validata-accid")
        Observable<BaseResponse<Void>> validateSession(@Body Map map);

        @POST("/api/chat/validata-groupid")
        Observable<BaseResponse<Void>> validateTeam(@Body Map map);

        @POST("/api/class/user-list")
        Observable<BaseResponse<List<User>>> fetchGradeUserList(@Body Map map);
    }
    public final class RoleRange{
        public static final int ROLE_PARENT=1;
        public static final int ROLE_TEACHER=2;
        public static final int ROLE_BABY=4;
    }
    public static Observable<List<User>> fetchGradeUsers(int gradeId,int roleRange){
        HashMap map=new HashMap();
        map.put("class_id",gradeId);
        List<String> role=new ArrayList<>();
        if ((roleRange&RoleRange.ROLE_BABY)!=0){
            role.add("baby");
        }
        if (((roleRange&RoleRange.ROLE_PARENT))!=0){
            role.add("parent");
        }
        if (((roleRange&RoleRange.ROLE_TEACHER))!=0){
            role.add("teacher");
        }
        map.put("role",role);
        return toSubscribe(HttpKit.getInstance().getService(Api.class).fetchGradeUserList(map));
    }

    public static Observable<Void> validateTeam(String teamId){
        HashMap map=new HashMap();
        map.put("group_id",teamId);
        return toSubscribe(HttpKit.getInstance().getService(Api.class).validateTeam(map));
    }
    public static Observable<Void> validateSession(String accId){
        HashMap map=new HashMap();
        map.put("accid",accId);
        return toSubscribe(HttpKit.getInstance().getService(Api.class).validateSession(map));
    }

    /***
     *
     * @param teamId
     * @param userRoleId
     * @param mute 1:禁言,0:解除
     * @return
     */
    public  static Observable<Void> muteGroupUser(final String teamId, int userRoleId, int mute){
            HashMap map=new HashMap();
        map.put("yx_group_id",teamId);
        map.put("user_role_id",userRoleId);
        map.put("mute",mute);
        return toSubscribe(HttpKit.getInstance().getService(Api.class).muteGroupUser(map))
                .doOnNext(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
//                        TeamDataCache.getInstance().updateMuteList(teamId);
                    }
                });
    }
    public static Observable<CommonId> createGrade(String name){
        Map map=new HashMap();
        map.put("name",name);
       return toSubscribe(HttpKit.getInstance().getService(Api.class).createGrade(map));
    }
    public static Observable<Void> updateGradeInfo(Grade grade){

        return toSubscribe(HttpKit.getInstance().getService(Api.class).updateGradeInfo(grade));
    }
    public static Observable<Grade> getGradeInfo(int gradeId){
     return toSubscribe(  HttpKit.getInstance().getService(Api.class).getGradeInfo(gradeId));
    }
    public static Observable<List<Grade>> getGradeList(){
      return toSubscribe(  HttpKit.getInstance().getService(Api.class).getGradeList());
    }
    public static Observable<List<Baby>> getBabyList(int class_id){
        Map map=new HashMap();
        map.put("class_id",class_id);
        return toSubscribe(  HttpKit.getInstance().getService(Api.class).getBabyList(map));
    }
}
