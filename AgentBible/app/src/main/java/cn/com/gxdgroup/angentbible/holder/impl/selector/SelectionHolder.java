package cn.com.gxdgroup.angentbible.holder.impl.selector;

import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.com.gxdgroup.angentbible.R;
import cn.com.gxdgroup.angentbible.holder.BaseHolder;
import cn.com.gxdgroup.angentbible.ui.SelectPopupWindow;
import cn.com.gxdgroup.angentbible.utils.L;
import cn.com.gxdgroup.angentbible.utils.UIUtils;

/**
 * Created by Ivy on 2016/10/20.
 */

public class SelectionHolder extends BaseHolder {

    private SelectPopupWindow selectPopWindow;

    private String[] selectTabText;//选择条件的文本
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
    private ArrayList<String[]> selectTabNames;
    List<String> parentData = new ArrayList<>();
    List<List<String>> childData = new ArrayList<List<String>>();
    private int mSelectIndex;//当前选择的页签Index

    private String selectedSelection1 = "";
    private String selectedSelection2 = "";
    private String selectedSelection3 = "";
    private String selectedSelection4 = "";

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

        if (menuType != -1) {
            selectTabNames = new ArrayList<>();
            selectTabNames.add(new String[]{"区域", "价格", "来源", "更多"});// 二手房
            selectTabNames.add(new String[]{"区域", "租金", "方式", "更多"});// 租房
            selectTabNames.add(new String[]{"区域", "房型", "来源"});// 客源-购房
//        selectTabNames.add(new String[]{"区域", "房型", "方式", "来源"});// 客源-租房(这个放到后面单独处理)
            selectTabNames.add(new String[]{"区域", "价格", "房型", "面积"});// 最新成交
            setShowMode(selectTabNames.get(menuType)); //TODO
        }


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
                setShowMode(selectTabNames.get(2));
            else if (index == 1)
                setShowMode(new String[]{"区域", "房型", "方式", "来源"});// 客源-租房(这个放到后面单独处理,在这里处理了)
        }
    }

    private void setShowMode(String[] names) {
        selectTabText = names;
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
        Drawable up = UIUtils.getDrawable(R.drawable.btn_up_back);
        String selectedSel = "";

        switch (view.getId()) {
            case R.id.ll_first:
                mTvFirst.setTextColor(blueColor);
                mIvFirstArrow.setImageDrawable(up);
                prepareData(0);
                selectedSel = selectedSelection1;
                break;
            case R.id.ll_second:
                mTvSecond.setTextColor(blueColor);
                mIvSecondArrow.setImageDrawable(up);
                prepareData(1);
                selectedSel = selectedSelection2;
                break;
            case R.id.ll_third:
                mTvThird.setTextColor(blueColor);
                mIvThirdArrow.setImageDrawable(up);
                prepareData(2);
                selectedSel = selectedSelection3;
                break;
            case R.id.ll_last:
                mTvLast.setTextColor(blueColor);
                mIvLastArrow.setImageDrawable(up);
                prepareData(3);
                selectedSel = selectedSelection4;
                break;
        }

        if (parentData != null) {
            selectPopWindow = new SelectPopupWindow(parentData, childData, mActivity, selectedSel,new SelectPopupWindow.SelectCategory() {

                @Override
                public void selectCategory(int parPos, String tvParent, int childPos, String tvChild) {
                    L.v("---" + parPos + "," + tvParent + "," + childPos + "," + tvChild);

                    if (mSelectIndex == 0) {
                        selectedSelection1 = mSelectIndex + "-" + parPos + "-" + childPos;
                        mTvFirst.setText(tvChild);
                    } else if (mSelectIndex == 1) {
                        selectedSelection2 = mSelectIndex + "-" + parPos + "-" + childPos;
                        if (childPos == -1) {
                            mTvSecond.setText(tvParent);
                        } else {
                            mTvSecond.setText(tvChild);
                        }

                    } else if (mSelectIndex == 2) {
                        selectedSelection3 = mSelectIndex + "-" + parPos + "-" + childPos;
                        if (childPos == -1) {
                            mTvThird.setText(tvParent);
                        } else {
                            mTvThird.setText(tvChild);
                        }
                    } else if (mSelectIndex == 3) {
                        selectedSelection4 = mSelectIndex + "-" + parPos + "-" + childPos;
                        if (childPos == -1) {
                            mTvLast.setText(tvParent);
                        } else {
                            mTvLast.setText(tvChild);
                        }

                    }

                }
            });
            selectPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    // 需要将剪头反转
                    int normalColor = UIUtils.getColor(R.color.text_grey);
                    Drawable down = UIUtils.getDrawable(R.drawable.btn_pulldown);
                    mTvFirst.setTextColor(normalColor);
                    mIvFirstArrow.setImageDrawable(down);
                    mTvSecond.setTextColor(normalColor);
                    mIvSecondArrow.setImageDrawable(down);
                    mTvThird.setTextColor(normalColor);
                    mIvThirdArrow.setImageDrawable(down);
                    mTvLast.setTextColor(normalColor);
                    mIvLastArrow.setImageDrawable(down);
                }
            });

            selectPopWindow.showAsDropDown(mLlFirst, 0, 1);
