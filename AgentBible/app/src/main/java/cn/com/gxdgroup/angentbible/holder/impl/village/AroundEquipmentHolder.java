package cn.com.gxdgroup.angentbible.holder.impl.village;

import android.support.v4.app.FragmentActivity;
import android.view.View;

import cn.com.gxdgroup.angentbible.R;
import cn.com.gxdgroup.angentbible.holder.BaseHolder;
import cn.com.gxdgroup.angentbible.utils.UIUtils;

/**
 * Created by Ivy on 2016/10/18.
 *
 * @description:
 */

public class AroundEquipmentHolder extends BaseHolder {
    public AroundEquipmentHolder(FragmentActivity activity) {
        super(activity);
    }

    @Override
    public View setContentView() {
        View view = View.inflate(mActivity, R.layout.holder_around_equipment, null);
        return view;
    }

    @Override
    public void setData() {

    }
}
