package cn.com.gxdgroup.angentbible.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.com.gxdgroup.angentbible.R;
import cn.com.gxdgroup.angentbible.adapter.common.DataRankAdapter;
import cn.com.gxdgroup.angentbible.base.BaseActivity;
import cn.com.gxdgroup.angentbible.holder.impl.home.TrendChartHolder;
import cn.com.gxdgroup.angentbible.interfaces.SimpleAppTitleListener;
import cn.com.gxdgroup.angentbible.ui.AppTitleView;
import cn.com.gxdgroup.angentbible.ui.ObservableScrollView;
import cn.com.gxdgroup.angentbible.utils.L;
import cn.com.gxdgroup.angentbible.utils.UIUtils;

/**
 * Created by Ivy on 2016/10/22.
 *
 * @description: 具体的某一个数据分析 全市分析..人们区域 热门板块..端口...
 */

public class DataAnalysisActivity extends BaseActivity {

    @BindView(R.id.fr_deal_analysis)
    FrameLayout mFrDealAnalysis;
    @BindView(R.id.fr_deal_volume_analysis)
    FrameLayout mFrDealVolumeAnalysis;
    @BindView(R.id.fr_quoted_analysis)
    FrameLayout mFrQuotedAnalysis;
    @BindView(R.id.fr_pv_analysis)
    FrameLayout mFrPvAnalysis;
    @BindView(R.id.titleView)
    AppTitleView mTitleView;
    @BindView(R.id.tv_city_ana)
    TextView mTvCityAna;
    @BindView(R.id.tv_hot_area)
    TextView mTvHotArea;
    @BindView(R.id.tv_hot_bankuai)
    TextView mTvHotBankuai;
    @BindView(R.id.tv_hot_garden)
    TextView mTvHotGarden;
    @BindView(R.id.sv_city_ana)
    ObservableScrollView mSvCityAna;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.ll_tite_listview)
    LinearLayout mLlTiteListview;
    @BindView(R.id.ll_bottom_tab)
    LinearLayout mLlBottomTab;
    @BindView(R.id.tab_month_year)
    Switch mTabMonthYear;
    @BindView(R.id.lv_data)
    ListView mLvData;
    private int mDataType;//数据分析的类型

    public static void startActivity(Activity ba, int dataType) {
        Intent intent = new Intent(ba, DataAnalysisActivity.class);
        intent.putExtra("dataType", dataType);
        ba.startActivity(intent);
    }

    @Override
    protected void setContentView() {
        mDataType = getIntent().getIntExtra("dataType", 0);
        setContentView(R.layout.activity_data_analysis);

    }

    @Override
    public void initView() {


        mTitleView.setListener(new SimpleAppTitleListener(this));

        mTitleView.showMode(AppTitleView.MODE.TITLE, -1, null)
                .setTitleMsg("全市分析");

        if (mDataType <= 3) {
            mLlBottomTab.setVisibility(View.VISIBLE);
            mSvCityAna.setVisibility(View.VISIBLE);
            mLlTiteListview.setVisibility(View.GONE);
        } else {
            mLlBottomTab.setVisibility(View.GONE);
        }


        if (mDataType == 0) {
            //全市分析
            TrendChartHolder dealHolder = new TrendChartHolder(this);//成交分析
            dealHolder.setTitle("成交分析");
            mFrDealAnalysis.addView(dealHolder.getContentView());

            TrendChartHolder dealVolumenHolder = new TrendChartHolder(this);//成交总金额分析
            dealVolumenHolder.setTitle("成交总金额分析");
            mFrDealVolumeAnalysis.addView(dealVolumenHolder.getContentView());

            TrendChartHolder quotedHolder = new TrendChartHolder(this);//挂牌分析
            quotedHolder.setTitle("挂牌分析");
            mFrQuotedAnalysis.addView(quotedHolder.getContentView());

            TrendChartHolder pvHolder = new TrendChartHolder(this);//浏览量分析
            pvHolder.setTitle("浏览量分析");
            mFrPvAnalysis.addView(pvHolder.getContentView());
        } else if (mDataType >= 1 && mDataType <= 3) {//热门区域 热门板块 热门小区
            mSvCityAna.setVisibility(View.GONE);
            mLlTiteListview.setVisibility(View.VISIBLE);
            mTitleView.showMode(AppTitleView.MODE.TAB, -1, null)
                    .setTabLeftMsg("成交套数").setTabRightMsg("成交总价");
        } else if (mDataType == 4) {//发布量
            mSvCityAna.setVisibility(View.GONE);
            mLlTiteListview.setVisibility(View.VISIBLE);
            mTitleView.showMode(AppTitleView.MODE.TAB, -1, null)
                    .setTabLeftMsg("二手房发布量").setTabRightMsg("租房发布量");
        } else if (mDataType == 5) {//网站经纪人数
            mSvCityAna.setVisibility(View.GONE);
            mLlTiteListview.setVisibility(View.VISIBLE);
            mTitleView.showMode(AppTitleView.MODE.TITLE, -1, null)
                    .setTitleMsg("网站经纪人数");
        } else if (mDataType == 6) {// 签约量
            mSvCityAna.setVisibility(View.GONE);
            mLlTiteListview.setVisibility(View.VISIBLE);
            mTitleView.showMode(AppTitleView.MODE.TITLE, -1, null)
                    .setTitleMsg("签约量");
        } else if (mDataType == 7) {//端口数
            mSvCityAna.setVisibility(View.GONE);
            mLlTiteListview.setVisibility(View.VISIBLE);
            mTitleView.showMode(AppTitleView.MODE.TITLE, -1, null)
                    .setTitleMsg("端口数");
        }


        mSvCityAna.setScrollListener(new ObservableScrollView.ScrollListener() {
            @Override
            public void scrollOritention(int oritention) {
                L.v("scroll--" + oritention);
                if (oritention == 16) {//显示
                    mLlBottomTab.setVisibility(View.VISIBLE);
                } else {//隐藏
                    mLlBottomTab.setVisibility(View.GONE);
                }
            }
        });

        mLvData.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int i) {

            }

            @Override
            public void onScroll(AbsListView view, int i, int i1, int i2) {

            }
        });

    }


    @OnClick({R.id.tv_city_ana, R.id.tv_hot_area, R.id.tv_hot_bankuai, R.id.tv_hot_garden})
    public void onClick(View view) {
        int blueColor = UIUtils.getColor(R.color.common_blue);
        int whiteColor = UIUtils.getColor(R.color.white);

        mTvCityAna.setTextColor(whiteColor);
        mTvHotArea.setTextColor(whiteColor);
        mTvHotBankuai.setTextColor(whiteColor);
        mTvHotGarden.setTextColor(whiteColor);


        switch (view.getId()) {
            case R.id.tv_city_ana:
                mSvCityAna.setVisibility(View.VISIBLE);
                mLlTiteListview.setVisibility(View.GONE);
                mTitleView.showMode(AppTitleView.MODE.TITLE, -1, null)
                        .setTitleMsg("全市分析");
                mTvCityAna.setTextColor(blueColor);
                break;
            case R.id.tv_hot_area:
                mSvCityAna.setVisibility(View.GONE);
                mLlTiteListview.setVisibility(View.VISIBLE);
                mTitleView.showMode(AppTitleView.MODE.TAB, -1, null)
                        .setTabLeftMsg("成交套数").setTabRightMsg("成交总价");
                mTvHotArea.setTextColor(blueColor);
                break;
            case R.id.tv_hot_bankuai:
                mSvCityAna.setVisibility(View.GONE);
                mLlTiteListview.setVisibility(View.VISIBLE);
                mTitleView.showMode(AppTitleView.MODE.TAB, -1, null)
                        .setTabLeftMsg("成交套数").setTabRightMsg("成交总价");
                mTvHotBankuai.setTextColor(blueColor);
                break;
            case R.id.tv_hot_garden:
                mSvCityAna.setVisibility(View.GONE);
                mLlTiteListview.setVisibility(View.VISIBLE);
                mTitleView.showMode(AppTitleView.MODE.TITLE, -1, null)
                        .setTitleMsg("热门小区");
                mTvHotGarden.setTextColor(blueColor);
                break;
        }
    }

    @OnClick(R.id.sv_city_ana)
    public void onClick() {
    }


    @Override
    protected void loadData() {

        // 加载数据这块先暂时写在这个地方 后期再优化

        ArrayList<String> data = new ArrayList<>();
        for (int i = 1; i < 20; i++) {
            data.add(i + "");
        }

        DataRankAdapter adapter = new DataRankAdapter(this, data, R.layout.item_rank);
        mLvData.setAdapter(adapter);
    }
}
