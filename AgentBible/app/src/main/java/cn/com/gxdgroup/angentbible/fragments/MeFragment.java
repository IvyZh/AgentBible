package cn.com.gxdgroup.angentbible.fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import cn.com.gxdgroup.angentbible.base.BaseFragment;


/**
 * Created by Ivy on 2016/10/14.
 *
 * @description:
 */

public class MeFragment extends BaseFragment {
    @Override
    public View inflaterView(LayoutInflater inflater) {
        TextView view = new TextView(mActivity);
        view.setText("Music");

        return view;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    public void initData() {

    }
}
