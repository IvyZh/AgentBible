package cn.com.gxdgroup.angentbible.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.com.gxdgroup.angentbible.R;
import cn.com.gxdgroup.angentbible.base.BaseFragment;
import cn.com.gxdgroup.angentbible.holder.impl.home.HomeCarouselHolder;
import cn.com.gxdgroup.angentbible.holder.impl.home.HomeMarketHolder;
import cn.com.gxdgroup.angentbible.holder.impl.home.HomeMenuHolder;

/**
 * Created by Ivy on 2016/10/14.
 *
 * @description:
 */

public class HomeFragment extends BaseFragment {
    @BindView(R.id.fr_imgs_ad)
    FrameLayout mFrImgsAd;
    @BindView(R.id.fr_menu)
    FrameLayout mFrMenu;
    @BindView(R.id.fr_market)
    FrameLayout mFrMarket;

    @Override
    public View inflaterView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_home, null);
    }

    @Override
    protected void initView(View view) {

        mFrImgsAd.addView(new HomeCarouselHolder(mActivity).getContentView());
        mFrMenu.addView(new HomeMenuHolder(mActivity).getContentView());
        mFrMarket.addView(new HomeMarketHolder(mActivity).getContentView());
    }

    @Override
    public void initData() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
