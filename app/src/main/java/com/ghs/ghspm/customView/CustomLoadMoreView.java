package com.ghs.ghspm.customView;

import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.ghs.ghspm.R;

/**
 * Author:wang_sir
 * Time:2019/1/30 18:58
 * Description:This is CustomLoadMoreView
 */
public final class CustomLoadMoreView extends LoadMoreView {
    @Override public int getLayoutId() {
        return R.layout.view_load_more;
    }

    @Override protected int getLoadingViewId() {
        return R.id.load_more_loading_view;
    }

    @Override protected int getLoadFailViewId() {
        return R.id.load_more_load_fail_view;
    }

    @Override protected int getLoadEndViewId() {
        return R.id.load_more_load_end_view;
    }
}
