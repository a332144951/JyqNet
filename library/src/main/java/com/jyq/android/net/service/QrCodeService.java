package com.jyq.android.net.service;


import com.jyq.android.net.HttpKit;
import com.jyq.android.net.modal.BaseResponse;
import com.jyq.android.net.modal.QrCode;

import retrofit2.http.GET;
import rx.Observable;

public class QrCodeService extends BaseService {
    private interface Api {
        @GET("/api/user-role/usercard")
        Observable<BaseResponse<QrCode>> getQrCode();
    }
    public static Observable<QrCode> getQrCode() {
        return   toSubscribe(HttpKit.getInstance().getService(Api.class).getQrCode());
    }
}