//            EventBus.getDefault().post(selectedSel);

        }
    }

    private void prepareData(int selectIndex) {// 根据点击的筛选条件，准备筛选数据
        mSelectIndex = selectIndex;
        String[] parentSelections = {"区域", "距离"};
        String[] childSelections1 = {"全部", "浦东", "闵行", "徐汇", "长宁", "普陀", "静安", "卢湾", "黄浦", "闸北", "虹口", "杨浦", "宝山", "嘉定", "青浦", "松江", "金山", "奉贤", "南汇", "崇明"};
        String[] childSelections2 = {"全部", "500米以内", "1000米以内", "2000米以内", "3000米以内"};

        L.v("menuType--" + menuType);
        if (menuType == 0) {
            if (selectIndex == 0) {
                parentData = Arrays.asList(parentSelections);
                childData.add(Arrays.asList(childSelections1));
                childData.add(Arrays.asList(childSelections2));
            } else if (selectIndex == 1) {
                parentData = Arrays.asList(new String[]{"全部", "200万以下", "200-250万", "250-300万", "300-400万", "400-500万", "500-800万", "800-1000万", "1000万以上"});
                childData.clear();
            } else if (selectIndex == 2) {
                parentData = Arrays.asList(new String[]{"全部", "个人", "经纪人"});
                childData.clear();
            } else if (selectIndex == 3) {//更多

            }
        } else if (menuType == 1) {
            if (selectIndex == 0) {
                parentData = Arrays.asList(parentSelections);
                childData.add(Arrays.asList(childSelections1));
                childData.add(Arrays.asList(childSelections2));
            } else if (selectIndex == 1) {
                parentData = Arrays.asList(new String[]{"全部", "2000元以下", "2000-3000元", "3000-4000元", "4000-5000元", "5000-6000元", "6000元以上"});
                childData.clear();
            } else if (selectIndex == 2) {
                parentData = Arrays.asList(new String[]{"全部", "整租", "合租"});
                childData.clear();
            } else if (selectIndex == 3) {//更多

            }
        } else if (menuType == 2 || menuType == 3) {
            if (selectIndex == 0) {//区域
                parentData = Arrays.asList(parentSelections);
                childData.add(Arrays.asList(childSelections1));
                childData.add(Arrays.asList(childSelections2));
            } else if (selectIndex == 1) {//房型
                parentData = Arrays.asList(new String[]{"1室", "2室", "3室", "4室", "5室", "5室以上"});
                childData.clear();
            } else if (selectIndex == 2) {// null or 方式
                parentData = Arrays.asList(new String[]{"全部", "整租", "合租"});
                childData.clear();
            } else if (selectIndex == 3) {//来源
                parentData = Arrays.asList(new String[]{"全部", "个人", "经纪人"});
                childData.clear();
            }
        }


    }

}
