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
    HomeMarketHolder homeMarketHolder;

    @Override
    public View inflaterView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_home, null);
    }

    @Override
    protected void initView(View view) {
        homeMarketHolder = new HomeMarketHolder(mActivity);
        mFrImgsAd.addView(new HomeCarouselHolder(mActivity).getContentView());
        mFrMenu.addView(new HomeMenuHolder(mActivity).getContentView());
        mFrMarket.addView(homeMarketHolder.getContentView());
    }

    @Override
    public void initData() {
        homeMarketHolder.setData();
    }

}
