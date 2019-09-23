package com.ghs.ghspm.models.workdesk.ownerinfo.owneradapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ghs.ghspm.R;
import com.ghs.ghspm.bean.OwnerMessageBean;
import com.ghs.ghspm.models.workdesk.ownerinfo.OwnerInfoContract;
import com.ghs.ghspm.tools.Contract;
import com.ghs.ghspm.tools.glide.GlideCircleTransform;
import com.ghs.ghspm.tools.StrUtils;

import java.util.List;

public class OwnerRightAdapter extends RecyclerView.Adapter<OwnerRightAdapter.ViewHolder> {

    private Context context;
    List<OwnerMessageBean.DataBean> ownerList;
    private String tag;

    public void setOwnerList(List<OwnerMessageBean.DataBean> ownerList, String tag) {
        this.ownerList = ownerList;
        this.tag = tag;
        this.notifyDataSetChanged();
    }

    public OwnerRightAdapter(Context context) {
        this.context = context;

    }

    @Override
    public OwnerRightAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewLayout = null;
        Log.i("TTT", tag);
        if (isMessage(ownerList)) {
            switch (tag) {
                case OwnerInfoContract.OWNER_USER:
                    boolean message = isMessage(ownerList);
                    viewLayout = LayoutInflater.from(context).inflate(R.layout.owner_user_layout, parent, false);

                    break;
                case OwnerInfoContract.OWNER_HOME:
                    viewLayout = LayoutInflater.from(context).inflate(R.layout.ownerright_item_layout, parent, false);
                    break;
                case OwnerInfoContract.OWNER_TENANT:

                    viewLayout = LayoutInflater.from(context).inflate(R.layout.teant_item_layout, parent, false);

                    break;
                //车辆信息
                case OwnerInfoContract.OWNER_CAR:

                    viewLayout = LayoutInflater.from(context).inflate(R.layout.owner_car_layout, parent, false);

                    break;

                case OwnerInfoContract.OWNER_EMPTY:
                    Log.i("TTT", "加载的空布局");
                    viewLayout = LayoutInflater.from(context).inflate(R.layout.empty_view, parent, false);


                    break;

            }
        }

        return new OwnerRightAdapter.ViewHolder(viewLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        OwnerMessageBean.DataBean dataBean = ownerList.get(position);
        switch (tag) {
            case OwnerInfoContract.OWNER_USER:

                if (dataBean != null) {
                    String fullName = dataBean.getFullName();
                    String picPath = dataBean.getHeadImage();
                    Glide.with(context).load(Contract.ImageBasePath + picPath)
                            .skipMemoryCache(false)
                            // 加载图片
//                        .error(errorimg)
//                        // 设置错误图片
//                        .crossFade()
//                        // 设置淡入淡出效果，默认300ms，可以传参
                            .placeholder(R.mipmap.default_user_head_icon)
                            // 设置占位图
                            .transform(new GlideCircleTransform(context))//圆角
                            .into(holder.owner_user_icon);
                    holder.owner_user_name.setText(fullName);

                    boolean b = StrUtils.isStringValueOk(dataBean.getAge());
                    if (b) {
                        holder.owner_user_age.setText(dataBean.getAge()+"岁");
                    }
                    holder.owner_user_mobile.setText(dataBean.getMobile());
                    holder.owner_user_house.setText(dataBean.getRoomSize());
                    holder.owner_user_address.setText(dataBean.getAddress());


                }

                break;
            case OwnerInfoContract.OWNER_HOME:
                OwnerMessageBean.DataBean x = ownerList.get(position);
                if (x != null) {
                    String picPath1 = x.getHeadImage();
                    Glide.with(context).load(Contract.ImageBasePath + picPath1)
                            .skipMemoryCache(false)
                            // 加载图片
//                        .error(errorimg)
//                        // 设置错误图片
//                        .crossFade()
//                        // 设置淡入淡出效果，默认300ms，可以传参
                            .placeholder(R.mipmap.default_user_head_icon)
                            // 设置占位图
                            .transform(new GlideCircleTransform(context))//圆角
                            .into(holder.ownerInfoRight_item_icon);
                    holder.ownerInfoRight_item_name.setText(x.getFullName());
                    boolean b = StrUtils.isStringValueOk(x.getAge());
                    if (b) {
                        holder.ownerInfoRight_item_age.setText(x.getAge()+"岁");
                    }

                    holder.ownerInfoRight_item_address.setText(x.getAddress());
                }

                break;
            case OwnerInfoContract.OWNER_TENANT:


                if (dataBean != null) {

                    OwnerMessageBean.DataBean dataBean1 = ownerList.get(position);
                    String picPath = dataBean1.getHeadImage();
                    Glide.with(context).load(Contract.ImageBasePath + picPath)
                            .skipMemoryCache(false)
                            // 加载图片
//                        .error(errorimg)
//                        // 设置错误图片
//                        .crossFade()
//                        // 设置淡入淡出效果，默认300ms，可以传参
                            .placeholder(R.mipmap.default_user_head_icon)
                            // 设置占位图
                            .transform(new GlideCircleTransform(context))//圆角
                            .into(holder.teant_item_icon);
                    holder.teant_item_name.setText(dataBean1.getFullName());
                    boolean b = StrUtils.isStringValueOk(dataBean1.getAge());
                    if (b) {
                        holder.teant_item_age.setText(dataBean1.getAge()+"岁");
                    }
                    holder.teant_item_address.setText(dataBean1.getAddress());


                }


                break;
            //车辆信息
            case OwnerInfoContract.OWNER_CAR:

                holder.owner_car_pai.setText(null);
                holder.owner_car_color.setText(null);
                holder.owner_car_number.setText(null);
                holder.owner_car_type.setText(null);
                holder.owner_car_year.setText(null);

                break;
            case OwnerInfoContract.OWNER_EMPTY:

                holder.empty_view_tv.setText("暂时没有内容！");

                break;


        }

    }


