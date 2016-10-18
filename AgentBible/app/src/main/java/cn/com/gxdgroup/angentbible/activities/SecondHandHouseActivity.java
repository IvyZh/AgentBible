package cn.com.gxdgroup.angentbible.activities;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import cn.com.gxdgroup.angentbible.R;
import cn.com.gxdgroup.angentbible.adapter.HouseInfoAdapter;
import cn.com.gxdgroup.angentbible.base.BaseActivity;

/**
 * Created by Ivy on 2016/10/18.
 *
 * @description: 二手房列表
 */

public class SecondHandHouseActivity extends BaseActivity {
    @BindView(R.id.lv_second_hand_house)
    ListView mLvSecondHandHouse;

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
}
