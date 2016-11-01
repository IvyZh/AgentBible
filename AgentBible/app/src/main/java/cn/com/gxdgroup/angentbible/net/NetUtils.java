package cn.com.gxdgroup.angentbible.net;

import com.tencent.mm.sdk.modelbase.BaseResp;

import org.json.JSONObject;

import cn.com.gxdgroup.angentbible.domain.MoviesBean;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Ivy on 2016/11/1.
 *
 * @description:
 */

public class NetUtils {
    private static class NetUtilsSingletonHolder {
        private static final NetUtils instance = new NetUtils();
    }

    public static NetUtils getInstance() {
        return NetUtilsSingletonHolder.instance;
    }

    private class ComposeThread<T> implements Observable.Transformer<T, T> {
        @Override
        public Observable<T> call(Observable<T> observable) {
            return observable
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    }

    public void getBooks(String id, Action1 onNext) {
        Retrofit2Utils.getServiceApi().getBookObservable(id).compose(new ComposeThread<MoviesBean>()).subscribe(onNext);
    }
}
