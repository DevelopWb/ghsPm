package com.ghs.ghspm.models.workdesk.signAndTable.tableTaskToDo.createForm;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghspm.R;
import com.ghs.ghspm.bean.DynamicLayoutBean;
import com.ghs.ghspm.bean.TableMultipleItem;
import com.ghs.ghspm.tools.StrUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:wang_sir
 * Time:2018/9/29 10:59
 * Description:This is 需要填充的表单的适配器
 */
public class CreateFormAdapter extends BaseMultiItemQuickAdapter<TableMultipleItem, BaseViewHolder> {


    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */

    private DynamicLayoutListener edittextChangedListener;

    private List<String> ImageList = new ArrayList<>();

    public CreateFormAdapter(List<TableMultipleItem> data, DynamicLayoutListener edittextChangedListener) {
        super(data);
        this.edittextChangedListener = edittextChangedListener;
        addItemType(TableMultipleItem.TABLE_TEXT_SHOW, R.layout.dynamic_layout_text_show);//纯文本显示
        addItemType(TableMultipleItem.TABLE_TEXT, R.layout.dynamic_layout_text);//单行文本输入
        addItemType(TableMultipleItem.TABLE_AREA, R.layout.dynamic_layout_text_area);//多行文本输入
        addItemType(TableMultipleItem.TABLE_RADIO, R.layout.dynamic_layout_radio);//单选
        addItemType(TableMultipleItem.TABLE_CHECKBOX, R.layout.dynamic_layout_radio);
        addItemType(TableMultipleItem.TABLE_NUMBER, R.layout.dynamic_layout_text);
        addItemType(TableMultipleItem.TABLE_MONEY, R.layout.dynamic_layout_text);
        addItemType(TableMultipleItem.TABLE_USER, R.layout.dynamic_layout_radio);//选择人
        addItemType(TableMultipleItem.TABLE_COPY_USER, R.layout.dynamic_layout_radio);//抄送人
        addItemType(TableMultipleItem.TABLE_CHECK_USER, R.layout.dynamic_layout_radio);//审批人
        addItemType(TableMultipleItem.TABLE_IMAGE, R.layout.dynamic_layout_image);
        addItemType(TableMultipleItem.TABLE_DATE, R.layout.dynamic_layout_radio);
        addItemType(TableMultipleItem.TABLE_DEVICE, R.layout.dynamic_layout_radio);
        addItemType(TableMultipleItem.TABLE_TOWER, R.layout.dynamic_layout_radio);
        addItemType(TableMultipleItem.TABLE_CELL, R.layout.dynamic_layout_radio);
        addItemType(TableMultipleItem.TABLE_ROOM, R.layout.dynamic_layout_radio);
    }


