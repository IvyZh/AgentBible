package cn.com.gxdgroup.angentbible.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.bigkoo.alertview.AlertView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.com.gxdgroup.angentbible.R;
import cn.com.gxdgroup.angentbible.base.BaseActivity;
import cn.com.gxdgroup.angentbible.holder.BaseHolder;
import cn.com.gxdgroup.angentbible.holder.impl.details.BannerHolder;
import cn.com.gxdgroup.angentbible.holder.impl.details.GardenPriceHolder;
import cn.com.gxdgroup.angentbible.holder.impl.details.HouseDealInDetailsHolder;
import cn.com.gxdgroup.angentbible.holder.impl.details.HouseFeatureHolder;
import cn.com.gxdgroup.angentbible.holder.impl.details.HouseIntroHolder;
import cn.com.gxdgroup.angentbible.holder.impl.details.SecondHandHouseInDetailsHolder;
import cn.com.gxdgroup.angentbible.holder.impl.details.ZuFangInDetailsHolder;
import cn.com.gxdgroup.angentbible.holder.impl.home.TrendChartHolder;
import cn.com.gxdgroup.angentbible.ui.TitleView;

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
    @BindView(R.id.titleView)
    TitleView mTitleView;

    private BaseHolder featureHolder, introHolder, bannerHolder, trendChartHolder, zuFangInDetailsHolder, secondHandHouseInDetailsHolder, dealInDetailsHolder;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_second_hand_house_details);
    }

    @Override
    protected void initView() {
        mTitleView.setLeftButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mTitleView.showRightImageView(true);
        mTitleView.setRightButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertView mAlertView = new AlertView("标题", "内容", "取消", new String[]{"确定"}, null, mContext, AlertView.Style.Alert, null).setCancelable(true);
                mAlertView.show();
            }
        });


        bannerHolder = new BannerHolder(this);
        featureHolder = new HouseFeatureHolder(this);
        introHolder = new HouseIntroHolder(this);
        trendChartHolder = new TrendChartHolder(this);
        zuFangInDetailsHolder = new ZuFangInDetailsHolder(this);
        secondHandHouseInDetailsHolder = new SecondHandHouseInDetailsHolder(this);
        dealInDetailsHolder = new HouseDealInDetailsHolder(this);

        mFrBanner.addView(bannerHolder.getContentView());//轮播
        mFrHouseFeature.addView(featureHolder.getContentView());//房源特征
        mFrHouseIntro.addView(introHolder.getContentView());//房源介绍
        mFrGardenPrice.addView(trendChartHolder.getContentView());//小区价格走势
        mFrZufang.addView(zuFangInDetailsHolder.getContentView());//小区租房
        mFrSecondHouse.addView(secondHandHouseInDetailsHolder.getContentView());//小区二手房
        mFrHouseDeal.addView(dealInDetailsHolder.getContentView());//小区成交记录
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {
        featureHolder.setData();
        ((TrendChartHolder) trendChartHolder).setLineDataNum(2);
        ((TrendChartHolder) trendChartHolder).setTitle("小区价格走势");
        ((TrendChartHolder) trendChartHolder).setLocationVisibility(false);
        trendChartHolder.setData();
    }


    public void contact(View v) {
        startActivity(new Intent(this, ContactActivity.class));
    }
}
