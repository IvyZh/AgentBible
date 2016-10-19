package cn.com.gxdgroup.angentbible.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.com.gxdgroup.angentbible.R;
import cn.com.gxdgroup.angentbible.base.BaseActivity;
import cn.com.gxdgroup.angentbible.ui.SearchBar;
import cn.com.gxdgroup.angentbible.utils.L;
import cn.com.gxdgroup.angentbible.utils.UIUtils;

/**
 * Created by Ivy on 2016/10/19.
 *
 * @description: 搜索界面（二手房）
 */

public class SearchActivity extends BaseActivity {
    @BindView(R.id.searchbar)
    SearchBar mSearchbar;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_search);
    }

    @Override
    protected void initView() {
        // 取消按钮
        mSearchbar.getViewSwitcher().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mSearchbar.getEditTextSearch().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int key, KeyEvent keyEvent) {

                String result = mSearchbar.getEditTextSearch().getText().toString().trim();
                L.v(key + "---" + keyEvent.getAction() + ",result:" + result);

                // 66 ---- 0
                if (key == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    if (TextUtils.isEmpty(result)) {
                        UIUtils.showToast("搜索内容不能为空");
                        return true;
                    }

                    UIUtils.showToast("搜索：" + result);// TODO 被调用了两次，待会用实体机测试看看
                    L.v("搜索：" + result);
                    return true;
                }
                return false;
            }
        });
    }


}
