package cn.com.gxdgroup.angentbible.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.com.gxdgroup.angentbible.R;
import cn.com.gxdgroup.angentbible.adapter.HouseInfoAdapter;
import cn.com.gxdgroup.angentbible.base.BaseActivity;
import cn.com.gxdgroup.angentbible.holder.impl.selector.SelectionHolder;

/**
 * Created by Ivy on 2016/10/18.
 *
 * @description: 房源列表界面
 */

public class HouseResourcesListActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.rl_search)
    RelativeLayout rlSearch;
    @BindView(R.id.fr_selection)
    FrameLayout frSelection;
    @BindView(R.id.lv_house_res)
    ListView lvHouseRes;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_second_hand_house);
    }

    @Override
    protected void initView() {

        SelectionHolder selectionHolder = new SelectionHolder(this);
        frSelection.addView(selectionHolder.getContentView());

//        selectionHolder.setSelectText(new String[]{"1","2","","5"});
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

        lvHouseRes.setAdapter(adapter);
        lvHouseRes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> view, View view1, int i, long l) {
                startActivity(new Intent(mContext, SeconHandHouseDetailsActivity.class));
            }
        });

    }


    @OnClick({R.id.iv_back, R.id.rl_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_search:
                startActivity(new Intent(this, SearchActivity.class));
                break;
        }
    }
}
