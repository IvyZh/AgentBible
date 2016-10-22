package cn.com.gxdgroup.angentbible.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.com.gxdgroup.angentbible.R;
import cn.com.gxdgroup.angentbible.base.BaseActivity;
import cn.com.gxdgroup.angentbible.holder.impl.home.TrendChartHolder;
import cn.com.gxdgroup.angentbible.interfaces.SimpleAppTitleListener;
import cn.com.gxdgroup.angentbible.ui.AppTitleView;

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
    protected void initView() {

        mTitleView.showMode(AppTitleView.MODE.TITLE, -1, null)
                .setTitleMsg("全市分析");

        mTitleView.setListener(new SimpleAppTitleListener(this));


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

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_city_ana, R.id.tv_hot_area, R.id.tv_hot_bankuai, R.id.tv_hot_garden})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_city_ana:
                break;
            case R.id.tv_hot_area:
                break;
            case R.id.tv_hot_bankuai:
                break;
            case R.id.tv_hot_garden:
                break;
        }
    }
}
