package com.jyq.android.net.service;

import com.jyq.android.net.HttpKit;
import com.jyq.android.net.modal.BaseResponse;
import com.jyq.android.net.modal.DateTime;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Author : GuoL
 * Create at 2016-10-26 9:24
 */

public class TodayStarService extends BaseService{
    private interface Api {
        @GET("/api/datetime/get")
        Observable<BaseResponse<DateTime>> getDataTime();
    }
    public static Observable<DateTime> getDataTime() {
        return toSubscribe(HttpKit.getInstance().getService(TodayStarService.Api.class).getDataTime());
    }
}
