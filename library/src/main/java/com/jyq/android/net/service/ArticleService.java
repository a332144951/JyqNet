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
import com.jyq.android.net.modal.Article;
import com.jyq.android.net.modal.BaseResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2017/3/21.
 */

public class ArticleService extends BaseService {
    private interface  Api{
        @POST("/api/article/list")
        Observable<BaseResponse<List<Article>>> getPublicArticles(@Body Map page);
    }

    public static Observable<List<Article>> getPublicArticles(int page){
        Map map=new HashMap();
        map.put("page",page);
        return toSubscribe(HttpKit.getInstance().getService(Api.class).getPublicArticles(map));
    }
}
