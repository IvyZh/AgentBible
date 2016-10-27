package cn.com.gxdgroup.angentbible.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import cn.com.gxdgroup.angentbible.utils.AppManager;


/**
 * Created by Ivy on 2016/10/7.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected BaseActivity mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        AppManager.getAppManager().addActivity(this);// 添加Activity到堆栈

        setContentView();//需要设置setContentView
        ButterKnife.bind(this);//绑定ButterKnife
        initView();//初始化View
        initListener();//监听
        loadData();//加载数据
    }

    /**
     * setContentView
     */
    protected abstract void setContentView();

    /**
     * 初始化View中控件
     */
    public abstract void initView();

    /**
     * 给控件添加点击监听事件(选填，一般也可以使用ButterKnife)
     */
    protected void initListener() {
    }

    /**
     * 加载数据的网络操作(选填)
     */
    protected void loadData() {
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        // 结束Activity&从堆栈中移除
        AppManager.getAppManager().finishActivity(this);
    }
}
