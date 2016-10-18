package cn.com.gxdgroup.angentbible.holder.impl.home;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import butterknife.OnClick;
import cn.com.gxdgroup.angentbible.R;
import cn.com.gxdgroup.angentbible.activities.SecondHandHouseActivity;
import cn.com.gxdgroup.angentbible.holder.BaseHolder;
import cn.com.gxdgroup.angentbible.utils.UIUtils;

/**
 * Created by Ivy on 2016/10/17.
 *
 * @description: 首页四大模块的Holder
 */

public class HomeMenuHolder extends BaseHolder {
    public HomeMenuHolder(FragmentActivity activity) {
        super(activity);
    }

    @Override
    public View setContentView() {
        return UIUtils.inflate(R.layout.holder_home_menu);
    }

    @OnClick({R.id.rl_second_hand_house, R.id.rl_tenement, R.id.rl_keyuan, R.id.rl_deal})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_second_hand_house:
                mActivity.startActivity(new Intent(mActivity, SecondHandHouseActivity.class));
                break;
            case R.id.rl_tenement:
                break;
            case R.id.rl_keyuan:
                break;
            case R.id.rl_deal:
                break;
        }
    }
}
