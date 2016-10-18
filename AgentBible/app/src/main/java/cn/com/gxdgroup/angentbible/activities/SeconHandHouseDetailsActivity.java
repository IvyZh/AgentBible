package cn.com.gxdgroup.angentbible.activities;

import android.os.Bundle;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.com.gxdgroup.angentbible.R;
import cn.com.gxdgroup.angentbible.base.BaseActivity;
import cn.com.gxdgroup.angentbible.holder.impl.details.BannerHolder;
import cn.com.gxdgroup.angentbible.holder.impl.details.GardenPriceHolder;
import cn.com.gxdgroup.angentbible.holder.impl.details.HouseDealInDetailsHolder;
import cn.com.gxdgroup.angentbible.holder.impl.details.HouseFeatureHolder;
import cn.com.gxdgroup.angentbible.holder.impl.details.HouseIntroHolder;
import cn.com.gxdgroup.angentbible.holder.impl.details.SecondHandHouseInDetailsHolder;
import cn.com.gxdgroup.angentbible.holder.impl.details.ZuFangInDetailsHolder;

/**
 * Created by Ivy on 2016/10/18.
 *
 * @description: 二手房详情界面
 */

public class SeconHandHouseDetailsActivity extends BaseActivity {
    @BindView(R.id.fr_banner)
    FrameLayout mFrBanner;
    @BindView(R.id.fr_house_intro)
    FrameLayout mFrHouseIntro;
    @BindView(R.id.fr_garden_price)
    FrameLayout mFrGardenPrice;
    @BindView(R.id.fr_second_house)
    FrameLayout mFrSecondHouse;
    @BindView(R.id.fr_zufang)
    FrameLayout mFrZufang;
    @BindView(R.id.fr_house_deal)
    FrameLayout mFrHouseDeal;
    @BindView(R.id.fr_house_feature)
    FrameLayout mFrHouseFeature;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_second_hand_house_details);
    }

    @Override
    protected void initView() {
        mFrBanner.addView(new BannerHolder(this).getContentView());
        mFrHouseFeature.addView(new HouseFeatureHolder(this).getContentView());
        mFrHouseIntro.addView(new HouseIntroHolder(this).getContentView());
        mFrGardenPrice.addView(new GardenPriceHolder(this).getContentView());

        mFrZufang.addView(new ZuFangInDetailsHolder(this).getContentView());
        mFrSecondHouse.addView(new SecondHandHouseInDetailsHolder(this).getContentView());
        mFrHouseDeal.addView(new HouseDealInDetailsHolder(this).getContentView());
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

    }


}
