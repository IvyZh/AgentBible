package cn.com.gxdgroup.angentbible.holder.impl.village;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import cn.com.gxdgroup.angentbible.holder.BaseHolder;

/**
 * Created by Ivy on 2016/10/20.
 *
 * @description:
 */

public class EquipmentMapDetialsHolder extends BaseHolder {
    public EquipmentMapDetialsHolder(FragmentActivity activity) {
        super(activity);
    }

    @Override
    public View setContentView() {
        TextView view = new TextView(mActivity);

        view.setText(" 周边配套 详情....");
        view.setTextSize(40);
        return view;
    }

    @Override
    public void setData() {

    }
}
