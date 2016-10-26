package cn.com.gxdgroup.angentbible.activities;

import android.os.Bundle;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.com.gxdgroup.angentbible.R;
import cn.com.gxdgroup.angentbible.base.BaseActivity;
import cn.com.gxdgroup.angentbible.holder.impl.village.VillageMagazineBasicHolder;
import cn.com.gxdgroup.angentbible.holder.impl.village.VillageMagazineDealHolder;
import cn.com.gxdgroup.angentbible.holder.impl.village.VillageMagazineQuoteHolder;
import cn.com.gxdgroup.angentbible.holder.impl.village.VillageMagazineVpHolder;
import cn.com.gxdgroup.angentbible.interfaces.SimpleAppTitleListener;
import cn.com.gxdgroup.angentbible.ui.AppTitleView;

/**
 * Created by Ivy on 2016/10/26.
 *
 * @description:
 */

public class VillageMagazineActivity extends BaseActivity {
    @BindView(R.id.titleView)
    AppTitleView mTitleView;
    @BindView(R.id.fr_imgs_basic)
    FrameLayout mFrImgsBasic;
    @BindView(R.id.fr_deal)
    FrameLayout mFrDeal;
    @BindView(R.id.fr_quote)
    FrameLayout mFrQuote;
    @BindView(R.id.fr_vp)
    FrameLayout mFrVp;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_village_magazine);
    }

    @Override
    public void initView() {

        mTitleView.showMode(AppTitleView.MODE.TITLE_R_IV, -1, this)
                .setRightIvRes(R.drawable.btn_share)
                .setTitleMsg("小区月报")
                .setListener(new SimpleAppTitleListener(this));

        mFrImgsBasic.addView(new VillageMagazineBasicHolder(this).getContentView());
        mFrDeal.addView(new VillageMagazineDealHolder(this).getContentView());
        mFrQuote.addView(new VillageMagazineQuoteHolder(this).getContentView());
        mFrVp.addView(new VillageMagazineVpHolder(this).getContentView());


    }


}
