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

import android.text.TextUtils;

import com.google.gson.Gson;
import com.jyq.android.common.CommonKit;
import com.jyq.android.net.service.QiniuService;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.android.storage.UploadOptions;

import java.io.File;
import java.util.List;

import id.zelory.compressor.Compressor;
import rx.Notification;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Action2;
import rx.functions.Func1;
import rx.observables.AsyncOnSubscribe;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/3/22.
 */

public class UploadImageKit {
    private static final UploadImageKit ourInstance = new UploadImageKit();

    public static UploadImageKit getInstance() {
        return ourInstance;
    }

    private UploadManager manager;
    private UploadOptions options;
    private Compressor compressor;

    private UploadImageKit() {
        Configuration config = new Configuration.Builder()
                .chunkSize(256 * 1024)
                .connectTimeout(10)
                .putThreshhold(512 * 1024)
                .responseTimeout(60)
                .retryMax(3)
                .build();
        manager = new UploadManager(config);
        compressor = new Compressor.Builder(CommonKit.getInstance().getContext())
                .setQuality(75)
                .build();
    }

    public  Observable<String> uploadSchoolAlbum(final  List<String> photos){
        return upload(photos,UploadType.SCHOOL_ALBUM);
    }
    public  Observable<String> uploadDynamic(final  List<String> photos){
        return upload(photos,UploadType.DYNAMIC);
    }
    public  Observable<String> uploadAvatar(final  List<String> photos){
        return upload(photos,UploadType.AVATAR);
    }
    private Observable<String> upload(final List<String> imagePaths, final UploadType type) {
        if (imagePaths == null || imagePaths.size() == 0) {
            return Observable.empty();
        }
       return QiniuService.getQiniuToken().flatMap(new Func1<String, Observable<String>>() {
            @Override
            public Observable<String> call(final String token) {
                return Observable.from(imagePaths)
                        .flatMap(new Func1<String, Observable<String>>() {
                            @Override
                            public Observable<String> call(String s) {
                                return _upload(token, s, type);
                            }
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        ;
            }
        });
    }

    private Observable<String> _upload(final String token, final String imagePath, final UploadType type) {

        return Observable.create(AsyncOnSubscribe.createStateless(new Action2<Long, Observer<Observable<? extends String>>>() {
            @Override
            public void call(Long aLong, Observer<Observable<? extends String>> observableObserver) {
                File file = compressorImage(imagePath);
                if (file == null) {
                    observableObserver.onNext(Observable.<String>empty());
                    return;
                }
                ResponseInfo responseInfo = manager.syncPut(file, type.getFileName(), token, null);
                if (responseInfo.isOK()) {
                    UploadImage image = new Gson().fromJson(responseInfo.response.toString(), UploadImage.class);
                    observableObserver.onNext(Observable.just(image.key));
                    observableObserver.onCompleted();
                }
            }
        }));
    }

    private File compressorImage(String imagePath) {
        if (TextUtils.isEmpty(imagePath)) {
            return null;
        }
        File file = new File(imagePath);
        if (!file.exists() || file.isDirectory()) {
            return null;
        }
        return compressor.compressToFile(file);
    }
}
