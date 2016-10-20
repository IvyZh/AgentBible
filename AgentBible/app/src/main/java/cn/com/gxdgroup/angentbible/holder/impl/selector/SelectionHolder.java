package cn.com.gxdgroup.angentbible.holder.impl.selector;

import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.text.TextUtilsCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import cn.com.gxdgroup.angentbible.R;
import cn.com.gxdgroup.angentbible.holder.BaseHolder;
import cn.com.gxdgroup.angentbible.utils.UIUtils;

/**
 * Created by Ivy on 2016/10/20.
 */

public class SelectionHolder extends BaseHolder {
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.iv_location_arrow)
    ImageView ivLocationArrow;
    @BindView(R.id.ll_first)
    LinearLayout llFirst;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.iv_price_arrow)
    ImageView ivPriceArrow;
    @BindView(R.id.ll_second)
    LinearLayout llSecond;
    @BindView(R.id.tv_source)
    TextView tvSource;
    @BindView(R.id.iv_source_arrow)
    ImageView ivSourceArrow;
    @BindView(R.id.ll_third)
    LinearLayout llThird;
    @BindView(R.id.tv_more)
    TextView tvMore;
    @BindView(R.id.iv_more_arrow)
    ImageView ivMoreArrow;
    @BindView(R.id.ll_last)
    LinearLayout llLast;

    public SelectionHolder(FragmentActivity activity) {
        super(activity);
    }

    @Override
    public View setContentView() {
        return UIUtils.inflate(R.layout.holder_selection);
    }

    @Override
    public void setData() {

    }

    @OnClick({R.id.ll_first, R.id.ll_second, R.id.ll_third, R.id.ll_last})
    public void onClick(View view) {
        int blueColor = UIUtils.getColor(R.color.common_blue);
        int normalColor = UIUtils.getColor(R.color.text_grey);
        Drawable up = UIUtils.getDrawable(R.drawable.btn_up_back);
        Drawable down = UIUtils.getDrawable(R.drawable.pull_down);
        switch (view.getId()) {
            case R.id.ll_first:
                tvLocation.setTextColor(blueColor);
                ivLocationArrow.setImageDrawable(up);
                break;
            case R.id.ll_second:
                tvPrice.setTextColor(blueColor);
                ivPriceArrow.setImageDrawable(up);
                break;
            case R.id.ll_third:
                tvSource.setTextColor(blueColor);
                ivSourceArrow.setImageDrawable(up);
                break;
            case R.id.ll_last:
                tvMore.setTextColor(blueColor);
                ivMoreArrow.setImageDrawable(up);
                break;
        }
    }

    public void setSelectText(String[] names) {
        if (names.length != 4) {
            throw new IllegalArgumentException("筛选条件必须为4个");
        }
        tvLocation.setText(names[0]);
        tvPrice.setText(names[1]);
        tvSource.setText(names[2]);
        tvMore.setText(names[3]);
        if (TextUtils.isEmpty(names[2])) {
            llThird.setVisibility(View.GONE);
        }
    }
}
