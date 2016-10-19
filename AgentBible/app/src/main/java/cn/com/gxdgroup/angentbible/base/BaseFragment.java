package cn.com.gxdgroup.angentbible.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
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

    protected FragmentActivity mActivity;

    /**
     * 此方法可以得到上下文对象
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    /*
     * 返回一个需要展示的View
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = setContentView(inflater);
        ButterKnife.bind(this, view);
        initView(view);
        return view;
    }

    /**
     * 这里可以放置子类加载网络数据的内容
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //loadData();
    }

    public abstract void loadData();

    /**
     * 子类实现此抽象方法返回View进行展示
     *
     * @return
     */
    public abstract View setContentView(LayoutInflater inflater);

    /**
     * 初始化控件
     */
    protected abstract void initView(View view);

}
