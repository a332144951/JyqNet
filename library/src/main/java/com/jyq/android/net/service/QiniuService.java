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



import com.jyq.android.common.CommonKit;
import com.jyq.android.common.cache.CommonCache;
import com.jyq.android.net.HttpKit;
import com.jyq.android.net.modal.BaseResponse;
import com.jyq.android.net.modal.CommonContent;

import retrofit2.http.POST;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Administrator on 2016/10/17.
 */

public class QiniuService extends BaseService{
    private interface Api{
        @POST("/api/qiniu/get-token")
        Observable<BaseResponse<CommonContent>> qiniuToken();

        @POST("/api/qiniu/domain")
        Observable<BaseResponse<CommonContent>> qiniuUrl();
    }

    public static Observable<String> getQiniuToken(){
       return toSubscribe(HttpKit.getInstance().getService(Api.class).qiniuToken())
               .map(new Func1<CommonContent, String>() {
                   @Override
                   public String call(CommonContent commonContent) {
                       return commonContent.content;
                   }
               })
               ;
    }

    public static Observable<String> getQiniuUrl(){
        return  toSubscribe(HttpKit.getInstance().getService(Api.class).qiniuUrl())
                .map(new Func1<CommonContent, String>() {
                    @Override
                    public String call(CommonContent commonContent) {
                        return commonContent.content;
                    }
                })
                .doOnNext(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        CommonCache.getInstance().updateImageHost(s);
                    }
                })
                ;
    }

}