    @Override
    protected void convert(BaseViewHolder helper, TableMultipleItem dataBean) {
        DynamicLayoutBean dataBeanObject = null;
        int position = helper.getAdapterPosition();
        final EditText singleEdittext = helper.getView(R.id.dynamic_layout_text_content_et);
        TextView singleTextView = helper.getView(R.id.dynamic_layout_select_tv);
//        helper.addOnClickListener(R.id.dynamic_layout_text_content_et);
        int type = helper.getItemViewType();
            dataBeanObject = (DynamicLayoutBean) dataBean.getObject();
            final DynamicLayoutBean dynamicLayoutBean = dataBeanObject;
            if (singleEdittext != null) {
                //1.防止数据混乱 绑定实体类
                singleEdittext.setTag(dataBeanObject);
                singleEdittext.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        //2.通过tag获取实体类 赋值
                        DynamicLayoutBean dynamicLayoutBean1 = (DynamicLayoutBean) singleEdittext.getTag();
                        dynamicLayoutBean1.setValue(s.toString().trim());
                    }
                });
                addEditListener(singleEdittext, dynamicLayoutBean, position);
            }
            boolean required = StrUtils.isStringValueOk(dataBeanObject.getRequired()) ? true : false;
            TextView requiredTag = helper.getView(R.id.lack_cord_tag_tv);
            if (requiredTag != null) {
                if (required) {
                    requiredTag.setVisibility(View.VISIBLE);
                } else {
                    requiredTag.setVisibility(View.INVISIBLE);
                }
            }
        if (dataBeanObject != null) {
            switch (type) {
                case TableMultipleItem.TABLE_TEXT_SHOW://纯文本显示
                    helper.setText(R.id.dynamic_layout_text_notice_tv, dataBeanObject.getValue());
                    break;
                case TableMultipleItem.TABLE_TEXT://单行文本
                    helper.setText(R.id.dynamic_layout_text_title_tv, dataBeanObject.getTitle());
                    setViewValue(dataBeanObject, singleEdittext);

                    break;
                case TableMultipleItem.TABLE_AREA://多行文本
                    helper.setText(R.id.dynamic_layout_text_title_tv, dataBeanObject.getTitle());
                    setViewValue(dataBeanObject, singleEdittext);


                    break;
                case TableMultipleItem.TABLE_RADIO://单项选择框
                    helper.setText(R.id.reason_of_abcence_title_tv, dataBeanObject.getTitle());
                    setViewValue(dataBeanObject, singleTextView);


                    break;
                case TableMultipleItem.TABLE_CHECKBOX://多项选择框
                    helper.setText(R.id.reason_of_abcence_title_tv, dataBeanObject.getTitle());
                    setViewValue(dataBeanObject, singleTextView);


                    break;
                case TableMultipleItem.TABLE_NUMBER://数字输入框
                    singleEdittext.setLines(1);
                    helper.setText(R.id.dynamic_layout_text_title_tv, dataBeanObject.getTitle());
                    setViewValue(dataBeanObject, singleEdittext);
                    singleEdittext.setInputType(EditorInfo.TYPE_CLASS_NUMBER | EditorInfo.TYPE_NUMBER_FLAG_DECIMAL);

                    break;
                case TableMultipleItem.TABLE_MONEY://金额输入框
                    singleEdittext.setLines(1);
                    helper.setText(R.id.dynamic_layout_text_title_tv, dataBeanObject.getTitle());
                    setViewValue(dataBeanObject, singleEdittext);
                    singleEdittext.setInputType(EditorInfo.TYPE_CLASS_NUMBER | EditorInfo.TYPE_NUMBER_FLAG_DECIMAL);

                    break;
                case TableMultipleItem.TABLE_USER://选择人员，可多选
                    helper.setText(R.id.reason_of_abcence_title_tv, dataBeanObject.getTitle());
                    TextView  textView_more = helper.getView(R.id.dynamic_layout_select_tv);
                    setViewValue(dataBeanObject, textView_more);


                    break;
                case TableMultipleItem.TABLE_COPY_USER://选择人员，抄送人
                    helper.setText(R.id.reason_of_abcence_title_tv, dataBeanObject.getTitle());
                    TextView  textView_cc = helper.getView(R.id.dynamic_layout_select_tv);
                    setViewValue(dataBeanObject, textView_cc);

                    break;
                case TableMultipleItem.TABLE_CHECK_USER://选择人员，审批人
                    helper.setText(R.id.reason_of_abcence_title_tv, dataBeanObject.getTitle());
                    TextView  textView = helper.getView(R.id.dynamic_layout_select_tv);
                    setViewValue(dataBeanObject, textView);

                    break;
                case TableMultipleItem.TABLE_IMAGE://图片
                    helper.setText(R.id.dynamic_layout_image_title_tv, dataBeanObject.getTitle());
                    RecyclerView recyclerView = helper.getView(R.id.dynamic_layout_select_photos_rv);
                    recyclerView.setTag(dataBeanObject);
                    String value = dataBeanObject.getValue();
                    if (edittextChangedListener != null) {
                        edittextChangedListener.getSelectPhotosData(value, recyclerView);
                    }
                    break;
                case TableMultipleItem.TABLE_DATE://日期
                    helper.setText(R.id.reason_of_abcence_title_tv, dataBeanObject.getTitle());
                    setViewValue(dataBeanObject, singleTextView);


                    break;
                case TableMultipleItem.TABLE_DEVICE://设备单选框
                    helper.setText(R.id.reason_of_abcence_title_tv, dataBeanObject.getTitle());
                    setViewValue(dataBeanObject, singleTextView);


                    break;
                case TableMultipleItem.TABLE_TOWER://楼栋单选框
                    helper.setText(R.id.reason_of_abcence_title_tv, dataBeanObject.getTitle());
                    setViewValue(dataBeanObject, singleTextView);
                    if (edittextChangedListener != null) {
                        edittextChangedListener.getTowerView(singleTextView);
                    }

                    break;
                case TableMultipleItem.TABLE_CELL://单元号单选框
                    helper.setText(R.id.reason_of_abcence_title_tv, dataBeanObject.getTitle());
                    setViewValue(dataBeanObject, singleTextView);
                    if (edittextChangedListener != null) {
                        edittextChangedListener.getCellView(singleTextView);
                    }

                    break;
                case TableMultipleItem.TABLE_ROOM://房间号单选框
                    helper.setText(R.id.reason_of_abcence_title_tv, dataBeanObject.getTitle());
                    setViewValue(dataBeanObject, singleTextView);
                    if (edittextChangedListener != null) {
                        edittextChangedListener.getRoomView(singleTextView);
                    }

                    break;
                default:
                    break;
            }

        }


    }

    /**
     * edittext 控件各种监听事件
     *
     * @param singleEdittext
     * @param dynamicLayoutBean
     */
    private void addEditListener(final EditText singleEdittext, final DynamicLayoutBean dynamicLayoutBean, final int position) {
        singleEdittext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (edittextChangedListener != null) {
                    edittextChangedListener.edittextFocusChangedListen(singleEdittext, hasFocus);
                }
            }
        });
        singleEdittext.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (edittextChangedListener != null) {
                        edittextChangedListener.edittextOnTouchListen(dynamicLayoutBean);
                    }
                }
                return false;
            }

        });

    }

    /**
     * 给相关项赋值
     *
     * @param dataBeanObject
     * @param textView
     */
    private void setViewValue(DynamicLayoutBean dataBeanObject, TextView textView) {
//3.根据赋的值， 重新赋值
        String value = dataBeanObject.getValue();
        Log.d("CreateFormActivity", dataBeanObject.getTitle() + "--" + dataBeanObject.getValue());
        if (StrUtils.isStringValueOk(value)) {
            textView.setText(value);
        } else {
            textView.setText("");
            textView.setHint(dataBeanObject.getFieldesc());
        }
    }
}





