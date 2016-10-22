package cn.com.gxdgroup.angentbible.holder.impl.selector;

import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import cn.com.gxdgroup.angentbible.R;
import cn.com.gxdgroup.angentbible.holder.BaseHolder;
import cn.com.gxdgroup.angentbible.utils.L;
import cn.com.gxdgroup.angentbible.utils.UIUtils;

/**
 * Created by Ivy on 2016/10/20.
 */

public class SelectionHolder extends BaseHolder {


    @BindView(R.id.tv_first)
    TextView mTvFirst;
    @BindView(R.id.iv_first_arrow)
    ImageView mIvFirstArrow;
    @BindView(R.id.ll_first)
    LinearLayout mLlFirst;
    @BindView(R.id.tv_second)
    TextView mTvSecond;
    @BindView(R.id.iv_second_arrow)
    ImageView mIvSecondArrow;
    @BindView(R.id.ll_second)
    LinearLayout mLlSecond;
    @BindView(R.id.third_weight_view)
    View mThirdWeightView;
    @BindView(R.id.tv_third)
    TextView mTvThird;
    @BindView(R.id.iv_third_arrow)
    ImageView mIvThirdArrow;
    @BindView(R.id.ll_third)
    LinearLayout mLlThird;
    @BindView(R.id.tv_last)
    TextView mTvLast;
    @BindView(R.id.iv_last_arrow)
    ImageView mIvLastArrow;
    @BindView(R.id.ll_last)
    LinearLayout mLlLast;
    @BindView(R.id.second_weight_view)
    View mSecondWeightView;
    @BindView(R.id.last_weight_view)
    View mLastWeightView;
    private int menuType;
    private ArrayList<String[]> namesList;

    public SelectionHolder(FragmentActivity activity) {
        super(activity);
    }

    public SelectionHolder(FragmentActivity activity, int menuType) {
        super(activity);
        this.menuType = menuType;
    }

    @Override
    public View setContentView() {
        return UIUtils.inflate(R.layout.holder_selection);
    }

    @Override
    public void setData() {

    }


    /**
     * 因为还没有根据传入的Menu设置显示布局，所以要复写父类的getContentView方法
     *
     * @return
     */
    @Override
    public View getContentView() {
        namesList = new ArrayList<>();
        namesList.add(new String[]{"区域", "价格", "来源", "更多"});// 二手房
        namesList.add(new String[]{"区域", "租金", "方式", "更多"});// 租房
        namesList.add(new String[]{"区域", "房型", "来源"});// 客源-购房
//        namesList.add(new String[]{"区域", "房型", "方式", "来源"});// 客源-租房(这个放到后面单独处理)
        namesList.add(new String[]{"区域", "价格", "房型", "面积"});// 最新成交

        setShowMode(namesList.get(menuType));

        return super.getContentView();
    }


    /**
     * 根据TAB的选择来显示下面的Selection
     *
     * @param index
     */
    public void setSelectionByTabIndex(int index) {
        L.v("mMenuType " + menuType + ",index " + index);
        if (menuType == 2) {
            if (index == 0)
                setShowMode(namesList.get(2));
            else if (index == 1)
                setShowMode(new String[]{"区域", "房型", "方式", "来源"});// 客源-租房(这个放到后面单独处理,在这里处理了)
        }
    }

    private void setShowMode(String[] names) {
        mTvFirst.setText(names[0]);
        mTvSecond.setText(names[1]);
        mTvThird.setText(names[2]);

        if (names.length == 3) {

            mLlThird.setVisibility(View.GONE);
            mTvLast.setText(names[2]);

            // 需要的操作 gone 掉weightview llselection 的 weight 设置成1
            mSecondWeightView.setVisibility(View.GONE);
            mThirdWeightView.setVisibility(View.GONE);
            mLastWeightView.setVisibility(View.GONE);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.FILL_PARENT);
            params.weight = 1.0f;
            mLlFirst.setLayoutParams(params);
            mLlSecond.setLayoutParams(params);
            mLlLast.setLayoutParams(params);

        } else if (names.length == 4) {
            mTvLast.setText(names[3]);
            mLlThird.setVisibility(View.VISIBLE);
            mThirdWeightView.setVisibility(View.VISIBLE);


            // 需要的操作 visible 掉weightview llselection 的 weight 设置成0
            mSecondWeightView.setVisibility(View.VISIBLE);
            mThirdWeightView.setVisibility(View.VISIBLE);
            mLastWeightView.setVisibility(View.VISIBLE);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.FILL_PARENT);
            params.weight = 0.0f;
            mLlFirst.setLayoutParams(params);
            mLlSecond.setLayoutParams(params);
            mLlLast.setLayoutParams(params);

        }
    }

    /**
     * 点击事件
     *
     * @param view
     */
    @OnClick({R.id.ll_first, R.id.ll_second, R.id.ll_third, R.id.ll_last})
    public void onClick(View view) {
        int blueColor = UIUtils.getColor(R.color.common_blue);
        int normalColor = UIUtils.getColor(R.color.text_grey);
        Drawable up = UIUtils.getDrawable(R.drawable.btn_up_back);
        Drawable down = UIUtils.getDrawable(R.drawable.btn_pulldown);
        switch (view.getId()) {
            case R.id.ll_first:
                mTvFirst.setTextColor(blueColor);
                mIvFirstArrow.setImageDrawable(up);
                break;
            case R.id.ll_second:
                mTvSecond.setTextColor(blueColor);
                mIvSecondArrow.setImageDrawable(up);
                break;
            case R.id.ll_third:
                mTvThird.setTextColor(blueColor);
                mIvThirdArrow.setImageDrawable(up);
                break;
            case R.id.ll_last:
                mTvLast.setTextColor(blueColor);
                mIvLastArrow.setImageDrawable(up);
                break;
        }
    }

}
