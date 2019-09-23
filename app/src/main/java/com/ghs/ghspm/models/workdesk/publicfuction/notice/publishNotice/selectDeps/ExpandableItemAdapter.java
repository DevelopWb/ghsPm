package com.ghs.ghspm.models.workdesk.publicfuction.notice.publishNotice.selectDeps;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.ghs.ghspm.R;
import com.ghs.ghspm.bean.section.SectionBeanNew;
import com.ghs.ghspm.bean.section.SectionFour;
import com.ghs.ghspm.bean.section.SectionOne;
import com.ghs.ghspm.bean.section.SectionThree;
import com.ghs.ghspm.bean.section.SectionTwo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luoxw on 2016/8/9.
 */
public class ExpandableItemAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    public static final int TYPE_LEVEL_0 = 0;
    public static final int TYPE_LEVEL_1 = 1;
    public static final int TYPE_LEVEL_2 = 2;
    public static final int TYPE_LEVEL_3 = 3;
    public static final int TYPE_LEVEL_4 = 4;
    private static final String TAG = ExpandableItemAdapter.class.getSimpleName();
    private List<Integer> level1s = new ArrayList<>();
    private List<Integer> level2s = new ArrayList<>();
    private List<Integer> level3s = new ArrayList<>();
    private List<Integer> level4s = new ArrayList<>();
    private List<Integer> level5s = new ArrayList<>();

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ExpandableItemAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(TYPE_LEVEL_0, R.layout.item_expandable_0);
        addItemType(TYPE_LEVEL_1, R.layout.item_expandable_1);
        addItemType(TYPE_LEVEL_2, R.layout.item_expandable_2);
        addItemType(TYPE_LEVEL_3, R.layout.item_expandable_3);
        addItemType(TYPE_LEVEL_4, R.layout.item_expandable);
    }

    /**
     * 设置人数
     * @param sectionBean
     * @return
     */
    private void  setUserNum( BaseViewHolder holder,SectionBeanNew sectionBean) {
        String   num =  sectionBean.getUserNum() > 0 ? sectionBean.getUserNum()+"人" : "0人";
        holder.setText(R.id.expand_list_section_name_tv, sectionBean.getRoleName() + " ("+ num+")");
    }

    @Override
    protected void convert(final BaseViewHolder holder, final MultiItemEntity item) {
        switch (holder.getItemViewType()) {
            case TYPE_LEVEL_0:
                final SectionOne sectionOne = (SectionOne) item;
                final SectionBeanNew sectionBean = sectionOne.getSectionBean();
                setUserNum(holder,sectionBean);
                if (sectionOne.getSubItems() != null && sectionOne.getSubItems().size() > 0) {
                    holder.getView(R.id.expand_list_arrow_iv).setVisibility(View.VISIBLE);
                    holder.setImageResource(R.id.expand_list_arrow_iv, sectionOne.isExpanded() ? R.mipmap.arror_down : R.mipmap.arror_right);

                } else {
                    holder.getView(R.id.expand_list_arrow_iv).setVisibility(View.INVISIBLE);
                }
                final CheckBox checkBox1 = holder.getView(R.id.expand_list_cb1);
                holder.addOnClickListener(R.id.expand_list_cb1);
                initCheckboxStatus(sectionBean, checkBox1);

                //checkbox点击事件
//                final CheckBox checkBox1 = holder.getView(R.id.expand_list_cb1);
//                if (checkBox1.isChecked()) {
//                    checkBox1.setChecked(false);
//                } else {
//                    checkBox1.setChecked(true);
//                }
//                checkBox1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        if (checkBox1.isChecked()) {
//                            checkBox1.setChecked(false);
//                            level1s.remove(sectionBean.getParentId());
//                        } else {
//                            level1s.add(sectionBean.getParentId());
//
//                            checkBox1.setChecked(true);
//                        }
//                        notifyDataSetChanged();
//                    }
//                });
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int pos = holder.getAdapterPosition();
                        Log.d(TAG, "Level 0 item pos: " + pos);
                        if (sectionOne.isExpanded()) {
                            collapse(pos);
                        } else {
                            expand(pos);
//                            }
                        }
                    }
                });
                break;
            case TYPE_LEVEL_1:
                final SectionTwo sectionTwo = (SectionTwo) item;
                final SectionBeanNew sectionBean2 = sectionTwo.getSectionBean();
                setUserNum(holder,sectionBean2);

                if (sectionTwo.getSubItems() != null && sectionTwo.getSubItems().size() > 0) {
                    holder.getView(R.id.expand_list_arrow_iv).setVisibility(View.VISIBLE);
                    holder.setImageResource(R.id.expand_list_arrow_iv, sectionTwo.isExpanded() ? R.mipmap.arror_down : R.mipmap.arror_right);

                } else {
                    holder.getView(R.id.expand_list_arrow_iv).setVisibility(View.INVISIBLE);
                }

                final CheckBox checkBox = holder.getView(R.id.expand_list_cb);
                holder.addOnClickListener(R.id.expand_list_cb);

                initCheckboxStatus(sectionBean2, checkBox);
