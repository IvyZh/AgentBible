package cn.com.gxdgroup.angentbible.fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import cn.com.gxdgroup.angentbible.R;
import cn.com.gxdgroup.angentbible.base.BaseFragment;
import cn.com.gxdgroup.angentbible.domain.MoviesBean;
import cn.com.gxdgroup.angentbible.holder.impl.ComingSoonHolder;
import cn.com.gxdgroup.angentbible.holder.impl.TheatersHolder;
import cn.com.gxdgroup.angentbible.net.Retrofit2Utils;
import cn.com.gxdgroup.angentbible.utils.L;
import cn.com.gxdgroup.angentbible.utils.UIUtils;

import java.util.List;

import butterknife.BindView;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by Ivy on 2016/10/14.
 *
 * @description:
 */

public class DataAnalysisFragments extends BaseFragment {
    @BindView(R.id.fr_in_theater)
    FrameLayout mFrInTheater;
    @BindView(R.id.fr_coming_soon)
    FrameLayout mFrComingSoon;

    private TheatersHolder theatersHolder;
    private ComingSoonHolder comingSoonHolder;
    private Observer mObserver;

    @Override
    public View inflaterView(LayoutInflater inflater) {
        L.v("inflaterView DataAnalysisFragments");
        View view = UIUtils.inflate(R.layout.fragment_movie);

        return view;
    }

    @Override
    protected void initView(View view) {

        theatersHolder = new TheatersHolder(mActivity);
        comingSoonHolder = new ComingSoonHolder(mActivity);

        mFrInTheater.addView(theatersHolder.getContentView());
        mFrComingSoon.addView(comingSoonHolder.getContentView());

        L.v("initView initView mObserver");

        mObserver = new Observer<MoviesBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(MoviesBean bean) {
                List<MoviesBean.SubjectsBean> subjects = bean.getSubjects();
                theatersHolder.setData(subjects);
            }

        };


    }

    @Override
    public void initData() {
        L.v("initView initData..");
        Observable<MoviesBean> observable = Retrofit2Utils.getServiceApi().getTheatersMoviesObservable("上海");

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mObserver);
    }


}
