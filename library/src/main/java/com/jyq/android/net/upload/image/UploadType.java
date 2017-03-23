package com.jyq.android.net.upload.image;

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


import com.jyq.android.net.cache.HttpCache;
import com.jyq.android.net.modal.User;

import java.util.Locale;

/**
 * Created by Administrator on 2016/12/1.
 */

enum UploadType {
    DYNAMIC {
        @Override
        String getFileName() {
            User loginUser= HttpCache.getInstance().getLoginUser();
            return  String.format(Locale.CHINA,"moment/%d/%d/%d",loginUser.getSchool().id,loginUser.logicId,System.currentTimeMillis());
        }
    },
    AVATAR {
        @Override
        String getFileName() {
            User loginUser= HttpCache.getInstance().getLoginUser();
            return  String.format(Locale.CHINA,"avatar/%d/%d",loginUser.logicId,System.currentTimeMillis());
        }
    },
    SCHOOL_ALBUM{
        @Override
        String getFileName() {
            User loginUser= HttpCache.getInstance().getLoginUser();
            return String.format(Locale.CHINA,"school/album/%d/%d",loginUser.getSchool().id,System.currentTimeMillis());
        }
    };

    abstract String getFileName();

}
