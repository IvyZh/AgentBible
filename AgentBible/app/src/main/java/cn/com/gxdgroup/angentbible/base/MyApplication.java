package cn.com.gxdgroup.angentbible.base;

import android.app.Application;
import android.os.Handler;

import cn.com.gxdgroup.angentbible.net.client.OkHttp3Utils;

import okhttp3.OkHttpClient;

/**
 * Created by Ivy on 2016/10/11.
 *
 * @description:
 */

public class MyApplication extends Application {
    private static MyApplication mContext;
    private static Handler handler;
    private static int mainTid;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        handler = new Handler();
        mainTid = android.os.Process.myTid();
        initOkHttp();//初始化OkHttp

    }

    /**
     * 初始化单例OkHttpClient对象
     */
    private void initOkHttp() {
        OkHttpClient client = OkHttp3Utils.getOkHttpClient();
    }

    public static MyApplication getContext() {
        return mContext;
    }

    public static Handler getHandler() {
        return handler;
    }

    public static int getMainTid() {
        return mainTid;
    }

}
