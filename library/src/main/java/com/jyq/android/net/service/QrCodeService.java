package com.jyq.android.net.service;


import com.jyq.android.net.HttpKit;
import com.jyq.android.net.modal.BaseResponse;
import com.jyq.android.net.modal.Medicine;
import com.jyq.android.net.modal.QrCode;
import com.jyq.android.net.modal.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

public class QrCodeService extends BaseService {
    private interface Api {
        @GET("/api/user-role/usercard")
        Observable<BaseResponse<QrCode>> getQrCode();
        @POST("/api/invite/qr-invite-preview")
        Observable<BaseResponse<User>> getUserInfoById(@Body Map map);
    }
    public static Observable<QrCode> getQrCode() {
        return   toSubscribe(HttpKit.getInstance().getService(Api.class).getQrCode());
    }
    public static Observable<User> getUserInfoById(int userId) {
        Map map = new HashMap();
        map.put("user_role_id", userId);
        return toSubscribe(HttpKit.getInstance().getService(Api.class).getUserInfoById(map));
    }
}
