package cn.com.gxdgroup.angentbible.fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import cn.com.gxdgroup.angentbible.R;
import cn.com.gxdgroup.angentbible.base.BaseFragment;
import cn.com.gxdgroup.angentbible.utils.L;
import cn.com.gxdgroup.angentbible.utils.UIUtils;


/**
 * Created by Ivy on 2016/10/14.
 *
 * @description:
 */

public class MeFragment extends BaseFragment {
    @Override
    public View setContentView(LayoutInflater inflater) {
        return UIUtils.inflate(R.layout.fragment_me);
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    public void loadData() {
        L.v("MeFragment load data...");
    }
}
