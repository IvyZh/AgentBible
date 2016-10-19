package cn.com.gxdgroup.angentbible.holder.impl.details;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;
import cn.com.gxdgroup.angentbible.R;
import cn.com.gxdgroup.angentbible.activities.VillageInfoActivity;
import cn.com.gxdgroup.angentbible.holder.BaseHolder;
import cn.com.gxdgroup.angentbible.utils.UIUtils;

/**
 * Created by Ivy on 2016/10/18.
 *
 * @description: 二手房详情里面的Holder
 */

public class HouseFeatureHolder extends BaseHolder {

    @BindView(R.id.tv_publish_time)
    TextView mTvPublishTime;
    @BindView(R.id.tv_house_price)
    TextView mTvHousePrice;
    @BindView(R.id.tv_house_layout)
    TextView mTvHouseLayout;
    @BindView(R.id.tv_house_area)
    TextView mTvHouseArea;
    @BindView(R.id.tv_unit_price)
    TextView mTvUnitPrice;
    @BindView(R.id.tv_house_fitment)
    TextView mTvHouseFitment;
    @BindView(R.id.tv_house_old)
    TextView mTvHouseOld;
    @BindView(R.id.tv_house_floor)
    TextView mTvHouseFloor;
    @BindView(R.id.tv_house_orientation)
    TextView mTvHouseOrientation;
    @BindView(R.id.tv_house_location)
    TextView mTvHouseLocation;
    @BindView(R.id.tv_garden_name_label)
    TextView mTvGardenNameLabel;
    @BindView(R.id.tv_garden_name)
    TextView mTvGardenName;
    @BindView(R.id.rl_garden_name)
    RelativeLayout mRlGardenName;

    public HouseFeatureHolder(FragmentActivity activity) {
        super(activity);
    }

    @Override
    public View setContentView() {
        return UIUtils.inflate(R.layout.holder_house_feature);
    }

    @Override
    public void setData() {
        mTvGardenName.setText("湖滨半岛公馆" + new Random().nextInt(99));
    }

    @OnClick(R.id.rl_garden_name)
    public void onClick() {
        mActivity.startActivity(new Intent(mActivity, VillageInfoActivity.class));
    }

}
