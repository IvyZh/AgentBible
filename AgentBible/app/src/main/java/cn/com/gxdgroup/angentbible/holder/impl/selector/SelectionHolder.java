package cn.com.gxdgroup.angentbible.holder.impl.selector;

import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.text.TextUtilsCompat;
import android.text.TextUtils;
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
    /**
     * 选择条件的初始值，用于对比是否发生了改变
     */
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
    private String tab1Selected, tab2Selected;

    private TextView[] tvArrs;

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
    public void initView() {
        tvArrs = new TextView[4];
        tvArrs[0] = mTvFirst;
        tvArrs[1] = mTvSecond;
        tvArrs[2] = mTvThird;
        tvArrs[3] = mTvLast;

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
            // 在切换页签之前要保留之前的内容 124

            if (index == 1) {// 保留租房的，区域、房型、来源三个值
                tab1Selected = selectedSelection1 + "##" + selectedSelection2 + "##" + selectedSelection4;
            } else if (index == 0) {// 保留 区域、房型、方式、来源四个值
                tab2Selected = selectedSelection1 + "##" + selectedSelection2 + "##" + selectedSelection3 + "##" + selectedSelection4;
            }


            if (index == 0) {
                if (TextUtils.isEmpty(selectedSelection1) && TextUtils.isEmpty(selectedSelection2) && TextUtils.isEmpty(selectedSelection4)) {
                    setShowMode(selectTabNames.get(2));//TODO
                } else {
                    setShowMode(selectTabNames.get(2));//TODO
                    if (tab1Selected == null) return;
                    String[] split = tab1Selected.split("##");//[   ""-##-1-1--1+##-"]
                    for (int i = 0; i < split.length; i++) {
                        L.v("----split--:" + split[i]);
                        if (!TextUtils.isEmpty(split[i])) {

                            if (i == 0) {
                                selectedSelection1 = split[0];
                            } else if (i == 1) {
                                selectedSelection2 = split[1];
                            } else if (i == 2) {
                                selectedSelection4 = split[2];
                            }


                            // i: 表示要给第几个TAB赋值
                            prepareData(i);//TODO
                            String[] split1 = split[i].split("_");

                            for (String s : split1) {
                                L.v("xxx--:" + s);
                            }

                            if (TextUtils.isEmpty(split1[2]) || Integer.valueOf(split1[2]) == -1) {//只有父类
                                Integer indexParent = Integer.valueOf(split1[1]);
                                tvArrs[i].setText(parentData.get(indexParent));
                            } else {
                                Integer indexParent = Integer.valueOf(split1[1]);
                                Integer indexChild = Integer.valueOf(split1[2]);
                                tvArrs[i].setText(childData.get(indexParent).get(indexChild));
                            }


                        }
                    }

                }
            } else if (index == 1) {
                if (TextUtils.isEmpty(selectedSelection1) && TextUtils.isEmpty(selectedSelection2) && TextUtils.isEmpty(selectedSelection3) && TextUtils.isEmpty(selectedSelection4)) {
                    setShowMode(new String[]{"区域", "房型", "方式", "来源"});// 客源-租房(这个放到后面单独处理,在这里处理了)
                } else {
                    setShowMode(new String[]{"区域", "房型", "方式", "来源"});// 客源-租房(这个放到后面单独处理,在这里处理了)

                    if (tab2Selected == null) return;


                    String[] split = tab2Selected.split("##");
                    for (int i = 0; i < split.length; i++) {
                        L.v("----split--:" + split[i]);
                        if (!TextUtils.isEmpty(split[i])) {

                            if (i == 0) {
                                selectedSelection1 = split[0];
                            } else if (i == 1) {
                                selectedSelection2 = split[1];
                            } else if (i == 2) {
                                selectedSelection3 = split[2];
                            } else if (i == 3) {
                                selectedSelection4 = split[3];
                            }


                            // i: 表示要给第几个TAB赋值
                            prepareData(i);//TODO
                            String[] split1 = split[i].split("_");

                            if (Integer.valueOf(split1[2]) == -1) {//只有父类
                                Integer indexParent = Integer.valueOf(split1[1]);
                                tvArrs[i].setText(parentData.get(indexParent));
                            } else {
                                Integer indexParent = Integer.valueOf(split1[1]);
                                Integer indexChild = Integer.valueOf(split1[2]);
                                tvArrs[i].setText(childData.get(indexParent).get(indexChild));
                            }

                        }
                    }

                }
            }

        }
    }

    private void setShowMode(String[] names) {
        selectTabText = names;
        mTvFirst.setText(names[0]);
        mTvSecond.setText(names[1]);
        mTvThird.setText(names[2]);

        int color = UIUtils.getColor(R.color.text_grey);
        mTvFirst.setTextColor(color);
        mTvSecond.setTextColor(color);
        mTvThird.setTextColor(color);
        mTvLast.setTextColor(color);


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
            selectPopWindow = new SelectPopupWindow(parentData, childData, mActivity, selectedSel, new SelectPopupWindow.SelectCategory() {

                @Override
                public void selectCategory(int parPos, String tvParent, int childPos, String tvChild) {
                    L.v("---" + parPos + "," + tvParent + "," + childPos + "," + tvChild);
                    String oriText = "全部";
                    int normalColor = UIUtils.getColor(R.color.text_grey);
                    int blueColor = UIUtils.getColor(R.color.common_blue);
                    String s = mSelectIndex + "_" + parPos + "_" + childPos;
                    if (mSelectIndex == 0) {
                        selectedSelection1 = s;
                        mTvFirst.setText(tvChild);
                        if (TextUtils.equals(oriText, mTvFirst.getText().toString().trim())) {
                            mTvFirst.setTextColor(normalColor);
                            mTvFirst.setText(selectTabText[0]);
                        } else {
                            mTvFirst.setTextColor(blueColor);
                        }

                    } else if (mSelectIndex == 1) {
                        selectedSelection2 = s;
                        if (childPos == -1) {
                            mTvSecond.setText(tvParent);
                        } else {
                            mTvSecond.setText(tvChild);
                        }
                        mTvSecond.setTextColor(UIUtils.getColor(R.color.common_blue));

                        if (TextUtils.equals(oriText, mTvSecond.getText().toString().trim())) {
                            mTvSecond.setTextColor(normalColor);
                            mTvSecond.setText(selectTabText[1]);
                        } else {
                            mTvSecond.setTextColor(blueColor);
                        }


                    } else if (mSelectIndex == 2) {
                        selectedSelection3 = s;
                        if (childPos == -1) {
                            mTvThird.setText(tvParent);
                        } else {
                            mTvThird.setText(tvChild);
                        }
                        if (TextUtils.equals(oriText, mTvThird.getText().toString().trim())) {
                            mTvThird.setTextColor(normalColor);
                            mTvThird.setText(selectTabText[2]);
                        } else {
                            mTvThird.setTextColor(blueColor);
                        }
                    } else if (mSelectIndex == 3) {
                        selectedSelection4 = s;
                        if (childPos == -1) {
                            mTvLast.setText(tvParent);
                        } else {
                            mTvLast.setText(tvChild);
                        }
                        mTvLast.setTextColor(UIUtils.getColor(R.color.common_blue));
                        if (TextUtils.equals(oriText, mTvLast.getText().toString().trim())) {
                            mTvLast.setTextColor(normalColor);

                            if (selectTabText.length == 3) {
                                mTvLast.setText(selectTabText[2]);
                            } else {
                                mTvLast.setText(selectTabText[3]);
                            }

                        } else {
                            mTvLast.setTextColor(blueColor);
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
//                    mTvFirst.setTextColor(normalColor);
                    mIvFirstArrow.setImageDrawable(down);
//                    mTvSecond.setTextColor(normalColor);
                    mIvSecondArrow.setImageDrawable(down);
//                    mTvThird.setTextColor(normalColor);
                    mIvThirdArrow.setImageDrawable(down);
//                    mTvLast.setTextColor(normalColor);
                    mIvLastArrow.setImageDrawable(down);

                    // 检测是否需要恢复文字颜色

                    if (TextUtils.equals(mTvFirst.getText().toString(), selectTabText[0])) {
                        mTvFirst.setTextColor(normalColor);
                    }
                    if (TextUtils.equals(mTvSecond.getText().toString(), selectTabText[1])) {
                        mTvSecond.setTextColor(normalColor);
                    }
                    if (TextUtils.equals(mTvThird.getText().toString(), selectTabText[2])) {
                        mTvThird.setTextColor(normalColor);
                    }
                    if (selectTabText.length == 3) {
                        if (TextUtils.equals(mTvLast.getText().toString(), selectTabText[2])) {
                            mTvLast.setTextColor(normalColor);
                        }
                    } else {
                        if (TextUtils.equals(mTvLast.getText().toString(), selectTabText[3])) {
                            mTvLast.setTextColor(normalColor);
                        }
                    }


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
            } else if (selectIndex == 3) {//更多 TODO

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
                parentData = Arrays.asList(new String[]{"全部", "1室", "2室", "3室", "4室", "5室", "5室以上"});
                childData.clear();
            } else if (selectIndex == 2) {// null or 方式 TODO
                parentData = Arrays.asList(new String[]{"全部", "整租", "合租"});
                childData.clear();
            } else if (selectIndex == 3) {//来源
                parentData = Arrays.asList(new String[]{"全部", "个人", "经纪人"});
                childData.clear();
            }
        }


    }

}
