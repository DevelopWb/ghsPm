package com.ghs.ghspm.models.workdesk.patrolForm.fillingForms;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghspm.R;
import com.ghs.ghspm.bean.PatrolTaskFormListBean;
import com.ghs.ghspm.tools.Contract;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FormFillingAdapter extends BaseQuickAdapter<PatrolTaskFormListBean.DataBean, BaseViewHolder> {

    public FormFillingAdapter(int layoutResId, @Nullable List<PatrolTaskFormListBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PatrolTaskFormListBean.DataBean item) {
        helper.setText(R.id.form_filling_item_title,item.getTaskName());

        String s = null;
        try {
            s = StringToDate(item.getCreateTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        helper.setText(R.id.form_filling_item_time,"时间："+s);
        Glide.with(mContext).load(Contract.ImageBasePath)
                .skipMemoryCache(false)
                // 加载图片
//                        .error(errorimg)
//                        // 设置错误图片
//                        .crossFade()
//                        // 设置淡入淡出效果，默认300ms，可以传参
                .placeholder(R.mipmap.statement)
                .into((ImageView) helper.getView(R.id.form_filling_item_image));

    }


    private String StringToDate(String time) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date;
        date = format.parse(time);
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy年MM月");
        String s = format1.format(date);

        return s;

    }

}
