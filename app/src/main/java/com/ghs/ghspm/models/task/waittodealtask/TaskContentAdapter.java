package com.ghs.ghspm.models.task.waittodealtask;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghspm.R;
import com.ghs.ghspm.bean.TaskContentMapBean;

import java.util.List;

public class TaskContentAdapter extends BaseQuickAdapter<TaskContentMapBean, BaseViewHolder> {


    public TaskContentAdapter(int layoutResId, @Nullable List<TaskContentMapBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TaskContentMapBean item) {


        helper.setText(R.id.task_content_item_content_key, item.getTitle()+" :");
        helper.setText(R.id.task_content_item_content_value, item.getValue());
    }

}
