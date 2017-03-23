package com.jyq.android.net.service;




import com.jyq.android.net.HttpKit;
import com.jyq.android.net.modal.BaseResponse;
import com.jyq.android.net.modal.Rank;
import com.jyq.android.net.modal.RankDetail;
import com.jyq.android.net.modal.RankOfMaster;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public class RankService extends BaseService {
    private interface Api {
        @POST("/api/top/school-parent")
        Observable<BaseResponse<List<Rank>>> getRankOfParent();
        @POST("/api/top/school-teacher")
        Observable<BaseResponse<List<Rank>>> getRankOfTeacher();
        @POST("/api/top/class-parent")
        Observable<BaseResponse<List<Rank>>> getRankOfParentInClass(@Body Map map);
        @POST("/api/score-log/history")
        Observable<BaseResponse<List<RankDetail>>> getScoreDetail(@Body Map map);
        @POST("/api/top/global-master")
        Observable<BaseResponse<RankOfMaster>> getRankOfMasterCountry();
        @POST("/api/top/local-master")
        Observable<BaseResponse<RankOfMaster>> getRankOfMasterCity();
        @POST("/api/top/user-role-rank")
        Observable<BaseResponse<Rank>> getRankUserDeatil(@Body Map map);

    }
    public static Observable<Rank> getRankUserDeatil(int user_role_id) {
        Map map = new HashMap();
        map.put("user_role_id", user_role_id);
        return   toSubscribe(HttpKit.getInstance().getService(Api.class).getRankUserDeatil(map));
    }
    public static Observable<List<Rank>> getRankOfParent() {
        return   toSubscribe(HttpKit.getInstance().getService(Api.class).getRankOfParent());
    }
    public static Observable<List<Rank>> getRankOfTeacher() {
        return   toSubscribe(HttpKit.getInstance().getService(Api.class).getRankOfTeacher());
    }
    public static Observable<List<Rank>> getRankOfParentInClass(int classId) {
        Map map = new HashMap();
        map.put("class_id", classId);
        return   toSubscribe(HttpKit.getInstance().getService(Api.class).getRankOfParentInClass(map));
    }
    public static Observable<RankOfMaster> getRankOfMasterCountry() {
        return   toSubscribe(HttpKit.getInstance().getService(Api.class).getRankOfMasterCountry());
    }
    public static Observable<RankOfMaster> getRankOfMasterCity() {
        return   toSubscribe(HttpKit.getInstance().getService(Api.class).getRankOfMasterCity());
    }
    public static Observable<List<RankDetail>> getScoreDetail(int roleId, int page, String start_date, String end_date) {
        Map map = new HashMap();
        map.put("user_role_id", roleId);
        map.put("page", page);
        map.put("start_date", start_date);
        map.put("end_date", end_date);
        return   toSubscribe(HttpKit.getInstance().getService(Api.class).getScoreDetail(map));
    }



}
