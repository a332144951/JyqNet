package com.jyq.android.net.service;



import com.jyq.android.net.HttpKit;
import com.jyq.android.net.modal.BaseResponse;
import com.jyq.android.net.modal.Grade;
import com.jyq.android.net.modal.Medicine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Author : GuoL
 * Create at 2016-10-26 9:24
 */

public class MedicineService extends BaseService{
    private interface Api {
        @GET("/api/class/list")
        Observable<BaseResponse<List<Grade>>> getClassList();
        @POST("/api/medicine/list")
        Observable<BaseResponse<List<Medicine>>> getMedicineListByClass(@Body Map map);
        @POST("/api/medicine/create")
        Observable<BaseResponse<Void>> createMedicine(@Body Map map);
        @POST("/api/medicine/complete")
        Observable<BaseResponse<Void>> passMedicine(@Body Map map);
    }
    //获取班级列表
    public static Observable<List<Grade>> getClassList() {
        return toSubscribe(HttpKit.getInstance().getService(MedicineService.Api.class).getClassList());
    }
    //获取喂药列表
    public static Observable<List<Medicine>> getMedicineListByClass(int classId,int stat) {
        Map map = new HashMap();
        map.put("stat", stat);
        map.put("class_id", classId);
        return toSubscribe(HttpKit.getInstance().getService(MedicineService.Api.class).getMedicineListByClass(map));
    }
    //创建喂药
    public static Observable<Void> createMedicine(String content,String medicine_time) {
        Map map = new HashMap();
        map.put("content", content);
        map.put("medicine_time", medicine_time);
        return toSubscribe(HttpKit.getInstance().getService(MedicineService.Api.class).createMedicine(map));
    }
    //完成喂药
    public static Observable<Void> passMedicine(int medicine_id) {
        Map map = new HashMap();
        map.put("medicine_id", medicine_id);
        return toSubscribe(HttpKit.getInstance().getService(MedicineService.Api.class).passMedicine(map));
    }
}
