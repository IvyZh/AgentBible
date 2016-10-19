package cn.com.gxdgroup.angentbible.holder.impl;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import cn.com.gxdgroup.angentbible.R;
import cn.com.gxdgroup.angentbible.domain.MoviesBean;
import cn.com.gxdgroup.angentbible.holder.BaseHolder;
import cn.com.gxdgroup.angentbible.ui.DividerItemDecoration;
import cn.com.gxdgroup.angentbible.utils.L;
import cn.com.gxdgroup.angentbible.utils.UIUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static cn.com.gxdgroup.angentbible.R.id.recyclerView;

/**
 * Created by Ivy on 2016/10/14.
 *
 * @description:
 */

public class TheatersHolder extends BaseHolder {
    @BindView(R.id.tv_more)
    TextView mTvMore;
    @BindView(recyclerView)
    RecyclerView mRecyclerView;

    public TheatersHolder(FragmentActivity activity) {
        super(activity);
    }

    @Override
    public View setContentView() {
        return UIUtils.inflate(R.layout.layout_theater_holder);
    }

    @Override
    public void setData() {

    }

    public void setData(List<MoviesBean.SubjectsBean> mDatas) {

        LinearLayoutManager layoutManager = new LinearLayoutManager(UIUtils.getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        DividerItemDecoration decoration = new DividerItemDecoration(UIUtils.getContext(), DividerItemDecoration.HORIZONTAL_LIST);
        mRecyclerView.addItemDecoration(decoration);

        mRecyclerView.setLayoutManager(layoutManager);


        MovieAdapter adapter = new MovieAdapter(mDatas);
        mRecyclerView.setAdapter(adapter);

    }

    @OnClick(R.id.tv_more)
    public void onClick() {
    }


    class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {
        private List<MoviesBean.SubjectsBean> mDatas;

        MovieAdapter(List<MoviesBean.SubjectsBean> mDatas) {
            this.mDatas = mDatas;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    UIUtils.getContext()).inflate(R.layout.item_movie, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            MoviesBean.SubjectsBean item = mDatas.get(position);
            holder.tvTitle.setText(item.getTitle());

            String medium = item.getImages().getMedium();
            Glide.with(UIUtils.getContext()).load(medium).into(holder.ivCover);

            MoviesBean.SubjectsBean.RatingBean rating = item.getRating();
            int max = rating.getMax();
            double average = rating.getAverage();
            int rate = (int) (average / 2 + 0.5);
            if (rate > 5) rate = 5;
            holder.rbRating.setMax(max);
            holder.rbRating.setProgress((int) average);

            holder.tvRating.setText(average + "");
        }


        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView tvRating;
            TextView tvTitle;
            ImageView ivCover;
            RatingBar rbRating;

            public MyViewHolder(View view) {
                super(view);
                tvRating = (TextView) view.findViewById(R.id.tv_rating);
                tvTitle = (TextView) view.findViewById(R.id.tv_title);
                ivCover = (ImageView) view.findViewById(R.id.iv_cover);
                rbRating = (RatingBar) view.findViewById(R.id.rb_rating);
            }
        }
    }

}
