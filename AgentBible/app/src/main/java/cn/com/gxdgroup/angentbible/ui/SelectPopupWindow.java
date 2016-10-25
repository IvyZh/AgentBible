package cn.com.gxdgroup.angentbible.ui;

import android.app.Activity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import cn.com.gxdgroup.angentbible.R;
import cn.com.gxdgroup.angentbible.adapter.ChildrenCategoryAdapter;
import cn.com.gxdgroup.angentbible.adapter.ParentCategoryAdapter;
import cn.com.gxdgroup.angentbible.utils.L;
import cn.com.gxdgroup.angentbible.utils.UIUtils;

/**
 * Created by Ivy on 2016/10/24.
 *
 * @description: 自定义的PopupWindow, 在构造方法中设置内容, 设置背景等.给要显示的两个ListView设置适配器, 添加ListView点击事件, 点击子类别的时候回调选中的两个下标, 关闭PopupWindow。
 */

public class SelectPopupWindow extends PopupWindow {
    private SelectCategory selectCategory;

    private List<String> parentStrings;
    private List<List<String>> childrenStrings;
    private String[] split;
    private ListView lvParentCategory = null;
    private ListView lvChildrenCategory = null;
    private ParentCategoryAdapter parentCategoryAdapter = null;
    private ChildrenCategoryAdapter childrenCategoryAdapter = null;
    private String mSelectedSel;

    /**
     * @param parentStrings   字类别数据
     * @param childrenStrings 字类别二位数组
     * @param activity
     * @param selectCategory  回调接口注入
     */
    public SelectPopupWindow(List parentStrings, List<List<String>> childrenStrings, Activity activity, String selectedSel, SelectCategory selectCategory) {
        this.selectCategory = selectCategory;
        this.parentStrings = parentStrings;
        this.childrenStrings = childrenStrings;
        this.mSelectedSel = selectedSel;

        View contentView = LayoutInflater.from(activity).inflate(R.layout.layout_quyu_choose_view, null);
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm); // 获取手机屏幕的大小

        this.setContentView(contentView);
        this.setWidth(dm.widthPixels);
        this.setHeight(dm.heightPixels * 1);
//        this.setHeight(dm.heightPixels * 7 / 10);

		/* 设置背景显示 */
//        setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.));
        setBackgroundDrawable(UIUtils.getDrawable(R.color.common_bg));
        /* 设置触摸外面时消失 */
        setOutsideTouchable(true);
        setTouchable(true);
        setFocusable(true); /*设置点击menu以外其他地方以及返回键退出 */

        /**
         * 1.解决再次点击MENU键无反应问题
         */
        contentView.setFocusableInTouchMode(true);

        //父类别适配器
        lvParentCategory = (ListView) contentView.findViewById(R.id.lv_parent_category);
        parentCategoryAdapter = new ParentCategoryAdapter(activity, parentStrings, R.layout.item_selection);
        lvParentCategory.setAdapter(parentCategoryAdapter);

        //子类别适配器
        lvChildrenCategory = (ListView) contentView.findViewById(R.id.lv_children_category);

        if (childrenStrings != null && childrenStrings.size() > 0) {
            childrenCategoryAdapter = new ChildrenCategoryAdapter(activity, childrenStrings.get(0), R.layout.item_selection);
            lvChildrenCategory.setAdapter(childrenCategoryAdapter);
        } else {
            lvChildrenCategory.setVisibility(View.GONE);
        }


        lvParentCategory.setOnItemClickListener(parentItemClickListener);
        lvChildrenCategory.setOnItemClickListener(childrenItemClickListener);


        if (!TextUtils.isEmpty(mSelectedSel)) {
            split = mSelectedSel.split("_");
            for (String s : split) {
                L.v("--split--" + s);
            }
            parentCategoryAdapter.setSelectedPosition(Integer.valueOf(split[1]));
            parentCategoryAdapter.notifyDataSetChanged();

            if (split.length == 3) {
                childrenCategoryAdapter.setSelectedPosition(Integer.valueOf(split[2]));
                childrenCategoryAdapter.setDatas(childrenStrings.get(Integer.valueOf(split[1])));
                childrenCategoryAdapter.notifyDataSetChanged();
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void helloEventBus(String message) {
        L.v("--helloEventBus--" + message);
    }//收不到

    /**
     * 子类别点击事件
     */
    private AdapterView.OnItemClickListener childrenItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (selectCategory != null) {
                selectCategory.selectCategory(parentCategoryAdapter.getPos(), parentStrings.get(parentCategoryAdapter.getPos()), position, childrenStrings.get(parentCategoryAdapter.getPos()).get(position));
            }
            dismiss();
        }
    };

    /**
     * 父类别点击事件
     */
    private AdapterView.OnItemClickListener parentItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            L.v("--position--" + position);

            if (childrenStrings != null && childrenStrings.size() > 0) {
                childrenCategoryAdapter.setDatas(childrenStrings.get(position));
                childrenCategoryAdapter.notifyDataSetChanged();

                parentCategoryAdapter.setSelectedPosition(position);
                parentCategoryAdapter.notifyDataSetChanged();

                if (split != null) {
                    if (position == Integer.valueOf(split[1])) {
                        childrenCategoryAdapter.setSelectedPosition(Integer.valueOf(split[2]));
                    } else {
                        childrenCategoryAdapter.setSelectedPosition(-1);
                    }

                }

            } else {
                if (selectCategory != null) {
                    selectCategory.selectCategory(position, parentStrings.get(position), -1, null);
                }
                dismiss();
            }

        }
    };

    /**
     * 选择成功回调
     *
     * @author apple
     */
    public interface SelectCategory {
        /**
         * 把选中的下标通过方法回调回来
         *
         * @param parentSelectposition   父类别选中下标
         * @param childrenSelectposition 子类别选中下标
         */
        public void selectCategory(int parentSelectposition, String tvParent, int childrenSelectposition, String tvChild);
    }

}
