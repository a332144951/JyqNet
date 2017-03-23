package com.jyq.android.net.exception;

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

import com.google.gson.JsonSyntaxException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

/**
 * Created by Administrator on 2017/3/20.
 */

public class ApiException extends RuntimeException {
    private int mErrorCode;
    public ApiException(int errorCode,String errorMessage,Throwable cause) {
        super(errorMessage,cause);
        this.mErrorCode=errorCode;

    }
    public int getErrorCode() {
        return mErrorCode;
    }
    public static ApiException exception(Throwable t){
        if(t instanceof ApiException){
            return (ApiException) t;
        }
        if(t instanceof JsonSyntaxException){

            return new ApiException(2,"解析错误",t);
        }
        if (t instanceof ConnectException){
            return new ApiException(1,"网络连接失败，请检查网络设置",t);
        }
        if (t instanceof SocketTimeoutException){
            return new ApiException(1,"连接超时",t);
        }
        return new ApiException(-1,"未知错误",t);
    }
    public static ApiException IMException(int code,Throwable cause){
        return new ApiException(code,"IM系统错误",cause);
    }
    public static ApiException exception(int errorCode, String errorMessage,Throwable cause){
        switch (errorCode){

            case ApiErrorCode.TOKEN_EXPIRED:
                return new TokenExpiredException(errorCode,errorMessage,cause);
            case ApiErrorCode.TOKEN_INVALID:
            case ApiErrorCode.TOKEN_REFRESH_EXPIRED:
                return new TokenRefreshException(errorCode,errorMessage,cause);
            case 500:
                return new ApiException(500,"服务器繁忙",cause);
            default:
                return new ApiException(errorCode, errorMessage,cause);
        }

    }
}
