package cn.com.gxdgroup.angentbible.adapter.common;

import android.content.Context;
import android.view.View;

import java.util.List;

import cn.com.gxdgroup.angentbible.R;

/**
 * Created by Ivy on 2016/10/24.
 *
 * @description:
 */

public class DataRankAdapter extends CommonAdapter<String> {
    public DataRankAdapter(Context context, List<String> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void convert(final ViewHolder holder, String item) {
        holder.setText(R.id.tv_rank_number, item);
        holder.setOnClickListener(R.id.iv_arrow, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int visibility = holder.getView(R.id.chart).getVisibility();
                if (visibility == View.GONE) {
                    holder.getView(R.id.chart).setVisibility(View.VISIBLE);
                } else {
                    holder.getView(R.id.chart).setVisibility(View.GONE);
                }
            }
        });
    }
}
