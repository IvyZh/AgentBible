package cn.com.gxdgroup.angentbible.activities;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import cn.com.gxdgroup.angentbible.R;
import cn.com.gxdgroup.angentbible.adapter.HouseInfoAdapter;
import cn.com.gxdgroup.angentbible.base.BaseActivity;
import cn.com.gxdgroup.angentbible.interfaces.SimpleAppTitleListener;
import cn.com.gxdgroup.angentbible.ui.AppTitleView;

/**
 * Created by Ivy on 2016/10/18.
 *
 * @description: 房源列表界面
 */

public class HouseResourcesListActivity extends BaseActivity {

    @BindView(R.id.lv_house_res)
    ListView lvHouseRes;
    @BindView(R.id.app_title)
    AppTitleView mAppTitle;

    private int mMenuType = 0;//0-二手房，1-租房，2-客源，3-最新成交

    public static void startActivity(Activity ba, int menuType) {
        Intent intent = new Intent(ba, HouseResourcesListActivity.class);
        intent.putExtra("mMenuType", menuType);
        ba.startActivity(intent);
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_house_res_list);
    }

    @Override
    public void initView() {

        mMenuType = getIntent().getIntExtra("mMenuType", 0);

        if (mMenuType == 2) {// 客源单独处理+要显示TAB
            mAppTitle.showMode(AppTitleView.MODE.TAB, mMenuType, this);
        } else {
            mAppTitle.showMode(AppTitleView.MODE.SEARCH, mMenuType, this);
        }

        mAppTitle.setListener(new SimpleAppTitleListener(mContext) {
            @Override
            public void OnrlSearch() {
                startActivity(new Intent(HouseResourcesListActivity.this, SearchActivity.class));
            }

            @Override
            public void onTabLeft() {
                super.onTabLeft();
            }

            @Override
            public void onTabRight() {
                super.onTabRight();
            }
        });
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

        HouseInfoAdapter adapter = new HouseInfoAdapter(this, data, R.layout.item_house_info, mMenuType);

        lvHouseRes.setAdapter(adapter);
        lvHouseRes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> view, View view1, int i, long l) {
//                startActivity(new Intent(mContext, HouseDetailsActivity.class));
                HouseDetailsActivity.startActivity(mContext, mMenuType);
            }
        });

    }


}