//                //checkbox点击事件
//                final CheckBox checkBox = holder.getView(R.id.expand_list_cb);
////                if (checkBox.isChecked()) {
////                    checkBox.setChecked(false);
////                }else {
////                    checkBox.setChecked(true);
////                }
//                if (level1s.contains(sectionBean2.getParentId())) {
//                    checkBox.setChecked(true);
//                } else {
//                    checkBox.setChecked(false);
//                }
//
//                checkBox.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        if (checkBox.isChecked()) {
//                            checkBox.setChecked(false);
//                            level2s.remove(sectionBean2.getParentId());
//
//                        } else {
//                            checkBox.setChecked(true);
//                            level2s.add(sectionBean2.getParentId());
//
//
//                        }
//                        notifyDataSetChanged();
//                    }
//                });
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int pos = holder.getAdapterPosition();
                        Log.d(TAG, "Level 0 item pos: " + pos);
                        if (sectionTwo.isExpanded()) {
                            collapse(pos);
                        } else {
                            expand(pos);
//                            }
                        }
                    }
                });
                break;
            case TYPE_LEVEL_2:
                final SectionThree sectionThree = (SectionThree) item;
                final SectionBeanNew sectionBean3 = sectionThree.getSectionBean();
                setUserNum(holder,sectionBean3);

                if (sectionThree.getSubItems() != null && sectionThree.getSubItems().size() > 0) {
                    holder.getView(R.id.expand_list_arrow_iv).setVisibility(View.VISIBLE);
                    holder.setImageResource(R.id.expand_list_arrow_iv, sectionThree.isExpanded() ? R.mipmap.arror_down : R.mipmap.arror_right);

                } else {
                    holder.getView(R.id.expand_list_arrow_iv).setVisibility(View.INVISIBLE);
                }
                final CheckBox checkBox3 = holder.getView(R.id.expand_list_cb);
                holder.addOnClickListener(R.id.expand_list_cb);

                initCheckboxStatus(sectionBean3, checkBox3);

//                //checkbox点击事件
//                //checkbox点击事件
//                final CheckBox checkBox3 = holder.getView(R.id.expand_list_cb);
////                if (checkBox3.isChecked()) {
////                    checkBox3.setChecked(false);
////                }else {
////                    checkBox3.setChecked(true);
////                }
//                if (level2s.contains(sectionBean3.getParentId())) {
//                    checkBox3.setChecked(true);
//                } else {
//                    checkBox3.setChecked(false);
//                }
//                checkBox3.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        if (checkBox3.isChecked()) {
//                            checkBox3.setChecked(false);
//                            level3s.remove(sectionBean3.getParentId());
//                        } else {
//                            level3s.add(sectionBean3.getParentId());
//
//                            checkBox3.setChecked(true);
//
//
//                        }
//                        notifyDataSetChanged();
//                    }
//                });
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int pos = holder.getAdapterPosition();
                        Log.d(TAG, "Level 0 item pos: " + pos);
                        if (sectionThree.isExpanded()) {
                            collapse(pos);
                        } else {
                            expand(pos);
//                            }
                        }
                    }
                });
                break;
            case TYPE_LEVEL_3:
                final SectionFour sectionFour = (SectionFour) item;
                SectionBeanNew sectionBean4 = sectionFour.getSectionBean();
                setUserNum(holder,sectionBean4);

                if (sectionFour.getSubItems() != null && sectionFour.getSubItems().size() > 0) {
                    holder.getView(R.id.expand_list_arrow_iv).setVisibility(View.VISIBLE);
                    holder.setImageResource(R.id.expand_list_arrow_iv, sectionFour.isExpanded() ? R.mipmap.arror_down : R.mipmap.arror_right);

                } else {
                    holder.getView(R.id.expand_list_arrow_iv).setVisibility(View.INVISIBLE);
                }
                final CheckBox checkBox4 = holder.getView(R.id.expand_list_cb);
                holder.addOnClickListener(R.id.expand_list_cb);

                initCheckboxStatus(sectionBean4, checkBox4);

//                //checkbox点击事件
//                final CheckBox checkBox4 = holder.getView(R.id.expand_list_cb);
//                if (level3s.contains(sectionBean4.getParentId())) {
//                    checkBox4.setChecked(true);
//                } else {
//                    checkBox4.setChecked(false);
//                }
////                if (checkBox4.isChecked()) {
////                    checkBox4.setChecked(false);
////                }else {
////                    checkBox4.setChecked(true);
////                }
//                checkBox4.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        if (checkBox4.isChecked()) {
//                            checkBox4.setChecked(false);
//                        } else {
//                            checkBox4.setChecked(true);
//                        }
//                        notifyDataSetChanged();
//                    }
//                });
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int pos = holder.getAdapterPosition();
                        Log.d(TAG, "Level 0 item pos: " + pos);
                        if (sectionFour.isExpanded()) {
                            collapse(pos);
                        } else {
                            expand(pos);
//                            }
                        }
                    }
                });
                break;
            case TYPE_LEVEL_4:
                break;
        }
    }

    /**
     * 初始化checkbox状态
     *
     * @param sectionBean
     * @param checkBox
     */
    private void initCheckboxStatus(SectionBeanNew sectionBean, CheckBox checkBox) {

        boolean selected = sectionBean.isSelected();
        if (selected) {
            checkBox.setChecked(true);
        } else {
            checkBox.setChecked(false);
        }
    }
}
