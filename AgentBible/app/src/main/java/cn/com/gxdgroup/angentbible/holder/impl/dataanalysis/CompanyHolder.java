package cn.com.gxdgroup.angentbible.holder.impl.dataanalysis;

import android.support.v4.app.FragmentActivity;
import android.view.View;

import cn.com.gxdgroup.angentbible.R;
import cn.com.gxdgroup.angentbible.holder.BaseHolder;
import cn.com.gxdgroup.angentbible.utils.UIUtils;

/**
 * Created by Ivy on 2016/10/20.
 *
 * @description: 公司
 */

public class CompanyHolder extends BaseHolder {
    public CompanyHolder(FragmentActivity activity) {
        super(activity);
    }

    @Override
    public View setContentView() {
        return UIUtils.inflate(R.layout.holder_company);
    }

    @Override
    public void setData() {

    }
}
