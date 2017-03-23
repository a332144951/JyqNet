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

import java.util.HashMap;
import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/3/21.
 */

public class BabyService extends BaseService {
    private interface Api{
        @GET("/api/user-role/baby-info")
        Observable<BaseResponse<Baby>> getBabyInfo(@Query("user_role_id") int id);

        @POST("/api/baby/get-baby-info")
        Observable<BaseResponse<Baby>> getBabyInfo(@Body Map params);
    }

    public static Observable<Baby> getBabyInfoById(int babyId){
        Map params=new HashMap();
        params.put("id",babyId);
        return toSubscribe(HttpKit.getInstance().getService(Api.class).getBabyInfo(params));
    }
    public static Observable<Baby> getBabyInfo(int userLogicId){
        return toSubscribe(HttpKit.getInstance().getService(Api.class).getBabyInfo(userLogicId));
    }
}
