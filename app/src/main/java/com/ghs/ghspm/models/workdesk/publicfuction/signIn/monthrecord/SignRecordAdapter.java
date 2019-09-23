package com.ghs.ghspm.models.workdesk.publicfuction.signIn.monthrecord;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghspm.R;
import com.ghs.ghspm.bean.ShiftRecordBean;
import com.ghs.ghspm.tools.CalendarUtil;
import com.ghs.ghspm.tools.StrUtils;

/**
 * Author:wang_sir
 * Time:2018/10/20 19:47
 * Description:This is SignRecordAdapter
 */
public class SignRecordAdapter extends BaseQuickAdapter<ShiftRecordBean.DataBean.PmAttendanceClockDOListBean, BaseViewHolder> {
    public SignRecordAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShiftRecordBean.DataBean.PmAttendanceClockDOListBean item) {
        int position = helper.getAdapterPosition();
        if (mData.size() - 1 == position) {
            helper.setVisible(R.id.work_sign_virtual_line, false);
        } else {
            helper.setVisible(R.id.work_sign_virtual_line, true);
        }
        String signName = item.getClockType() == 1 ? "上班打卡 " : "下班打卡 ";
        String signTime = "";
        if (StrUtils.isStringValueOk(item.getClockTime())) {
            signTime = CalendarUtil.getTimeFromStringTime("HH:mm", "yyyy-MM-dd HH:mm:ss",item.getClockTime() );
        }

        helper.setText(R.id.work_time_tv, signTime);
        helper.setText(R.id.work_type_tv, signName);
        helper.setVisible(R.id.request_reSign_tv, false);
        helper.addOnClickListener(R.id.request_reSign_tv);
        initSignStatusTv(helper, item);
    }

    /**
     * 打卡状态
     * @param helper
     * @param item
     */
    private void initSignStatusTv(BaseViewHolder helper, ShiftRecordBean.DataBean.PmAttendanceClockDOListBean item) {
        helper.setTextColor(R.id.sign_status_tv, Color.RED);
        int errorCode = item.getErrorCode();//异常信息 1:缺卡 2:超出范围 3:迟到 4:严重迟到 5:旷工迟到 6:早退
        //打卡状态
        switch (errorCode) {
            case 0:
                helper.setTextColor(R.id.sign_status_tv, ContextCompat.getColor(mContext,R.color.app_black));

                helper.setText(R.id.sign_status_tv, "状态:正常");
                break;
            case 1:
                helper.setVisible(R.id.request_reSign_tv, true);
                helper.setText(R.id.sign_status_tv, "状态:缺卡");
                break;
            case 2:
                helper.setText(R.id.sign_status_tv, "状态:超出范围");

                break;
            case 3:
                helper.setText(R.id.sign_status_tv, "状态:迟到");

                break;
            case 4:
                helper.setText(R.id.sign_status_tv, "状态:严重迟到");

                break;
            case 5:
                helper.setText(R.id.sign_status_tv, "状态:旷工迟到");

                break;
            case 6:
                helper.setText(R.id.sign_status_tv, "状态:早退");

                break;
            default:
                break;
        }
    }
}
