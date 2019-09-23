package com.ghs.ghspm.models.task;

import android.util.Log;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghspm.R;
import com.ghs.ghspm.bean.TableTaskOutLineBean;
import com.ghs.ghspm.bean.TaskContentMapBean;
import com.ghs.ghspm.bean.TaskMultipleItem;
import com.ghs.ghspm.bean.TasksBean;
import com.ghs.ghspm.tools.CalendarUtil;
import com.ghs.ghspm.tools.GsonManager;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:wang_sir
 * Time:2018/9/29 10:59
 * Description:This is TaskAdapter
 */
public class TaskAdapter extends BaseMultiItemQuickAdapter<TaskMultipleItem, BaseViewHolder> {


    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public TaskAdapter(List<TaskMultipleItem> data) {
        super(data);
        addItemType(TaskMultipleItem.TEMP_TASK, R.layout.notice_item);
        addItemType(TaskMultipleItem.TABLE_TASK, R.layout.notice_item);
    }

    private List<TaskContentMapBean> parseJSONWithJSONObject(String JsonData) {
        List<TaskContentMapBean> list = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(JsonData);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String title = jsonObject.getString("title");
                String value = jsonObject.getString("value");
                list.add(new TaskContentMapBean(title, value));
            }

            return list;
        } catch (Exception e) {

            e.printStackTrace();

            return list;
        }


    }

    @Override
    protected void convert(BaseViewHolder helper, TaskMultipleItem dataBean) {
        switch (helper.getItemViewType()) {
            case TaskMultipleItem.TEMP_TASK:
                TasksBean.DataBean item = null;
                item = (TasksBean.DataBean) dataBean.getObject();
                List<TaskContentMapBean> contentList = parseJSONWithJSONObject(item.getContent());
                if(contentList.size()!= 0){

                    List<TaskContentMapBean> list = (List<TaskContentMapBean>) GsonManager.getInstance().parseJsonToList(item.getContent(), new TypeToken<List<TaskContentMapBean>>() {
                    }.getType());

                    for (TaskContentMapBean b : list) {
                        if ("工单内容".equals(b.getTitle())) {

                            helper.setText(R.id.notice_item_title_tv, b.getValue());
                            break;
                        }
                    }
                }else {
                    Log.i("TAG", item.getContent());
                    helper.setText(R.id.notice_item_title_tv,item.getContent());

                }


                helper.setText(R.id.notice_item_creater_tv, item.getCreateUserName());
                helper.setText(R.id.notice_item_time_tv, "时间:" + CalendarUtil.getTimeFromStringTime("yyyy-MM-dd HH:mm", "yyyy-MM-dd HH:mm:ss", item.getCreateTime()));
                int status = item.getStatus();
                //1已完成，2待处理，3完成待审核，4转发待审核，5放弃待审核，6已放弃
                switch (status) {
                    case 1:
                        helper.setText(R.id.notice_item_satsus_tv, "已完成");
                        break;
                    case 2:
                        helper.setText(R.id.notice_item_satsus_tv, "待处理");

                        break;
                    case 3:
                        helper.setText(R.id.notice_item_satsus_tv, "完成待审核");

                        break;
                    case 4:
                        helper.setText(R.id.notice_item_satsus_tv, "转发待审核");

                        break;
                    case 5:
                        helper.setText(R.id.notice_item_satsus_tv, "放弃待审核");

                        break;
                    case 6:
                        helper.setText(R.id.notice_item_satsus_tv, "已放弃");

                        break;
                    default:
                        break;
                }
                break;
            case TaskMultipleItem.TABLE_TASK:
                helper.setVisible(R.id.notice_item_title_tag_tv, true);
                TableTaskOutLineBean.DataBean tableTaskBean = (TableTaskOutLineBean.DataBean) dataBean.getObject();
                helper.setText(R.id.notice_item_title_tv, tableTaskBean.getTitle());
                helper.setText(R.id.notice_item_creater_tv, tableTaskBean.getUserName());
                helper.setText(R.id.notice_item_time_tv, "时间:" + CalendarUtil.getTimeFromStringTime("yyyy-MM-dd HH:mm", "yyyy-MM-dd HH:mm:ss", tableTaskBean.getCreateTime()));
                int tableTaskStatus = tableTaskBean.getStatus();
                //1已完成，2待处理，3完成待审核，4转发待审核，5放弃待审核，6已放弃
                switch (tableTaskStatus) {
                    case 1:
                        helper.setText(R.id.notice_item_satsus_tv, "待处理");
                        break;
                    case 2:
                        helper.setText(R.id.notice_item_satsus_tv, "待签批");

                        break;
                    case 3:
                        helper.setText(R.id.notice_item_satsus_tv, "已完成");

                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }


    }
}
