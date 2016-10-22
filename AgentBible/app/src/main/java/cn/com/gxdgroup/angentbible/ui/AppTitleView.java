package cn.com.gxdgroup.angentbible.ui;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.com.gxdgroup.angentbible.holder.impl.selector.SelectionHolder;
import cn.com.gxdgroup.angentbible.interfaces.AppTitleListener;
import cn.com.gxdgroup.angentbible.R;
import cn.com.gxdgroup.angentbible.utils.L;
import cn.com.gxdgroup.angentbible.utils.UIUtils;

/**
 * Created by Ivy on 2016/10/19.
 *
 * @description: 头部 （ivBack +Title + SearchView + Tab + RightText + RightImage）
 */

public class AppTitleView extends RelativeLayout implements View.OnClickListener {

    private Context mContext;

    private int mMenuType;
    private ImageView ivBack, ivRight;
    private Button btTabLeft, btTabRight;
    private View rlSearch, llTab;
    private TextView tvRight, tvTitle;
    private AppTitleListener mListener;
    private FrameLayout frSelection;
    private SelectionHolder selectionHolder;

    public static enum MODE {
        SEARCH, TAB, TITLE, TITLE_R_TV, TITLE_R_IV;
    }

    private String mTitleMsg, mRightMsg, mTabRightMsg, mTabLeftMsg;

    public AppTitleView(Context context) {
        super(context);
    }

    public AppTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AppTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

    }

    private void init(final Context context) {
        LayoutInflater.from(context).inflate(R.layout.app_title_view, this);

        ivBack = (ImageView) findViewById(R.id.iv_back);
        rlSearch = findViewById(R.id.rl_search);
        llTab = findViewById(R.id.ll_tab);
        btTabLeft = (Button) findViewById(R.id.bt_tab_left);
        btTabRight = (Button) findViewById(R.id.bt_tab_right);
        ivRight = (ImageView) findViewById(R.id.iv_right);
        tvRight = (TextView) findViewById(R.id.tv_right);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        frSelection = (FrameLayout) findViewById(R.id.fr_selection);

        ivBack.setOnClickListener(this);
        rlSearch.setOnClickListener(this);
        btTabLeft.setOnClickListener(this);
        btTabRight.setOnClickListener(this);
        ivRight.setOnClickListener(this);
        tvRight.setOnClickListener(this);
        showMode(MODE.SEARCH, -1, null);
    }


    // 一些设置方法

    public void setListener(AppTitleListener listener) {
        mListener = listener;
    }

    public void setTitleMsg(String titleMsg) {
        mTitleMsg = titleMsg;
        tvTitle.setText(titleMsg);
    }

    public void setRightMsg(String rightMsg) {
        mRightMsg = rightMsg;
        tvRight.setText(rightMsg);
    }

    public void setTabRightMsg(String tabRightMsg) {
        mTabRightMsg = tabRightMsg;
        btTabRight.setText(tabRightMsg);
    }

    public void setTabLeftMsg(String tabLeftMsg) {
        mTabLeftMsg = tabLeftMsg;
        btTabLeft.setText(tabLeftMsg);
    }

    //点击事件

    @Override
    public void onClick(View v) {
        if (mListener != null) {
            switch (v.getId()) {
                case R.id.iv_back:
                    mListener.onBack();
                    break;
                case R.id.rl_search:
                    mListener.OnrlSearch();
                    break;
                case R.id.iv_right:
                    mListener.OnivRight();
                    break;
                case R.id.tv_right:
                    mListener.OntvRight();
                    break;
                case R.id.bt_tab_left:
                    mListener.onTabLeft();
                    setTab(0);
                    break;
                case R.id.bt_tab_right:
                    mListener.onTabRight();
                    setTab(1);
                    break;
            }
        }
    }

    private void setTab(int index) {
        if (index == 0) {
            btTabLeft.setTextColor(UIUtils.getColor(R.color.common_blue));
            btTabLeft.setBackgroundDrawable(UIUtils.getDrawable(R.drawable.shape_tab_left_pre));
            btTabRight.setTextColor(UIUtils.getColor(R.color.white));
            btTabRight.setBackgroundDrawable(UIUtils.getDrawable(R.drawable.shape_tab_right_normal));
        } else {
            btTabRight.setTextColor(UIUtils.getColor(R.color.common_blue));
            btTabRight.setBackgroundDrawable(UIUtils.getDrawable(R.drawable.shape_tab_right_pre));
            btTabLeft.setTextColor(UIUtils.getColor(R.color.white));
            btTabLeft.setBackgroundDrawable(UIUtils.getDrawable(R.drawable.shape_tab_left_normal));
        }

        // 判断是否menuType==2,如果是的话还要修改SelectionHolder的显示布局

        if (mMenuType == 2) {
            selectionHolder.setSelectionByTabIndex(index);
        }
    }

    /**
     * 设置显示模式
     * @param menuType 0-二手房，1-租房，2-客源，3-最新成交 【后来发现可以不用传进来因为已经传进来了上下文，可以通过它来获取menuType判断，但是要保证是否这个界面还有其他的入口？】
     * 感觉还是通过传入menuType来判断比较安全，因为用户有可能会从搜索界面进来。
     */
    public void showMode(MODE mode, int menuType, FragmentActivity activity) {
        this.mMenuType = menuType;
        switch (mode) {
            case SEARCH:
                setVisible(false, llTab, tvRight, ivRight, tvTitle).setVisible(true, rlSearch);
                break;
            case TITLE:
                setVisible(false, llTab, rlSearch, ivRight).setVisible(true, tvTitle);
            case TITLE_R_IV:
                setVisible(false, llTab, rlSearch, tvRight).setVisible(true, ivRight);
            case TITLE_R_TV:
                setVisible(false, llTab, rlSearch, ivRight).setVisible(true, tvRight);
                break;
            case TAB:
                setVisible(false, rlSearch, tvTitle, ivRight, tvRight).setVisible(true, llTab);
                break;
        }

        if (menuType == -1) {
            frSelection.setVisibility(GONE);
        } else {
            frSelection.setVisibility(VISIBLE);
            selectionHolder = new SelectionHolder(activity, menuType);
            frSelection.addView(selectionHolder.getContentView());
        }
    }

    /**
     * 设置控件的显示
     */
    private AppTitleView setVisible(boolean show, View... views) {
        for (View v : views) {
            v.setVisibility(show ? VISIBLE : GONE);
        }
        return this;
    }
}
