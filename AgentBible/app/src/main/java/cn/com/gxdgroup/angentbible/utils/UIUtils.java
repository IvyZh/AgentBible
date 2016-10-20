package cn.com.gxdgroup.angentbible.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Toast;

import cn.com.gxdgroup.angentbible.base.MyApplication;

public class UIUtils {
    public static Toast mToast;

    public static void showToast(final String msg) {
        if (mToast == null) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mToast = Toast.makeText(getContext(), "", Toast.LENGTH_SHORT);
                }
            });
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mToast.setText(msg);
                mToast.show();
            }
        });
    }


    public static Context getContext() {
        return MyApplication.getContext();
    }

    public static Resources getResources() {
        return MyApplication.getContext().getResources();
    }


    public static void runOnUiThread(Runnable runnable, long delayMillis) {
        if (android.os.Process.myTid() == MyApplication.getMainTid()) {
            if (delayMillis != 0) {
                MyApplication.getHandler().postDelayed(runnable, delayMillis);
            } else {
                runnable.run();
            }
        } else {
            MyApplication.getHandler().postDelayed(runnable, delayMillis);
        }
    }

    public static void runOnUiThread(Runnable runnable) {
        runOnUiThread(runnable, 0);
    }

    public static int getColor(int colorId) {
        return getResources().getColor(colorId);
    }

    public static View inflate(int viewId) {
        return View.inflate(getContext(), viewId, null);
    }

    public static Drawable getDrawable(int id) {
        Drawable drawable = getResources().getDrawable(id);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());//必须设置图片大小，否则不显示
        return drawable;
    }

    public static Typeface getTypeFaceHelveticaNeue() {
        return Typeface.createFromAsset(getContext().getAssets(), "HelveticaNeueLTPro-Th_0.ttf");
//        return Typeface.createFromAsset(getContext().getAssets(), "fonts/HelveticaNeueLTPro-Th_0.ttf");
    }

}