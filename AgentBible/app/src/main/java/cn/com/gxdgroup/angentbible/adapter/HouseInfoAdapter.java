package cn.com.gxdgroup.angentbible.adapter;

import android.content.Context;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.com.gxdgroup.angentbible.R;
import cn.com.gxdgroup.angentbible.adapter.common.CommonAdapter;
import cn.com.gxdgroup.angentbible.adapter.common.ViewHolder;
import cn.com.gxdgroup.angentbible.utils.UIUtils;

/**
 * Created by Ivy on 2016/10/18.
 *
 * @description:
 */

public class HouseInfoAdapter extends CommonAdapter<String> {

    public HouseInfoAdapter(Context context, List<String> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
    }

    @Override
    public void convert(ViewHolder holder, String item) {
        String url = "http://cdn1.dooioo.com/gimage10/M00/4F/BF/ChYCZ1crapaAItWeAAAphhDd-DE261.jpg";
        holder.setImageByUrl(R.id.iv_cover, url);
    }
}
