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

import android.text.TextUtils;


import com.jyq.android.net.HttpKit;
import com.jyq.android.net.cache.HttpCache;
import com.jyq.android.net.modal.Address;
import com.jyq.android.net.modal.BaseResponse;
import com.jyq.android.net.modal.Contacts;
import com.jyq.android.net.modal.User;

import java.util.HashMap;
import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Administrator on 2016/8/31.
 */
public class UserService extends BaseService{

    private interface Api{
        @POST("/api/user-role/address/update")
        Observable<BaseResponse<Void>> updateAddress(@Body Address address);

        @GET("/api/user-role/address")
        Observable<BaseResponse<Address>> getAddress(@Query("user_role_id") int id);

        @POST("/api/user-role/profile/update")
        Observable<BaseResponse<Void>> updateProfile(@Body Map map);

        @GET("/api/user-role/profile")
        Observable<BaseResponse<User>> getUserProfile(@Query("user_role_id") int id);

        @GET("/api/contact/list")
        Observable<BaseResponse<Contacts>> getContacts();
        @POST("/api/user-role/position/upload")
        Observable<BaseResponse<Void>> uploadLocation(@Body Map map);
    }

    public static Observable<Void> updateAddress(Address address){
      return toSubscribe(  HttpKit.getInstance().getService(Api.class).updateAddress(address));
    }
    public static Observable<Address> getAddress(int userLogicId){
      return toSubscribe(  HttpKit.getInstance().getService(Api.class).getAddress(userLogicId));
    }

    public static Observable<Void> updateProfile(String name, String avatar, String description){
        Map<String,String> param=new HashMap();
        if(!TextUtils.isEmpty(name)){
            param.put("name",name);
        }
        if(!TextUtils.isEmpty(avatar)){
            param.put("avatar",avatar);
        }
        if(!TextUtils.isEmpty(description)){
            param.put("description",description);
        }
     return toSubscribe(   HttpKit.getInstance().getService(Api.class).updateProfile(param));
    }
    public static Observable<User> getUserProfile(final User tempuser){
       return toSubscribe( HttpKit.getInstance().getService(Api.class).getUserProfile(tempuser.logicId))
               .map(new Func1<User, User>() {
                   @Override
                   public User call(User user) {
//                       tempuser.setAvatar(user.getAvatar());
//                       tempuser.name=user.name;
//                       tempuser.description=user.description;
//                       tempuser.py=user.py;
                       return new User.Builder(tempuser)
                               .name(user.name)
                               .py(user.py)
                               .description(user.description)
                               .avatar(user.getAvatarKey())
                               .score(user.score)
                               .province(user.getAddress().provinceId)
                               .city(user.getAddress().cityId)
                               .area(user.getAddress().areaId)
                               .street(user.getAddress().street)
                               .addr_text(user.getAddress().fullAddress)
                               .biye_school(user.biye_school)
                               .build();
                   }
               }).doOnNext(new Action1<User>() {
                   @Override
                   public void call(User user) {
                       HttpCache.getInstance().updateLoginUser(user);
                   }
               });

    }

    public static Observable<Contacts> getUserContacts(){
      return toSubscribe(  HttpKit.getInstance().getService(Api.class).getContacts());
    }
    public static Observable<Void> uploadLocation(double x,double y) {
        Map map = new HashMap();
        map.put("lng",x);
        map.put("lat",y);
        return   toSubscribe(HttpKit.getInstance().getService(Api.class).uploadLocation(map));
    }

}