    @Override
    public int getItemCount() {
        return ownerList != null ? ownerList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //业主
        private ImageView owner_user_icon;
        private TextView owner_user_name;
        private TextView owner_user_age;
        private TextView owner_user_mobile;
        private TextView owner_user_house;
        private TextView owner_user_address;
        //家人
        private TextView ownerInfoRight_item_name;
        private TextView ownerInfoRight_item_age;
        private TextView ownerInfoRight_item_relation;
        private TextView ownerInfoRight_item_address;
        private ImageView ownerInfoRight_item_icon;
        //租客
        private ImageView teant_item_icon;
        private TextView teant_item_name;
        private TextView teant_item_age;
        private TextView teant_item_address;
        //车辆信息
        private TextView owner_car_pai;
        private TextView owner_car_type;
        private TextView owner_car_color;
        private TextView owner_car_number;
        private TextView owner_car_year;
        private TextView empty_view_tv;

        public ViewHolder(View itemView) {
            super(itemView);
            if (isMessage(ownerList)) {


                switch (tag) {
                    case OwnerInfoContract.OWNER_USER:
                        owner_user_icon = itemView.findViewById(R.id.owner_user_icon);
                        owner_user_name = itemView.findViewById(R.id.owner_user_name);
                        owner_user_age = itemView.findViewById(R.id.owner_user_age);
                        owner_user_mobile = itemView.findViewById(R.id.owner_user_mobile);
                        owner_user_house = itemView.findViewById(R.id.owner_user_house);
                        owner_user_address = itemView.findViewById(R.id.owner_user_address);


                        break;
                    case OwnerInfoContract.OWNER_HOME:
                        ownerInfoRight_item_icon = itemView.findViewById(R.id.ownerInfoRight_item_icon);
                        ownerInfoRight_item_name = itemView.findViewById(R.id.ownerInfoRight_item_name);
                        ownerInfoRight_item_age = itemView.findViewById(R.id.ownerInfoRight_item_age);
                        ownerInfoRight_item_relation = itemView.findViewById(R.id.ownerInfoRight_item_relation);
                        ownerInfoRight_item_address = itemView.findViewById(R.id.ownerInfoRight_item_address);


                        break;
                    case OwnerInfoContract.OWNER_TENANT:

                        teant_item_icon = itemView.findViewById(R.id.teant_item_icon);
                        teant_item_name = itemView.findViewById(R.id.teant_item_name);
                        teant_item_age = itemView.findViewById(R.id.teant_item_age);
                        teant_item_address = itemView.findViewById(R.id.teant_item_address);


                        break;
                    //车辆信息
                    case OwnerInfoContract.OWNER_CAR:
                        owner_car_pai = itemView.findViewById(R.id.owner_car_pai);
                        owner_car_type = itemView.findViewById(R.id.owner_car_type);
                        owner_car_color = itemView.findViewById(R.id.owner_car_color);
                        owner_car_number = itemView.findViewById(R.id.owner_car_number);
                        owner_car_year = itemView.findViewById(R.id.owner_car_year);

                        break;
                    case OwnerInfoContract.OWNER_EMPTY:
                        empty_view_tv = itemView.findViewById(R.id.empty_view_tv);

                        break;


                }
            }

        }
    }

    public boolean isMessage(List<OwnerMessageBean.DataBean> ownerList) {
        if (ownerList != null) {
            if (ownerList.size() == 0) {
                Log.i("TTT", "集合为空");
                return false;
            } else {

                return true;
            }

        }
        return false;

    }
}

