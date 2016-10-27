package cn.com.gxdgroup.angentbible.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.com.gxdgroup.angentbible.R;
import cn.com.gxdgroup.angentbible.activities.CollectionActivity;
import cn.com.gxdgroup.angentbible.activities.LoginActivity;
import cn.com.gxdgroup.angentbible.base.BaseFragment;
import cn.com.gxdgroup.angentbible.constant.MenuType;
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
    @BindView(R.id.tv_nickname)
    TextView mTvNickname;
    private Tencent mTencent;

    @Override
    public View setContentView(LayoutInflater inflater) {
        return UIUtils.inflate(R.layout.fragment_me);
    }

    @Override
    protected void initView(View view) {
        mTencent = Tencent.createInstance("1105165474", mActivity);
    }

    @Override
    public void loadData() {
        L.v("MeFragment load data...");
    }


    @OnClick({R.id.iv_portrait, R.id.rl_collection_house, R.id.rl_collection_people, R.id.rl_feedback, R.id.rl_invite, R.id.rl_update, R.id.rl_setting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_collection_house:
                CollectionActivity.startActivity(mActivity, MenuType.COLL_HOUSE);
                break;
            case R.id.rl_collection_people:
                CollectionActivity.startActivity(mActivity, MenuType.COLL_KE);
                break;
            case R.id.rl_feedback:
                break;
            case R.id.rl_invite:
                share();
                break;
            case R.id.rl_update:
                shareToQzone();
                break;
            case R.id.rl_setting:
                break;
            case R.id.iv_portrait:
                startActivity(new Intent(mActivity, LoginActivity.class));
                break;
        }
    }

    private void shareToQzone() {
        //分享类型
        final Bundle params = new Bundle();
        params.putString(QzoneShare.SHARE_TO_QZONE_KEY_TYPE, QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT + "");
        params.putString(QzoneShare.SHARE_TO_QQ_TITLE, "标题123");//必填
        params.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, "摘要233");//选填
        params.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, "http://www.baidu.com");//必填
        ArrayList<String> urls = new ArrayList<>();
//        urls.add("http://imgcache.qq.com/qzone/space_item/pre/0/66768.gif");
        urls.add("http://img.educity.cn/img_21/359/2014062609/3225093910.png");

        params.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, urls);
        mTencent.shareToQzone(mActivity, params, new IUiListener() {

            @Override
            public void onComplete(Object o) {

            }

            @Override
            public void onError(UiError error) {

            }

            @Override
            public void onCancel() {

            }
        });
    }

    private void share() {
        final Bundle params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        params.putString(QQShare.SHARE_TO_QQ_TITLE, "要分享的标题");
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY, "要分享的摘要");
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, "http://www.163.com");
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, "http://imgcache.qq.com/qzone/space_item/pre/0/66768.gif");
        params.putString(QQShare.SHARE_TO_QQ_APP_NAME, "测试应用222222");
        params.putInt(QQShare.SHARE_TO_QQ_EXT_INT, 123);
        mTencent.shareToQQ(mActivity, params, new IUiListener() {

            @Override
            public void onComplete(Object o) {

            }

            @Override
            public void onError(UiError error) {

            }

            @Override
            public void onCancel() {

            }
        });
    }


    /**
     * 读取QQ的信息设置头像和昵称
     *
     * @param info
     */
    public void setAvatar(Object info) {
        JSONObject json = (JSONObject) info;
        try {
            String qq_2Ulr = json.getString("figureurl_qq_2");
            String nickname = json.getString("nickname");

            Glide.with(mActivity).load(qq_2Ulr).into(mIvPortrait);
            mTvNickname.setText(nickname);


        } catch (JSONException e) {
            e.printStackTrace();
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
