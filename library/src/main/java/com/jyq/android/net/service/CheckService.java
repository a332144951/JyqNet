package com.jyq.android.net.service;



import com.jyq.android.net.HttpKit;
import com.jyq.android.net.modal.Assess;
import com.jyq.android.net.modal.BaseResponse;
import com.jyq.android.net.modal.Dynamic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public class CheckService extends BaseService {
    private interface Api {
        @POST("/api/baby-rate/audit-list")
        Observable<BaseResponse<List<Assess>>> getCheckAssess(@Body Map map);
        @POST("/api/baby-rate/delete-baby-rate")
        Observable<BaseResponse<Void>> deleteAssess(@Body Map map);
        @POST("/api/baby-rate/audit-pass")
        Observable<BaseResponse<Void>> passAssess(@Body Map map);
        @POST("/api/moment/audit-list")
        Observable<BaseResponse<List<Dynamic>>> getCheckDynamic(@Body Map map);
        @POST("/api/moment/audit-pass")
        Observable<BaseResponse<Void>> passCheckDynamic(@Body Map map);
        @POST("/api/moment/delete-moment")
        Observable<BaseResponse<Void>> deleteCheckDynamic(@Body Map map);
    }

    public static Observable<List<Assess>> getCheckAssess(int page,int type) {
        Map map = new HashMap();
        map.put("page",page);
        map.put("type",type);
        return   toSubscribe(HttpKit.getInstance().getService(Api.class).getCheckAssess(map));
    }
    public static Observable<Void> deleteAssess(int id) {
        Map map = new HashMap();
        map.put("rate_id",id);
        return   toSubscribe(HttpKit.getInstance().getService(Api.class).deleteAssess(map));
    }
    public static Observable<Void> passAssess(int id) {
        Map map = new HashMap();
        map.put("rate_id",id);
        return   toSubscribe(HttpKit.getInstance().getService(Api.class).passAssess(map));
    }
    public static Observable<List<Dynamic>> getCheckDynamic(int page) {
        Map map = new HashMap();
        map.put("page",page);
        return   toSubscribe(HttpKit.getInstance().getService(Api.class).getCheckDynamic(map));
    }
    public static Observable<Void> passCheckDynamic(int id) {
        Map map = new HashMap();
        map.put("moment_id",id);
        return   toSubscribe(HttpKit.getInstance().getService(Api.class).passCheckDynamic(map));
    }
    public static Observable<Void> deleteCheckDynamic(int id) {
        Map map = new HashMap();
        map.put("moment_id",id);
        return   toSubscribe(HttpKit.getInstance().getService(Api.class).deleteCheckDynamic(map));
    }
}
