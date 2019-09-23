package com.ghs.ghspm.models.workdesk.signAndTable.tableTaskToDo.formTaskSign.autoForm;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghspm.R;
import com.ghs.ghspm.base.displayPhotos.DisplayPhotosActivity;
import com.ghs.ghspm.bean.AutoFormBean;
import com.ghs.ghspm.tools.StrUtils;

import java.util.List;

/**
 * Author:wang_sir
 * Time:2018/12/25 17:56
 * Description:This is LeftMenuAdapter
 */
public class RightPresentAdapter extends BaseQuickAdapter<List<AutoFormBean.DataBean>, BaseViewHolder> {

    public RightPresentAdapter(int layoutResId) {
        super(layoutResId);
    }

    private RightContentAdapterItemChildClick childClicklisten;

    public void setOnRightContentAdapterItemChildClickListener(RightContentAdapterItemChildClick rightContentAdapterItemChildClickListener) {
        this.childClicklisten = rightContentAdapterItemChildClickListener;
    }

    @Override
    protected void convert(BaseViewHolder helper, List<AutoFormBean.DataBean> item) {
        Log.d("RIGHTA", "----------" + String.valueOf(helper.getAdapterPosition()));
        final int pos = helper.getAdapterPosition();
        if (pos == mData.size()-1) {
            if (childClicklisten != null) {
                childClicklisten.loadFinished();
            }
        }
        RecyclerView recyclerView = helper.getView(R.id.right_item_child_rv);
//右侧标题
        RightChildAdapter rightContentAdapter = new RightChildAdapter(R.layout.test_text);
        LinearLayoutManager managere = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(managere);
        recyclerView.setAdapter(rightContentAdapter);
        rightContentAdapter.addData(item);
        rightContentAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                AutoFormBean.DataBean dataBean = (AutoFormBean.DataBean) adapter.getData().get(position);
                if ("image".equals(dataBean.getCssClass())) {
                    String prevalue = dataBean.getPrevalue();
                    Log.i("TAG", "imageUrl--------" + prevalue);
                    if (StrUtils.isStringValueOk(prevalue)) {
                        new DisplayPhotosActivity().startDisplayPhotosActivity(mContext, dataBean.getPrevalue(), 0);
                    }else{
                        if (childClicklisten != null) {
                            childClicklisten.onRightContentChildClick(dataBean, pos);
                        }
                    }
                } else {
                    if (childClicklisten != null) {
                        childClicklisten.onRightContentChildClick(dataBean, pos);
                    }
                }
            }
        });

    }

    public interface RightContentAdapterItemChildClick {
        void onRightContentChildClick(AutoFormBean.DataBean dataBean, int presentPosition);

        void loadFinished();//数据加载完毕
    }

}
