package cn.com.gxdgroup.angentbible.holder.impl.village;

import android.support.v4.app.FragmentActivity;
import android.view.View;

import cn.com.gxdgroup.angentbible.R;
import cn.com.gxdgroup.angentbible.holder.BaseHolder;
import cn.com.gxdgroup.angentbible.utils.UIUtils;

/**
 * Created by Ivy on 2016/10/18.
 *
 * @description: 小区信息-基本信息Holder
 */

public class VillageBasicHolder extends BaseHolder {


    public VillageBasicHolder(FragmentActivity activity) {
        super(activity);
    }

    @Override
    public View setContentView() {
        return UIUtils.inflate(R.layout.holder_village_basic);
    }

    @Override
    public void setData() {

    }


}