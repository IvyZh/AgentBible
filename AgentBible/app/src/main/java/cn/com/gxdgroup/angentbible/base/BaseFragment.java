package cn.com.gxdgroup.angentbible.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by Ivy on 2016/10/14.
 *
 * @description:
 */

public abstract class BaseFragment extends Fragment {

    public FragmentActivity mActivity;

    /**
     * 此方法可以得到上下文对象
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    /*
     * 返回一个需要展示的View
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mActivity = getActivity();
        View view = inflaterView(inflater);
        ButterKnife.bind(this, view);
        initView(view);
        return view;
    }

    /*
     * 当Activity初始化之后可以在这里进行一些数据的初始化操作
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * 子类实现此抽象方法返回View进行展示
     *
     * @return
     */
    public abstract View inflaterView(LayoutInflater inflater);

    /**
     * 初始化控件
     */
    protected abstract void initView(View view);

    /**
     * 子类在此方法中实现数据的初始化
     */
    public abstract void initData();


}
