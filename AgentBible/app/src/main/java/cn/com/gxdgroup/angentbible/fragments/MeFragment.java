package cn.com.gxdgroup.angentbible.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.com.gxdgroup.angentbible.R;
import cn.com.gxdgroup.angentbible.activities.CollectionActivity;
import cn.com.gxdgroup.angentbible.activities.LoginActivity;
import cn.com.gxdgroup.angentbible.base.BaseFragment;
import cn.com.gxdgroup.angentbible.utils.L;
import cn.com.gxdgroup.angentbible.utils.UIUtils;


/**
 * Created by Ivy on 2016/10/14.
 *
 * @description:
 */

public class MeFragment extends BaseFragment {
    @BindView(R.id.iv_portrait)
    ImageView mIvPortrait;
    @BindView(R.id.iv_scfy)
    ImageView mIvScfy;
    @BindView(R.id.rl_collection_house)
    RelativeLayout mRlCollectionHouse;
    @BindView(R.id.iv_scky)
    ImageView mIvScky;
    @BindView(R.id.rl_collection_people)
    RelativeLayout mRlCollectionPeople;
    @BindView(R.id.iv_reflact)
    ImageView mIvReflact;
    @BindView(R.id.rl_feedback)
    RelativeLayout mRlFeedback;
    @BindView(R.id.iv_invite)
    ImageView mIvInvite;
    @BindView(R.id.rl_invite)
    RelativeLayout mRlInvite;
    @BindView(R.id.iv_update)
    ImageView mIvUpdate;
    @BindView(R.id.rl_update)
    RelativeLayout mRlUpdate;
    @BindView(R.id.iv_setting)
    ImageView mIvSetting;
    @BindView(R.id.rl_setting)
    RelativeLayout mRlSetting;

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


    @OnClick({R.id.iv_portrait, R.id.rl_collection_house, R.id.rl_collection_people, R.id.rl_feedback, R.id.rl_invite, R.id.rl_update, R.id.rl_setting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_collection_house:
                CollectionActivity.startActivity(mActivity, 4);
                break;
            case R.id.rl_collection_people:
                CollectionActivity.startActivity(mActivity, 5);
                break;
            case R.id.rl_feedback:
                break;
            case R.id.rl_invite:
                break;
            case R.id.rl_update:
                break;
            case R.id.rl_setting:
                break;
            case R.id.iv_portrait:
                startActivity(new Intent(mActivity, LoginActivity.class));
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

}
