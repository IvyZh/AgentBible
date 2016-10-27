package cn.com.gxdgroup.angentbible.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.com.gxdgroup.angentbible.R;
import cn.com.gxdgroup.angentbible.base.BaseActivity;
import cn.com.gxdgroup.angentbible.utils.L;
import cn.com.gxdgroup.angentbible.utils.UIUtils;

/**
 * Created by Ivy on 2016/10/24.
 *
 * @description: 登录界面
 */

public class LoginActivity extends BaseActivity {
    @BindView(R.id.iv_close)
    ImageView mIvClose;
    @BindView(R.id.iv_logo)
    ImageView mIvLogo;
    @BindView(R.id.et_username)
    EditText mEtUsername;
    @BindView(R.id.iv_delete)
    ImageView mIvDelete;
    @BindView(R.id.rl_username)
    RelativeLayout mRlUsername;
    @BindView(R.id.view_divide)
    View mViewDivide;
    @BindView(R.id.et_pwd)
    EditText mEtPwd;
    @BindView(R.id.view_divide2)
    View mViewDivide2;
    @BindView(R.id.bt_login)
    Button mBtLogin;
    @BindView(R.id.tv_one_label)
    TextView mTvOneLabel;
    @BindView(R.id.ll_one)
    LinearLayout mLlOne;
    @BindView(R.id.iv_wechat)
    ImageView mIvWechat;
    @BindView(R.id.iv_qq)
    ImageView mIvQq;
    private Tencent mTencent;
    private IUiListener loginListener;
    private static boolean isServerSideLogin = false;
    private UserInfo mInfo;

    @Override
    protected void setContentView() {

        setContentView(R.layout.activity_login);
    }

    @Override
    public void initView() {
        // Tencent类是SDK的主要实现类，开发者可通过Tencent类访问腾讯开放的OpenAPI。
        // 其中APP_ID是分配给第三方应用的appid，类型为String。
        mTencent = Tencent.createInstance("1105165474", this.getApplicationContext());
        // 1.4版本:此处需新增参数，传入应用程序的全局context，可通过activity的getApplicationContext方法获取

        loginListener = new BaseUiListener() {
            @Override
            protected void doComplete(JSONObject values) {
                L.v(values.toString());
                initOpenidAndToken(values);
                updateUserInfo();
            }
        };
    }


    @OnClick({R.id.iv_close, R.id.iv_delete, R.id.bt_login, R.id.iv_wechat, R.id.iv_qq})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                break;
            case R.id.iv_delete:
                break;
            case R.id.bt_login:

                break;
            case R.id.iv_wechat:
                break;
            case R.id.iv_qq:
                doLogin();
                break;
        }
    }

    private void doLogin() {

        if (!mTencent.isSessionValid()) {
            mTencent.login(this, "all", loginListener);
            isServerSideLogin = false;
            Log.d("SDKQQAgentPref", "FirstLaunch_SDK:" + SystemClock.elapsedRealtime());
        } else {
            if (isServerSideLogin) { // Server-Side 模式的登陆, 先退出，再进行SSO登陆
                mTencent.logout(this);
                mTencent.login(this, "all", loginListener);
                isServerSideLogin = false;
                Log.d("SDKQQAgentPref", "FirstLaunch_SDK:" + SystemClock.elapsedRealtime());
                return;
            }
            mTencent.logout(this);
            isServerSideLogin = false;
            updateUserInfo();
        }


    }


    private void initOpenidAndToken(JSONObject jsonObject) {
        try {
            String token = jsonObject.getString(Constants.PARAM_ACCESS_TOKEN);
            String expires = jsonObject.getString(Constants.PARAM_EXPIRES_IN);
            String openId = jsonObject.getString(Constants.PARAM_OPEN_ID);
            if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires)
                    && !TextUtils.isEmpty(openId)) {
                mTencent.setAccessToken(token, expires);
                mTencent.setOpenId(openId);
            }
        } catch (Exception e) {
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        L.v("-->onActivityResult " + requestCode + " resultCode=" + resultCode);
        if (requestCode == Constants.REQUEST_LOGIN ||
                requestCode == Constants.REQUEST_APPBAR) {
            Tencent.onActivityResultData(requestCode, resultCode, data, loginListener);
        }

        super.onActivityResult(requestCode, resultCode, data);

    }

    private class BaseUiListener implements IUiListener {
        @Override
        public void onComplete(Object response) {
            //V2.0版本，参数类型由JSONObject 改成了Object,具体类型参考api文档

            if (null == response) {
                UIUtils.showToast("返回为空,登录失败");
                return;
            }
            JSONObject jsonResponse = (JSONObject) response;
            if (null != jsonResponse && jsonResponse.length() == 0) {
                UIUtils.showToast("返回为空,登录失败");
                return;
            }
            UIUtils.showToast("登录成功");
            L.v("onComplete:" + response.toString());
            doComplete((JSONObject) response);
            finish();
        }

        protected void doComplete(JSONObject values) {

        }

        @Override
        public void onError(UiError e) {
            L.v("onError:", "code:" + e.errorCode + ", msg:"
                    + e.errorMessage + ", detail:" + e.errorDetail);
        }

        @Override
        public void onCancel() {
            L.v("onCancel", "");
        }
    }

    private void updateUserInfo() {
        if (mTencent != null && mTencent.isSessionValid()) {
            IUiListener listener = new IUiListener() {
                @Override
                public void onError(UiError e) {

                }

                @Override
                public void onComplete(final Object response) {
                    JSONObject responseJ = (JSONObject) response;
                    EventBus.getDefault().post(response);
                }

                @Override
                public void onCancel() {

                }
            };
            mInfo = new UserInfo(this, mTencent.getQQToken());
            mInfo.getUserInfo(listener);

        }
    }

}
