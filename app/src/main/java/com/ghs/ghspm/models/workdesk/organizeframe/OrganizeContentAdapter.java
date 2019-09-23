package com.ghs.ghspm.models.workdesk.organizeframe;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.bean.OrganizeItem;
import com.ghs.ghspm.bean.RoleBean;
import com.ghs.ghspm.bean.UserInfoBean;
import com.ghs.ghspm.bean.UsersFromRoleBean;
import com.ghs.ghspm.tools.StrUtils;

import java.util.List;

/**
 * Author:wang_sir
 * Time:2018/10/11 15:24
 * Description:This is OrganizeContentAdapter
 */
public class OrganizeContentAdapter extends BaseMultiItemQuickAdapter<OrganizeItem, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */

    OnHeadMsgCallBack onHeadMsgCallBack;
    public OrganizeContentAdapter(List<OrganizeItem> data) {
        super(data);
        addItemType(OrganizeItem.DEPS, R.layout.organize_deps_item);
        addItemType(OrganizeItem.USERS, R.layout.organize_users_item);
        addItemType(OrganizeItem.USER, R.layout.organize_user_item);
    }

    public void setOnHeadBgCallBack(OnHeadMsgCallBack onHeadMsgCallBack) {
        this.onHeadMsgCallBack = onHeadMsgCallBack;
    }

    @Override
    protected void convert(BaseViewHolder helper, OrganizeItem item) {
        switch (helper.getItemViewType()) {
            case OrganizeItem.DEPS:
                RoleBean.DataBean dataBean = (RoleBean.DataBean) item.getObject();
                helper.setText(R.id.organize_deps_item_name_tv, dataBean.getRoleName() + "(" + dataBean.getUserNum() + "人）");
                break;
            case OrganizeItem.USERS:
                UsersFromRoleBean.DataBean usersFromRoleBean = (UsersFromRoleBean.DataBean) item.getObject();
                helper.setText(R.id.organize_user_name_tv, usersFromRoleBean.getName());
                helper.setText(R.id.organize_user_job_tv, usersFromRoleBean.getPosition());
                if (usersFromRoleBean.getDuty() > 0) {
                    helper.setText(R.id.organize_user_work_status_tv, "今日值班");

                    helper.setBackgroundRes(R.id.organize_user_work_status_tv, R.mipmap.table_task_top_tag);
                    helper.setTextColor(R.id.organize_user_work_status_tv, ContextCompat.getColor(mContext, R.color.app_white));
                } else {
                    helper.setText(R.id.organize_user_work_status_tv, usersFromRoleBean.getWorkState());

                    helper.setBackgroundColor(R.id.organize_user_work_status_tv, Color.parseColor("#F8F8F8"));
                    helper.setTextColor(R.id.organize_user_work_status_tv, ContextCompat.getColor(mContext, R.color.app_black));

                }

                if (StrUtils.isStringValueOk(usersFromRoleBean.getHeadImage())) {
                    ((BaseActivity) mContext).setHeadPicBgResource((ImageView) helper.getView(R.id.organize_user_head_iv), (TextView) helper.getView(R.id.organize_user_head_tv), usersFromRoleBean.getName(), usersFromRoleBean.getHeadImage(), usersFromRoleBean.getHeadImageBackGroudColor(), true);
                }

                break;
            case OrganizeItem.USER:
                UserInfoBean.DataBean.UserConfigListBean userBean = (UserInfoBean.DataBean.UserConfigListBean) item.getObject();
                helper.setGone(R.id.organize_user_head_iv, false);
                helper.setGone(R.id.organize_user_head_tv, false);
                helper.setGone(R.id.organize_item_value_tv, false);
                helper.setTextColor(R.id.organize_item_value_tv,ContextCompat.getColor(mContext,R.color.app_black));

                if ("头像".equals(userBean.getTitle())) {
                    helper.setGone(R.id.organize_user_head_iv, true);
                    helper.setGone(R.id.organize_user_head_tv, true);
                    helper.setText(R.id.organize_user_key_tv, userBean.getTitle());
                    if (onHeadMsgCallBack != null) {
                        onHeadMsgCallBack.setHeadPicBg(helper.getView(R.id.organize_user_head_iv),helper.getView(R.id.organize_user_head_tv));
                    }

                } else {

                    helper.setGone(R.id.organize_item_value_tv, true);
                    helper.setBackgroundRes(R.id.organize_item_value_tv, 0);
                    if ("电话".equals(userBean.getTitle())) {
                        helper.setBackgroundRes(R.id.organize_item_value_tv, R.drawable.bg_gray_bottom_blue_shape);
                        helper.setTextColor(R.id.organize_item_value_tv,ContextCompat.getColor(mContext,R.color.app_default_blue));
                    }
                    helper.setText(R.id.organize_user_key_tv, userBean.getTitle());
                    helper.setText(R.id.organize_item_value_tv, userBean.getValue());
                }
                break;
            default:
                break;
        }
    }

    public interface  OnHeadMsgCallBack{
       void  setHeadPicBg(ImageView imageView,TextView textView);
    }
}
