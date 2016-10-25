package cn.com.gxdgroup.angentbible.ui;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;

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

    private List<String> mParentData;
    private List<List<String>> mChildData;
    private ListView lvParentCategory = null;
    private ListView lvChildrenCategory = null;
    private ParentCategoryAdapter parentCategoryAdapter = null;
    private ChildrenCategoryAdapter childrenCategoryAdapter = null;
    private int pPos, cPos;

    public SelectPopupWindow(List parentData, List<List<String>> childData, int parentSelectPos, int childSelectPos, Activity activity, SelectCategory selectCategory) {
        this.selectCategory = selectCategory;
        this.mParentData = parentData;
        this.mChildData = childData;
        this.pPos = parentSelectPos;
        this.cPos = childSelectPos;


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
        parentCategoryAdapter = new ParentCategoryAdapter(activity, parentData, R.layout.item_selection);
        lvParentCategory.setAdapter(parentCategoryAdapter);
        //设置默认选中的状态，父类和子类都需要
        L.v("select pos:" + parentSelectPos + "," + childSelectPos);

        parentCategoryAdapter.setSelectedPosition(parentSelectPos);
        parentCategoryAdapter.notifyDataSetInvalidated();


        //子类别适配器
        lvChildrenCategory = (ListView) contentView.findViewById(R.id.lv_children_category);

        if (mChildData != null && mChildData.size() > 0) {
            childrenCategoryAdapter = new ChildrenCategoryAdapter(activity, mChildData.get(parentSelectPos), R.layout.item_selection);
            lvChildrenCategory.setAdapter(childrenCategoryAdapter);
            childrenCategoryAdapter.setSelectedPosition(childSelectPos);
            childrenCategoryAdapter.notifyDataSetChanged();
        } else {
            lvChildrenCategory.setVisibility(View.GONE);
        }

        lvParentCategory.setOnItemClickListener(parentItemClickListener);
        lvChildrenCategory.setOnItemClickListener(childrenItemClickListener);


    }

    /**
     * 子类别点击事件
     */
    private AdapterView.OnItemClickListener childrenItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (selectCategory != null) {
                selectCategory.selectCategory(parentCategoryAdapter.getPos(), mParentData.get(parentCategoryAdapter.getPos()), position, mChildData.get(parentCategoryAdapter.getPos()).get(position));
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

            if (mChildData != null && mChildData.size() > 0) {

                parentCategoryAdapter.setSelectedPosition(position);
                parentCategoryAdapter.notifyDataSetChanged();

                if (position == pPos) {
                    childrenCategoryAdapter.setSelectedPosition(cPos);
                } else {
                    childrenCategoryAdapter.setSelectedPosition(0);
                }
                childrenCategoryAdapter.setDatas(mChildData.get(position));
                childrenCategoryAdapter.notifyDataSetChanged();

            } else {
                if (selectCategory != null) {
                    selectCategory.selectCategory(position, mParentData.get(position), -1, "");
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
         * @param pPos 父类选中的位置
         * @param tvP  父类选中的文字
         * @param cPos 子类选中的位置 -1
         * @param tvC  子类选中的文字 ""
         */
        public void selectCategory(int pPos, String tvP, int cPos, String tvC);
    }

}
