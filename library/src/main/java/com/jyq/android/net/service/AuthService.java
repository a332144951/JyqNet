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

import com.google.common.collect.ImmutableMap;
import com.jyq.android.net.HttpKit;
import com.jyq.android.net.cache.HttpCache;
import com.jyq.android.net.modal.AppAuth;
import com.jyq.android.net.modal.BaseResponse;
import com.jyq.android.net.modal.User;

import java.util.HashMap;
import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Administrator on 2017/3/20.
 */

public class AuthService extends BaseService {
    private interface AuthApi {
        @POST("/api/auth/refresh")
        Observable<BaseResponse<AppAuth>> refreshToken();

        @POST("/api/auth/login")
        Observable<BaseResponse<AppAuth>> login(@Body Map login);

        @GET("/api/auth/user")
        Observable<BaseResponse<User>> userInfo();

        @POST("/api/auth/create")
        Observable<BaseResponse<Void>> register(@Body Map register);
    }

    public static Observable<User> userInfo() {
        return toSubscribe(HttpKit.getInstance().getService(AuthApi.class).userInfo()).doOnNext(new Action1<User>() {
            @Override
            public void call(User user) {
                HttpCache.getInstance().updateLoginUser(user);
            }
        });
    }

    public static Observable<Void> register(String phone, String password) {
        Map map = new HashMap();
        map.put("login_id", phone);
        map.put("password", password);
        return toSubscribe(HttpKit.getInstance().getService(AuthApi.class).register(map));
    }

    public static Observable<Boolean> login(String phone, String password, User.UserType type) {
        ImmutableMap map = ImmutableMap.of("login_id", phone, "password", password, "role", type.getType());
        return toSubscribe(HttpKit.getInstance().getService(AuthApi.class).login(map))
                .doOnNext(new Action1<AppAuth>() {
                    @Override
                    public void call(AppAuth appAuth) {
                        HttpKit.getInstance().updateAuthenticator(appAuth.accessToken);
                        HttpCache.getInstance().updateImInfo(appAuth.getImInfo());

                    }
                })
                .map(new Func1<AppAuth, Boolean>() {
                    @Override
                    public Boolean call(AppAuth appAuth) {
                        return appAuth.init;
                    }
                })
                ;
    }

    public static Observable<AppAuth> refreshToken() {
        return toSubscribe(HttpKit.getInstance().getService(AuthApi.class).refreshToken()).doOnNext(new Action1<AppAuth>() {
            @Override
            public void call(AppAuth token) {
                HttpKit.getInstance().updateAuthenticator(token.accessToken);
            }
        });
    }


}
