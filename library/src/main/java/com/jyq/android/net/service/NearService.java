package com.jyq.android.net.service;



import com.jyq.android.net.HttpKit;
import com.jyq.android.net.modal.BaseResponse;
import com.jyq.android.net.modal.Near;
import com.jyq.android.net.modal.School;
import com.jyq.android.net.modal.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public class NearService extends BaseService {
    private interface Api {
        @POST("/api/geo/radius-user")
        Observable<BaseResponse<List<User>>> getNearPerson(@Body Map map);
        @POST("/api/geo/radius-school")
        Observable<BaseResponse<List<School>>> getNearSchool(@Body Map map);
        @POST("/api/user-role/local-user-list")
        Observable<BaseResponse<List<User>>> getCityPerson(@Body Map map);
        @POST("/api/school/local-school-list")
        Observable<BaseResponse<List<School>>> getCitySchool(@Body Map map);
    }

    public static Observable<List<User>> getNearPerson(Near near) {
        Map map = new HashMap();
        map.put("lng", near.lng);
        map.put("lat", near.lat);
        map.put("radius", 3);
        map.put("unit", "km");
        map.put("withcoord", 1);
        map.put("withdist", 1);
        map.put("sort", "ASC");
        map.put("role",near.type);
        return   toSubscribe(HttpKit.getInstance().getService(Api.class).getNearPerson(map));
    }
    public static Observable<List<User>> getCityPerson(String type) {
        Map map = new HashMap();
        map.put("role",type);
        return   toSubscribe(HttpKit.getInstance().getService(Api.class).getCityPerson(map));
    }
    public static Observable<List<School>> getNearSchool(Near near) {
        Map map = new HashMap();
        map.put("lng", near.lng);
        map.put("lat", near.lat);
        map.put("radius", 3);
        map.put("unit", "km");
        map.put("withcoord", 1);
        map.put("withdist", 1);
        map.put("sort", "ASC");
        map.put("type",near.type);
        return   toSubscribe(HttpKit.getInstance().getService(Api.class).getNearSchool(map));
    }
    public static Observable<List<School>> getCitySchool(String type) {
        Map map = new HashMap();
        map.put("type",type);
        return   toSubscribe(HttpKit.getInstance().getService(Api.class).getCitySchool(map));
    }



}
