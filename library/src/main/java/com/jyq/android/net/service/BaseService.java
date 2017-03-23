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

import com.jyq.android.net.exception.ApiException;
import com.jyq.android.net.exception.TokenExpiredException;
import com.jyq.android.net.modal.BaseResponse;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/3/20.
 */

abstract class BaseService {

    protected static <T> Observable<T> toSubscribe(Observable<BaseResponse<T>> observable) {
        return observable.map(new HttpResultFunc<T>())
                .retryWhen(new TokenFunc<>())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends T>>() {
                    @Override
                    public Observable<? extends T> call(Throwable throwable) {
                        return Observable.error(ApiException.exception(throwable));
                    }
                })
                .compose(new ThreadTransformer<T>());

    }

    private static class TokenFunc<T> implements Func1<Observable<? extends Throwable>, Observable<?>> {

        @Override
        public Observable<?> call(Observable<? extends Throwable> observable) {

            return observable.flatMap(new Func1<Throwable, Observable<?>>() {
                @Override
                public Observable<?> call(Throwable throwable) {
                    if (throwable instanceof TokenExpiredException) {
//                        return AuthService.refreshToken().observeOn(Schedulers.io());
                    }
                    return Observable.error(throwable);
                }
            });
        }
    }

    private static class HttpResultFunc<T> implements Func1<BaseResponse<T>, T> {
        @Override
        public T call(BaseResponse<T> tBaseResponse) {
            if (!tBaseResponse.isSuccess()) {
                throw ApiException.exception(tBaseResponse.getErrorCode(), tBaseResponse.getMessage(), new Throwable("数据异常"));
            }
            return tBaseResponse.getData();
        }
    }

    private static class ThreadTransformer<T> implements Observable.Transformer<T, T> {
        @Override
        public Observable<T> call(Observable<T> tObservable) {
            return tObservable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());

        }
    }


}
