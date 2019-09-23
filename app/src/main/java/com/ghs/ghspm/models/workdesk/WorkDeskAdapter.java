package com.ghs.ghspm.models.workdesk;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghspm.R;
import com.ghs.ghspm.bean.MultiWorkDeskMenuBean;
import com.ghs.ghspm.bean.ToolFormBean;
import com.ghs.ghspm.tools.Contract;
import com.ghs.ghspm.tools.StrUtils;

import java.util.List;

/**
 * Author:wang_sir
 * Time:2018/6/29 11:16
 * Description:This is WorkDeskAdapter
 */

public class WorkDeskAdapter extends BaseMultiItemQuickAdapter<MultiWorkDeskMenuBean, BaseViewHolder> {


    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public WorkDeskAdapter(List<MultiWorkDeskMenuBean> data) {
        super(data);
        addItemType(MultiWorkDeskMenuBean.FIXED_MENU, R.layout.work_fragment_item);
        addItemType(MultiWorkDeskMenuBean.DYNAMIC_MENU, R.layout.work_fragment_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiWorkDeskMenuBean item) {

        switch (item.getItemType()) {
            case MultiWorkDeskMenuBean.FIXED_MENU:
                String name = (String) item.getObject();
                if ("组织架构".equals(name)) {
                    name = "通讯录";
                }
                helper.setText(R.id.work_item_content_tv, name);
                switch (name) {
                    case "任务打卡":
                        helper.setImageResource(R.id.work_item_content_iv, R.mipmap.task_sign_icon);
                        break;
                    case "内部报修":
                        helper.setImageResource(R.id.work_item_content_iv, R.mipmap.maintain_icon);
                        break;
                    case "通讯录":
                        helper.setImageResource(R.id.work_item_content_iv, R.mipmap.organize_icon);
                        break;
                    case "业主信息":
                        helper.setImageResource(R.id.work_item_content_iv, R.mipmap.user_info_icon);
                        break;
                    case "车辆信息":
                        helper.setImageResource(R.id.work_item_content_iv, R.mipmap.work_desk_car_icon);
                        break;
                    case "水电表抄录":
                        helper.setText(R.id.work_item_content_tv, "水电表抄录");
                        helper.setImageResource(R.id.work_item_content_iv, R.mipmap.work_desk_water_icon);
                        break;
                    case "账单管理":
                        helper.setImageResource(R.id.work_item_content_iv, R.mipmap.work_desk_car_icon);
                        break;
                    case "资金管理":
                        helper.setImageResource(R.id.work_item_content_iv, R.mipmap.work_desk_car_icon);
                        break;
                    case "签批与表单":
                        helper.setImageResource(R.id.work_item_content_iv, R.mipmap.sign_table_icon);
                        break;
                    case "任务打卡统计":
                        helper.setImageResource(R.id.work_item_content_iv, R.mipmap.work_desk_car_icon);
                        break;
                    case "排班":
                        helper.setImageResource(R.id.work_item_content_iv, R.mipmap.shift_icon);
                        break;
                    case "巡查巡检":

                        helper.setImageResource(R.id.work_item_content_iv, R.mipmap.xujian_icon);

                        break;

                    case "更多应用":
                        helper.setImageResource(R.id.work_item_content_iv, R.mipmap.gengduo);

                        break;
                    case "访客审核":
                        helper.setImageResource(R.id.work_item_content_iv, R.mipmap.property_visitor_check);

                        break;
                    default:
                        helper.setImageResource(R.id.work_item_content_iv, R.mipmap.ic_launcher);

                        break;
                }
                break;
            case MultiWorkDeskMenuBean.DYNAMIC_MENU:
                ToolFormBean.DataBean dataBean = (ToolFormBean.DataBean) item.getObject();
                helper.setText(R.id.work_item_content_tv, dataBean.getFormName());
                ImageView imageView = helper.getView(R.id.work_item_content_iv);
                String imagePath = dataBean.getIcon();
                if (StrUtils.isStringValueOk(imagePath)) {
                    Glide.with(mContext).load(Contract.ImageBasePath + dataBean.getIcon())
                            .skipMemoryCache(false)
                            // 加载图片
//                        .error(errorimg)
//                        // 设置错误图片
//                        .crossFade()
//                        // 设置淡入淡出效果，默认300ms，可以传参
//                            .placeholder(R.mipmap.default_user_head_icon)
                            // 设置占位图
//                            .transform(new GlideCircleTransform(context))//圆角
                            .into(imageView);
                }

                break;
            default:
                break;
        }

    }
}