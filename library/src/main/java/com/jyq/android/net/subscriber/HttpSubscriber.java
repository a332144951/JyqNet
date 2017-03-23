package com.jyq.android.net.subscriber;

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

import android.util.Log;


import com.jyq.android.net.exception.ApiException;

import rx.Subscriber;

/**
 * Created by Administrator on 2016/10/17.
 */

public abstract class HttpSubscriber<T> extends Subscriber<T> {

    protected abstract void onSuccess(T t);

    protected abstract void onApiError(ApiException e);

    @Override
    public void onCompleted() {
        if (!isUnsubscribed()) {
            unsubscribe();
        }
    }

    @Override
    public void onError(Throwable e) {
        ApiException exception = ApiException.exception(e);
//        Toast.makeText(AppCache.getContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
        Log.e("httpError", exception.getMessage(), exception);
        onApiError(exception);
//        if(e instanceof ApiException){
//            onApiError((ApiException) e);
//        }else{
//            onApiError(ApiException.exception(e));
//        }
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }
}
