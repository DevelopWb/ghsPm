package com.ghs.ghspm.base;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.ghs.ghspm.R;

/**
 * Author:wang_sir
 * Time:2018/6/20 21:35
 * Description:This is FragmentAdapter
 */
public class FragmentAdapter extends FragmentPagerAdapter {
    private SparseArray<Fragment> fragments;
    private Context context;

    public FragmentAdapter(FragmentManager fm, SparseArray<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    public FragmentAdapter(FragmentManager fm, SparseArray<Fragment> fragments, Context context) {
        super(fm);
        this.fragments = fragments;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    public View getTabView(int position, String[] titles) {
        View v = LayoutInflater.from(context).inflate(R.layout.task_tab_item, null);
        TextView tv = (TextView) v.findViewById(R.id.task_tab_item_tv);
        tv.setText(titles[position]);
        TextView unReadTag = (TextView) v.findViewById(R.id.task_tab_item_unread_tag_tv);
        unReadTag.setVisibility(View.GONE);
//        if (0==position) {
//            unReadTag.setVisibility(View.VISIBLE);
//        }else{
//
//
//        }
        return v;
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }

    /*
     * 重写该方法，取消调用父类该方法
     * 可以避免在viewpager切换，fragment不可见时执行到onDestroyView，可见时又从onCreateView重新加载视图
     * 因为父类的destroyItem方法中会调用detach方法，将fragment与view分离，（detach()->onPause()->onStop()->onDestroyView()）
     * 然后在instantiateItem方法中又调用attach方法，此方法里判断如果fragment与view分离了，
     * 那就重新执行onCreateView，再次将view与fragment绑定（attach()->onCreateView()->onActivityCreated()->onStart()->onResume()）
     * */
}
