package cn.com.gxdgroup.angentbible.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.com.gxdgroup.angentbible.R;
import cn.com.gxdgroup.angentbible.base.BaseActivity;
import cn.com.gxdgroup.angentbible.holder.impl.village.AroundEquipmentHolder;
import cn.com.gxdgroup.angentbible.holder.impl.village.EquipmentMapDetialsHolder;
import cn.com.gxdgroup.angentbible.ui.TitleView;
import cn.com.gxdgroup.angentbible.utils.UIUtils;

/**
 * Created by Ivy on 2016/10/20.
 *
 * @description: 周边配套界面
 */

public class EquipmentActivity extends BaseActivity {
    @BindView(R.id.titleView)
    TitleView mTitleView;
    @BindView(R.id.fr_map)
    FrameLayout mFrMap;

    private boolean isMap = true;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_equipment);
    }

    @Override
    protected void initView() {
        final AroundEquipmentHolder holder = new AroundEquipmentHolder(this);
        final EquipmentMapDetialsHolder detialsHolder = new EquipmentMapDetialsHolder(this);

        holder.showMapTitleBar(false);


        mFrMap.addView(holder.getContentView());
        mFrMap.addView(detialsHolder.getContentView());

        detialsHolder.getContentView().setVisibility(View.GONE);

        mTitleView.setLeftButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mTitleView.showRightImageView(false);
        mTitleView.setRightTextListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isMap) {
                    holder.getContentView().setVisibility(View.GONE);
                    detialsHolder.getContentView().setVisibility(View.VISIBLE);
                } else {
                    detialsHolder.getContentView().setVisibility(View.GONE);
                    holder.getContentView().setVisibility(View.VISIBLE);
                }
                isMap = !isMap;
            }
        });

    }

}