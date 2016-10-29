package cn.com.gxdgroup.angentbible.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import cn.com.gxdgroup.angentbible.constant.MyConstants;
import cn.com.gxdgroup.angentbible.utils.AppManager;
import cn.com.gxdgroup.angentbible.utils.L;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    private Bundle bundle;
    //这个实体类是我自定义的实体类，用来保存第三方的数据的实体类
    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(WXEntryActivity.this);
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this, MyConstants.APP_ID, false);
        api.registerApp(MyConstants.APP_ID);

        api.handleIntent(getIntent(), WXEntryActivity.this);  //必须调用此句话
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        api.handleIntent(intent, WXEntryActivity.this);//必须调用此句话
    }

    @Override
    public void onReq(BaseReq req) {
        System.out.println();
    }


    @Override
    public void onResp(BaseResp arg0) {


        bundle = getIntent().getExtras();
        SendAuth.Resp resp = new SendAuth.Resp(bundle);
        //获取到code之后，需要调用接口获取到access_token

        L.v("--resp code--" + resp.code);
        if (resp.errCode == BaseResp.ErrCode.ERR_OK) {
            getToken(resp.code);
        } else {
            WXEntryActivity.this.finish();
        }

    }

    /**
     * Title: onResp
     * <p>
     * API：https://open.weixin.qq.com/ cgi- bin/showdocument ?action=dir_list&t=resource/res_list&verify=1&id=open1419317853 &lang=zh_CN
     * Description:在此处得到Code之后调用https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
     * 获取到token和openID。之后再调用https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID 获取用户个人信息
     */


    //这个方法会取得accesstoken  和openID
    private void getToken(String code) {
        L.v("--getToken--");
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + MyConstants.APP_ID + "&secret=SECRET&code="+code+"&grant_type=authorization_code";


    }

    //获取到token和openID之后，调用此接口得到身份信息
    private void getUserInfo(String token, String openID) {
        L.v("--getUserInfo--");
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + MyConstants.APP_ID + "&secret=SECRET&code=CODE&grant_type=authorization_code";



    }
}
