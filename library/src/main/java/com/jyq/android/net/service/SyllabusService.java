package com.jyq.android.net.service;

import android.util.SparseArray;


import com.jyq.android.net.HttpKit;
import com.jyq.android.net.modal.BaseResponse;
import com.jyq.android.net.modal.Grade;
import com.jyq.android.net.modal.Syllabus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;
import rx.functions.Func1;

/**
 * Author : GuoL
 * Create at 2016-10-26 9:24
 */

public class SyllabusService extends BaseService{
    private interface Api {
        @GET("/api/class/list")
        Observable<BaseResponse<List<Grade>>> getClassList();
        @POST("/api/course/week-all")
        Observable<BaseResponse<List<Syllabus>>> getClassSyllabus(@Body Map map);
        @POST("/api/course/save")
        Observable<BaseResponse<Syllabus>> editSyllabus(@Body Map map);
    }
    //获取班级列表
    public static Observable<List<Grade>> getClassList() {
        return toSubscribe(HttpKit.getInstance().getService(SyllabusService.Api.class).getClassList());
    }
    //更改课程
    public static Observable<Syllabus> editSyllabus(Syllabus syllabus) {
        Map map = new HashMap();
        map.put("week",syllabus.week);
        map.put("type",syllabus.type);
        map.put("section",syllabus.section);
        map.put("text",syllabus.text);
        return toSubscribe(HttpKit.getInstance().getService(SyllabusService.Api.class).editSyllabus(map));
    }
    //获取班级课程
    public static Observable<SparseArray<SparseArray<SparseArray<Syllabus>>>> getClassSyllabus(int class_id) {
        Map map = new HashMap();
        map.put("class_id", class_id);
        return toSubscribe(HttpKit.getInstance().getService(SyllabusService.Api.class).getClassSyllabus(map))
                .flatMap(new Func1<List<Syllabus>, Observable<SparseArray<SparseArray<SparseArray<Syllabus>>>>>() {
                    @Override
                    public Observable<SparseArray<SparseArray<SparseArray<Syllabus>>>> call(List<Syllabus> syllabuses) {
                        SparseArray<SparseArray<SparseArray<Syllabus>>> week=new SparseArray<SparseArray<SparseArray<Syllabus>>>();
                        for (int i=1;i<8;i++) {
                            SparseArray<SparseArray<Syllabus>> item=new SparseArray<>();
                            for (int j=0;j<3;j++){
                                SparseArray<Syllabus> syllabuss=new SparseArray<Syllabus>();
                                int count =5;
                                if (j==2){
                                    count=3;
                                }
                                for (int k=1;k<count;k++){
                                    Syllabus syllabus=new Syllabus();
                                    syllabus.week=i;
                                    syllabus.type=j;
                                    syllabus.section=k;
                                    syllabuss.put(k,syllabus);
                                }
                                item.put(j,syllabuss);
                            }
                            week.put(i,item);
                        }
                        for (Syllabus s: syllabuses){
                            SparseArray<SparseArray<Syllabus>> day=week.get(s.week,new SparseArray<SparseArray<Syllabus>>());
                            SparseArray<Syllabus> type=week.get(s.week,new SparseArray<SparseArray<Syllabus>>()).get(s.type,new SparseArray<Syllabus>());
                            type.put(s.section,s);
                            day.put(s.type,type);
                            week.put(s.week,day);
                        }
                        return Observable.just(week);
                    }
                });

    }
}
