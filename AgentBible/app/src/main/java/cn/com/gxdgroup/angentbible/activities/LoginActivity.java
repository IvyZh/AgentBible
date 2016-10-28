package cn.com.gxdgroup.angentbible.activities;

import android.content.Intent;
import android.opengl.ETC1;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import cn.com.gxdgroup.angentbible.R;
import cn.com.gxdgroup.angentbible.base.BaseActivity;
import cn.com.gxdgroup.angentbible.domain.MessageEvent;
import cn.com.gxdgroup.angentbible.listener.GetQQUserInfoUiListener;
import cn.com.gxdgroup.angentbible.listener.MyTextWatcher;
import cn.com.gxdgroup.angentbible.listener.QQLoginUiListener;
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
    private IUiListener qqLoginListener;
    private static boolean isServerSideLogin = false;
    private UserInfo mInfo;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_login);
    }

    @Override
    public void initView() {
        mTencent = Tencent.createInstance("1105165474", this.getApplicationContext());

        qqLoginListener = new QQLoginUiListener() {
            @Override
            protected void doComplete(JSONObject values) {
                initOpenidAndToken(values);
                updateUserInfo();
            }
        };

        mEtUsername.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void onTextChanged(CharSequence sequence, int i, int i1, int i2) {
                mIvDelete.setVisibility(sequence.length() > 0 ? View.VISIBLE : View.GONE);
            }
        });
    }

    @Override
    public void initActionBar(int colorId) {
        super.initActionBar(R.color.login_title);
    }

    @OnClick({R.id.iv_close, R.id.iv_delete, R.id.bt_login, R.id.iv_wechat, R.id.iv_qq, R.id.tv_user_handbook})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                finish();
                break;
            case R.id.iv_delete:
                mEtUsername.setText("");
                break;
            case R.id.bt_login:

                break;
            case R.id.iv_wechat:
                break;
            case R.id.iv_qq:
                doQQLogin();
                break;
            case R.id.tv_user_handbook:
                UIUtils.showToast("进入用户服务协议界面");
                break;
        }
    }


    //##############################################################################
    //################                                              ################
    //################                                              ################
    //################                 以下是QQ登陆的逻辑             ################
    //################                                              ################
    //################                                              ################
    //##############################################################################

    private void doQQLogin() {
        if (!mTencent.isSessionValid()) {
            mTencent.login(this, "all", qqLoginListener);
            isServerSideLogin = false;
        } else {
            if (isServerSideLogin) { // Server-Side 模式的登陆, 先退出，再进行SSO登陆
                mTencent.logout(this);
                mTencent.login(this, "all", qqLoginListener);
                isServerSideLogin = false;
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
            if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires) && !TextUtils.isEmpty(openId)) {
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
            Tencent.onActivityResultData(requestCode, resultCode, data, qqLoginListener);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    private void updateUserInfo() {
        if (mTencent != null && mTencent.isSessionValid()) {
            mInfo = new UserInfo(this, mTencent.getQQToken());
            mInfo.getUserInfo(new GetQQUserInfoUiListener() {
                @Override
                public void onComplete(Object response) {
                    MessageEvent event = new MessageEvent(0, response);
                    EventBus.getDefault().post(event);
                }
            });

            UIUtils.showToast("登陆成功");
            finish();
        }
    }

    //##############################################################################
    //################                                              ################
    //################                                              ################
    //################                 以上是QQ登陆的逻辑             ################
    //################                                              ################
    //################                                              ################
    //##############################################################################

}
