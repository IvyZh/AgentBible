package cn.com.gxdgroup.angentbible.holder.impl.details;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

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
    @BindView(R.id.tv_temp_garden)
    TextView mTvTempGarden;

    public HouseFeatureHolder(FragmentActivity activity) {
        super(activity);
    }

    @Override
    public View setContentView() {
        return UIUtils.inflate(R.layout.holder_house_feature);
    }

    @OnClick(R.id.tv_temp_garden)
    public void onClick() {
        mActivity.startActivity(new Intent(mActivity, VillageInfoActivity.class));
    }
}
