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
import com.jyq.android.net.modal.Invite;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2016/11/30.
 */

public class InviteService extends BaseService {
    private interface Api{

        @GET("/api/invite/list")
        Observable<BaseResponse<List<Invite>>> getInviteList();
        @POST("/api/invite/accept")
        Observable<BaseResponse<Void>> passInvite(@Body Map map);
        @POST("/api/invite/delete")
        Observable<BaseResponse<Void>> deleteInvite(@Body Map map);
    }
    public static Observable<List<Invite>> getInviteList(){
        return toSubscribe(HttpKit.getInstance().getService(Api.class).getInviteList());
    }
    public static Observable<Void> passInvite(int id){
        Map map=new HashMap();
        map.put("invite_id",id);
        return toSubscribe(HttpKit.getInstance().getService(Api.class).passInvite(map));
    }
    public static Observable<Void> deleteInvite(int id){
        Map map=new HashMap();
        map.put("invite_id",id);
        return toSubscribe(HttpKit.getInstance().getService(Api.class).deleteInvite(map));
    }
}
