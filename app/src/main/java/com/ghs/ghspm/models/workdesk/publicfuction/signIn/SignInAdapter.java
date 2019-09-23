package com.ghs.ghspm.models.workdesk.publicfuction.signIn;

import android.support.v4.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghspm.R;
import com.ghs.ghspm.bean.UserCardInfo;
import com.ghs.ghspm.tools.PubUtil;

/**
 * Author:wang_sir
 * Time:2018/10/19 17:14
 * Description:This is SignInAdapter
 */
public class SignInAdapter extends BaseQuickAdapter<UserCardInfo.DataBean.ClockRecordListBean, BaseViewHolder> {
    public SignInAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserCardInfo.DataBean.ClockRecordListBean item) {
        int position = helper.getAdapterPosition();
        helper.addOnClickListener(R.id.sign_update_tv);
        if (mData.size() - 1 == position) {
            helper.setVisible(R.id.work_sign_virtual_line, false);
        } else {
            helper.setVisible(R.id.work_sign_virtual_line, true);
        }
        initTimeTagStatus(helper, item);
        String shiftDay = item.getShiftDay();
        helper.setText(R.id.work_sign_date_tv, shiftDay);
        initClockTimeAndClockAddress(helper, item);


    }

    /**
     * 初始化时间轴小圆点的状态  待打卡时高亮，已打卡和未打卡显示灰色
     *
     * @param helper
     * @param item
     */
    private void initTimeTagStatus(BaseViewHolder helper, UserCardInfo.DataBean.ClockRecordListBean item) {
        boolean timeTagLight = item.isTimeTagLight();
        if (timeTagLight) {
            helper.setImageResource(R.id.work_sign_tag_iv, R.drawable.open_record_circle_default_color_shape);
            initWorkTypeValue(helper, item, true);
        } else {
            helper.setImageResource(R.id.work_sign_tag_iv, R.drawable.circle_gray_all_shape);
            initWorkTypeValue(helper, item, false);

        }
    }

    /**
     * 初始化打卡时间和打卡地点的状态
     *
     * @param helper
     * @param item
     */
    private void initClockTimeAndClockAddress(BaseViewHolder helper, UserCardInfo.DataBean.ClockRecordListBean item) {
        int result = item.getResult();// -1:未打卡 1：true;0：false
        helper.setGone(R.id.sign_update_tv, false);
        if (-1 == result) {
            setClockAddrInfo(helper, item, false);
        } else if (0 == result) {
            setClockAddrInfo(helper, item, true);
        } else if (1 == result) {
            setClockAddrInfo(helper, item, true);
        }
    }

    /**
     * @param helper
     * @param item
     * @param tagLight 时间标签（小圆点） 高亮
     */
    private void initWorkTypeValue(BaseViewHolder helper, UserCardInfo.DataBean.ClockRecordListBean item, boolean tagLight) {
        //排班的时间 和 日期
        int clockType = item.getClockType();
        String content = "";
        if (2 == PubUtil.SHIFT_TYPE) {//自由工时
            content = clockType == 1 ? "上班" : "下班";
        } else {
            String shiftTime = item.getShiftTime();
            String workType = clockType == 1 ? "上班时间" : "下班时间";
            content = workType + shiftTime;
        }
        helper.setText(R.id.work_type_tv, content);
        if (tagLight) {
            helper.setTextColor(R.id.work_type_tv, ContextCompat.getColor(mContext,R.color.app_default_blue));
        } else {
            helper.setTextColor(R.id.work_type_tv,ContextCompat.getColor(mContext,R.color.hint_color));


        }
    }

    /**
     * 设置打卡地址
     *
     * @param visible
     */
    private void setClockAddrInfo(BaseViewHolder helper, UserCardInfo.DataBean.ClockRecordListBean item, boolean visible) {
        if (visible) {
            helper.setGone(R.id.sign_abnormal_tag_tv, true);
            int errorCode = item.getErrorCode();//1:缺卡 2:超出范围 3:迟到 4:严重迟到 5:旷工迟到 6:早退
            String clockTime = item.getClockTime();
            String clockPlace = item.getClockPlace();
            helper.setGone(R.id.sign_time_tv, true);
            helper.setGone(R.id.sign_addr_icon_iv, true);
            helper.setGone(R.id.sign_addr_tv, true);
            int replaceClockId = item.getReplaceClockId();
            if (replaceClockId > 0) {//已补卡
                helper.setGone(R.id.sign_addr_icon_iv, false);
                helper.setText(R.id.sign_time_tv, "补卡时间 " + clockTime);
                helper.setText(R.id.sign_addr_tv, "已补卡");
            } else {
                helper.setText(R.id.sign_time_tv, "打卡时间 " + clockTime);
                helper.setText(R.id.sign_addr_tv, clockPlace);
            }
            //更新打卡是否展示
            int updateBt = item.getUpdateBut();//0:不显示 1:显示
            if (1 == updateBt) {//显示更新打卡按钮
                helper.setGone(R.id.sign_update_tv, true);
                helper.setText(R.id.sign_update_tv, "更新打卡");
            } else {
                helper.setGone(R.id.sign_update_tv, false);
            }
            //打卡状态
            switch (errorCode) {
                case 0:
                    helper.setGone(R.id.sign_abnormal_tag_tv, false);
                    break;
                case 1:
                    helper.setGone(R.id.sign_time_tv, false);
                    helper.setGone(R.id.sign_addr_icon_iv, false);
                    helper.setGone(R.id.sign_addr_tv, false);
                    helper.setText(R.id.sign_abnormal_tag_tv, "缺卡");
                    helper.setGone(R.id.sign_update_tv, true);
                    helper.setText(R.id.sign_update_tv, "申请补卡");
                    break;
                case 2:
                    helper.setText(R.id.sign_abnormal_tag_tv, "超出范围");

                    break;
                case 3:
                    helper.setText(R.id.sign_abnormal_tag_tv, "迟到");

                    break;
                case 4:
                    helper.setText(R.id.sign_abnormal_tag_tv, "严重迟到");

                    break;
                case 5:
                    helper.setText(R.id.sign_abnormal_tag_tv, "旷工迟到");

                    break;
                case 6:
                    helper.setText(R.id.sign_abnormal_tag_tv, "早退");

                    break;
                default:
                    break;
            }
        } else {
            helper.setGone(R.id.sign_time_tv, false);
            helper.setGone(R.id.sign_abnormal_tag_tv, false);
            helper.setGone(R.id.sign_addr_icon_iv, false);
            helper.setGone(R.id.sign_addr_tv, false);
        }
    }
}
