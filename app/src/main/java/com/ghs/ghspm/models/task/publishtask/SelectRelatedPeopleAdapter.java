package com.ghs.ghspm.models.task.publishtask;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghspm.R;
import com.ghs.ghspm.bean.OrganizeItem;
import com.ghs.ghspm.bean.RoleBean;
import com.ghs.ghspm.bean.UsersFromRoleBean;

import java.util.List;

/**
 * Author:wang_sir
 * Time:2018/10/11 15:24
 * Description:This is OrganizeContentAdapter
 */
public class SelectRelatedPeopleAdapter extends BaseMultiItemQuickAdapter<OrganizeItem,BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public SelectRelatedPeopleAdapter(List<OrganizeItem> data) {
        super(data);
        addItemType(OrganizeItem.DEPS, R.layout.organize_deps_item);
        addItemType(OrganizeItem.USERS, R.layout.select_user_item);
    }


    @Override
    protected void convert(BaseViewHolder helper, OrganizeItem item) {
        switch (helper.getItemViewType()) {
            case OrganizeItem.DEPS:
                RoleBean.DataBean dataBean = (RoleBean.DataBean) item.getObject();
                helper.setText(R.id.organize_deps_item_name_tv,dataBean.getRoleName()+"("+dataBean.getUserNum()+"人）");
                break;
            case OrganizeItem.USERS:
                UsersFromRoleBean.DataBean userListBean = (UsersFromRoleBean.DataBean) item.getObject();

                if (userListBean.isSelected()) {
                    helper.setImageResource(R.id.select_user_iv,R.mipmap.select_related_people_press);
                }else{
                    helper.setImageResource(R.id.select_user_iv,R.mipmap.select_related_people_normal);

                }
                helper.setText(R.id.organize_user_name_tv,userListBean.getName());
                helper.setText(R.id.organize_user_job_tv,userListBean.getPosition());
                helper.setText(R.id.organize_user_work_status_tv,userListBean.getWorkState());
                if (userListBean.getDuty()>0) {
                    helper.setText(R.id.organize_user_work_status_tv,"今日值班");

                    helper.setBackgroundRes(R.id.organize_user_work_status_tv,R.mipmap.table_task_top_tag);
                    helper.setTextColor(R.id.organize_user_work_status_tv, ContextCompat.getColor(mContext,R.color.app_white));
                }else{
                    helper.setText(R.id.organize_user_work_status_tv,userListBean.getWorkState());

                    helper.setBackgroundColor(R.id.organize_user_work_status_tv, Color.parseColor("#ffffff"));
                    helper.setTextColor(R.id.organize_user_work_status_tv,ContextCompat.getColor(mContext,R.color.app_black));

                }
                break;
        }

    }
}
