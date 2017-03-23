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


import com.jyq.android.net.HttpKit;
import com.jyq.android.net.modal.Baby;
import com.jyq.android.net.modal.BaseResponse;
import com.jyq.android.net.modal.MagicDevice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Administrator on 2016/9/2.
 */
public class MagicDeviceService extends BaseService{
    private interface Api{
        @POST("/api/user-role/bind-bar")
        Observable<BaseResponse<Void>> bindDevice(@Body Map map);

        @POST("/api/user-role/unbind-bar")
        Observable<BaseResponse<Void>> unbindDevice();

        @GET("/api/user-role/bar-code")
        Observable<BaseResponse<MagicDevice>> getDeviceInfo();

        @POST("/api/baby/bind-card-list")
        Observable<BaseResponse<List<Baby>>> getBabyBoundList();

        @POST("/api/baby/bind-card")
        Observable<BaseResponse<Void>> bindBaby(@Body Map map);
    }
    public static Observable<Void> bindBaby(int babyId){
        Map map=new HashMap();
        map.put("baby_id",babyId);
        return toSubscribe(HttpKit.getInstance().getService(Api.class).bindBaby(map));
    }
    public static Observable<List<Baby>> getBabyBoundList(){
        return toSubscribe(HttpKit.getInstance().getService(Api.class).getBabyBoundList());
    }
    public static Observable<Void> bindDevice(String macAddress){
        Map<String,String> map=new HashMap<>();
        map.put("barcode",macAddress);
      return toSubscribe(  HttpKit.getInstance().getService(Api.class).bindDevice(map));
    }
    public  static Observable<Void> unbindDevice(){
        return toSubscribe(HttpKit.getInstance().getService(Api.class).unbindDevice());
    }
//    public static Observable<BlueToothDevice> getDeviceInfo(){
//       return toSubscribe( HttpKit.getInstance().getService(Api.class).getDeviceInfo()).map(new Func1<MagicDevice, BlueToothDevice>() {
//           @Override
//           public BlueToothDevice call(MagicDevice magicDevice) {
//               return BlueToothDevice.createWithLEAddress(magicDevice.macAddress);
//           }
//       });
//    }
}
