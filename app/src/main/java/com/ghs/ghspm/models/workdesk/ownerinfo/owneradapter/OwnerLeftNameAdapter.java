package com.ghs.ghspm.models.workdesk.ownerinfo.owneradapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ghs.ghspm.R;
import com.ghs.ghspm.models.workdesk.ownerinfo.OwnerInfoContract;

import java.util.List;

public class OwnerLeftNameAdapter extends RecyclerView.Adapter<OwnerLeftNameAdapter.ViewHolder> {

    private Context context;
    private List<String> data;
    private int hight ;

    public void setData(List<String> data) {
        this.data = data;
    }
    public OwnerInfoContract.LeftOnClickListenter leftOnClickListenter;

    public void setLeftOnClickListenter(OwnerInfoContract.LeftOnClickListenter leftOnClickListenter) {
        this.leftOnClickListenter = leftOnClickListenter;
    }
    private int thisPosition;
    //再定义一个int类型的返回值方法
    public int getthisPosition() {
        return thisPosition;
    }

    //其次定义一个方法用来绑定当前参数值的方法
    //此方法是在调用此适配器的地方调用的，此适配器内不会被调用到
    public void setThisPosition(int thisPosition) {
        this.thisPosition = thisPosition;
    }


    public OwnerLeftNameAdapter(Context context,int hight) {
        this.context = context;
        this.hight = hight;

    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ownerleft_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        ViewGroup.LayoutParams layoutParams = holder.left_item_layout.getLayoutParams();
        layoutParams.height = hight/8;
        holder.left_item_layout.setLayoutParams(layoutParams);
        holder.ownerLeft_itme_name.setText(data.get(position));

        if (position == getthisPosition()) {
            holder.itemView.setBackground(ContextCompat.getDrawable(context,R.color.owner_list_backgroud_blue));
            holder.ownerLeft_itme_name.setTextColor(Color.WHITE);
        } else {
            holder.itemView.setBackgroundColor(Color.WHITE);
            holder.ownerLeft_itme_name.setTextColor(Color.BLACK);

        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Log.e("这里是点击每一行item的响应事件", "" + position);
                leftOnClickListenter.OnClick(v,position);

            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        private final TextView ownerLeft_itme_name;
        private final LinearLayout left_item_layout;
        public ViewHolder(View itemView) {
            super(itemView);
            ownerLeft_itme_name = itemView.findViewById(R.id.ownerLeft_itme_name);
             left_item_layout = itemView.findViewById(R.id.left_item_layout);
        }
    }
}

