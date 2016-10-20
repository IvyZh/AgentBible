package cn.com.gxdgroup.angentbible.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.com.gxdgroup.angentbible.R;
import cn.com.gxdgroup.angentbible.adapter.HouseInfoAdapter;
import cn.com.gxdgroup.angentbible.base.BaseActivity;

/**
 * Created by Ivy on 2016/10/18.
 *
 * @description: 房源列表界面
 */

public class HouseResourcesListActivity extends BaseActivity {
    @BindView(R.id.lv_second_hand_house)
    ListView mLvSecondHandHouse;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.iv_search)
    ImageView mIvSearch;
    @BindView(R.id.rl_search)
    RelativeLayout mRlSearch;
    @BindView(R.id.tv_location)
    TextView mTvLocation;
    @BindView(R.id.iv_location_arrow)
    ImageView mIvLocationArrow;
    @BindView(R.id.rl_location)
    RelativeLayout mRlLocation;
    @BindView(R.id.tv_price)
    TextView mTvPrice;
    @BindView(R.id.iv_price_arrow)
    ImageView mIvPriceArrow;
    @BindView(R.id.rl_price)
    RelativeLayout mRlPrice;
    @BindView(R.id.tv_source)
    TextView mTvSource;
    @BindView(R.id.iv_source_arrow)
    ImageView mIvSourceArrow;
    @BindView(R.id.rl_source)
    RelativeLayout mRlSource;
    @BindView(R.id.tv_more)
    TextView mTvMore;
    @BindView(R.id.iv_more_arrow)
    ImageView mIvMoreArrow;
    @BindView(R.id.rl_more)
    RelativeLayout mRlMore;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_second_hand_house);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {
        ArrayList<String> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add("zhang san " + i);
        }

        HouseInfoAdapter adapter = new HouseInfoAdapter(this, data, R.layout.item_house_info);

        mLvSecondHandHouse.setAdapter(adapter);
        mLvSecondHandHouse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> view, View view1, int i, long l) {
                startActivity(new Intent(mContext, SeconHandHouseDetailsActivity.class));
            }
        });

    }


    @OnClick({R.id.iv_back, R.id.rl_search, R.id.rl_location, R.id.rl_price, R.id.rl_source, R.id.rl_more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_search:
                startActivity(new Intent(this, SearchActivity.class));
                break;
            case R.id.rl_location:
                break;
            case R.id.rl_price:
                break;
            case R.id.rl_source:
                break;
            case R.id.rl_more:
                break;
        }
    }
}
