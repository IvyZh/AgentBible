package cn.com.gxdgroup.angentbible.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.com.gxdgroup.angentbible.R;
import cn.com.gxdgroup.angentbible.base.BaseActivity;
import cn.com.gxdgroup.angentbible.holder.BaseHolder;
import cn.com.gxdgroup.angentbible.holder.impl.details.BannerHolder;
import cn.com.gxdgroup.angentbible.holder.impl.details.HouseDealInDetailsHolder;
import cn.com.gxdgroup.angentbible.holder.impl.details.SecondHandHouseInDetailsHolder;
import cn.com.gxdgroup.angentbible.holder.impl.details.ZuFangInDetailsHolder;
import cn.com.gxdgroup.angentbible.holder.impl.home.TrendChartHolder;
import cn.com.gxdgroup.angentbible.holder.impl.village.GardenMagazineHolder;
import cn.com.gxdgroup.angentbible.holder.impl.village.StaticMapHolder;
import cn.com.gxdgroup.angentbible.holder.impl.village.VillageBasicHolder;
import cn.com.gxdgroup.angentbible.ui.TitleView;

/**
 * Created by Ivy on 2016/10/18.
 *
 * @description: 小区信息页面
 */

public class VillageInfoActivity extends BaseActivity {


    @BindView(R.id.fr_banner)
    FrameLayout mFrBanner;
    @BindView(R.id.fr_village_basic)
    FrameLayout mFrVillageBasic;
    @BindView(R.id.fr_garden_price)
    FrameLayout mFrGardenPrice;
    @BindView(R.id.fr_garden_magazine)
    FrameLayout mFrGardenMagazine;
    @BindView(R.id.fr_arround_equipment)
    FrameLayout mFrArroundEquipment;
    @BindView(R.id.fr_second_house)
    FrameLayout mFrSecondHouse;
    @BindView(R.id.fr_zufang)
    FrameLayout mFrZufang;
    @BindView(R.id.fr_house_deal)
    FrameLayout mFrHouseDeal;
    @BindView(R.id.titleView)
    TitleView mTitleView;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
//    @BindView(R.id.iv_map)
//    ImageView mIvMap;

    private BaseHolder trendChartHolder;
//    private TextureMapView mMapView;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_village_info);
    }

    @Override
    public void initView() {

        trendChartHolder = new TrendChartHolder(this);

        //http://api.map.baidu.com/staticimage?width=720&height=400&center=121.433346,31.1882&zoom=15&markers=121.433346,31.1882&markerStyles=l,0

//        AroundEquipmentHolder equipmentHolder = new AroundEquipmentHolder(this);

        mFrBanner.addView(new BannerHolder(this).getContentView());
        mFrVillageBasic.addView(new VillageBasicHolder(this).getContentView());
        mFrGardenPrice.addView(trendChartHolder.getContentView());


        mFrGardenMagazine.addView(new GardenMagazineHolder(this).getContentView());

        StaticMapHolder staticMapHolder = new StaticMapHolder(this);

        mFrArroundEquipment.addView(staticMapHolder.getContentView());


        mFrZufang.addView(new ZuFangInDetailsHolder(this).getContentView());
        mFrSecondHouse.addView(new SecondHandHouseInDetailsHolder(this).getContentView());
        mFrHouseDeal.addView(new HouseDealInDetailsHolder(this).getContentView());

        mTitleView.setRightButtonVisibility(View.GONE);
        mTitleView.setLeftButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


//        mMapView = equipmentHolder.getMapView();

//        String url = "http://api.map.baidu.com/staticimage?width=720&height=400&center=121.433346,31.1882&zoom=15&markers=121.433346,31.1882&markerStyles=l,0";
//        Glide.with(mContext).load(url).into(mIvMap);


    }


    @Override
    protected void loadData() {
        ((TrendChartHolder) trendChartHolder).setTitle("小区价格走势");
        ((TrendChartHolder) trendChartHolder).setLocationVisibility(false);
        ((TrendChartHolder) trendChartHolder).setLineDataNum(2);
        trendChartHolder.setData();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
//        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
//        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
//        mMapView.onPause();
    }

    public ScrollView getScrollView() {
        return scrollView;
    }


}
