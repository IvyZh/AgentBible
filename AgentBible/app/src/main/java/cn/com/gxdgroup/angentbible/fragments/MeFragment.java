package cn.com.gxdgroup.angentbible.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXImageObject;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.open.utils.Util;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import cn.com.gxdgroup.angentbible.R;
import cn.com.gxdgroup.angentbible.activities.CollectionActivity;
import cn.com.gxdgroup.angentbible.activities.LoginActivity;
import cn.com.gxdgroup.angentbible.base.BaseFragment;
import cn.com.gxdgroup.angentbible.constant.MenuType;
import cn.com.gxdgroup.angentbible.constant.MyConstants;
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
                shareToWechat();
                break;
            case R.id.iv_portrait:
                startActivity(new Intent(mActivity, LoginActivity.class));
                break;
        }
    }

    /**
     * 分享到微信
     */
    private void shareToWechat() {
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        IWXAPI api = WXAPIFactory.createWXAPI(mActivity, MyConstants.APP_ID, false);
        api.registerApp(MyConstants.APP_ID);

        Bitmap bitmap = BitmapFactory.decodeResource(UIUtils.getResources(), R.drawable.launcher_logo);
//        WXImageObject imgObj = new WXImageObject(bitmap);

        WXWebpageObject webPage = new WXWebpageObject();
        webPage.webpageUrl = "http://www.baidu.com";

        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = webPage;
        msg.description = "这是网页描述";
        msg.title = "网页title";


        // 设置缩略图
        Bitmap thumbBmp = Bitmap.createScaledBitmap(bitmap, 60, 60, true);
        bitmap.recycle();
        msg.thumbData = bmpToByteArray(thumbBmp, true);
        SendMessageToWX.Req req = new SendMessageToWX.Req();

        req.transaction = buildTransaction("img");

        req.message = msg;

//        req.scene = SendMessageToWX.Req.WXSceneSession;//分享到好友
        req.scene = SendMessageToWX.Req.WXSceneTimeline;//分享到朋友圈

        api.sendReq(req);
    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }


    private byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {

        ByteArrayOutputStream output = new ByteArrayOutputStream();

        bmp.compress(Bitmap.CompressFormat.JPEG, 100, output);

        if (needRecycle) {

            bmp.recycle();

        }

        byte[] result = output.toByteArray();

        try {

            output.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return result;

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
    public void setAvatar(Object info, int type) {
        JSONObject json = (JSONObject) info;
        try {
            String nickname = "", headimgurl = "";
            if (type == 0) {
                headimgurl = json.getString("figureurl_qq_2");
                nickname = json.getString("nickname");
            } else if (type == 1) {
                headimgurl = json.getString("headimgurl");
                nickname = json.getString("nickname");
            }

            Glide.with(mActivity).load(headimgurl).into(mIvPortrait);
            mTvNickname.setText(nickname);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}
