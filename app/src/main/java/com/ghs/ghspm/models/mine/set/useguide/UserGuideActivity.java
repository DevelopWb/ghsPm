package com.ghs.ghspm.models.mine.set.useguide;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.base.BasePresent;
import com.ghs.ghspm.tools.ActivityResultManager;

import java.util.Arrays;

public class UserGuideActivity extends BaseActivity {

    private RecyclerView mUserGuideRv;
    String[] strs = {"任务", "签到", "万能钥匙", "公告", "通讯录", "水电表抄录", "车辆信息", "我的"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void getDate() {

    }

    @Override
    public void initLayoutView() {
        initView();
        initActionBar("使用指南", null);
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_user_guide);

    }

    @Override
    public void actionBarRightTvOnClick() {

    }

    @Override
    public BasePresent creatPresenter() {
        return null;
    }

    private void initView() {
        mUserGuideRv = (RecyclerView) findViewById(R.id.user_guide_rv);
        UserGuideAdapter userGuideAdapter = new UserGuideAdapter(R.layout.user_guide_item);
        initRecyclerview(mUserGuideRv, userGuideAdapter, LinearLayoutManager.VERTICAL, false);
        userGuideAdapter.setNewData(Arrays.asList(strs));
        addDivider(true,mUserGuideRv,true,true);

        userGuideAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String title = (String) adapter.getData().get(position);

                switch (title) {
                    case "任务":
                        userGuidDisplay(ActivityResultManager.USER_GUIDE_TASK);

                        break;
                    case "签到":
                        userGuidDisplay(ActivityResultManager.USER_GUIDE_SIGN);

                        break;
                    case "万能钥匙":
                        userGuidDisplay(ActivityResultManager.USER_GUIDE_KEY);

                        break;
                    case "公告":
                        userGuidDisplay(ActivityResultManager.USER_GUIDE_NOTICE);

                        break;
                    case "通讯录":
                        userGuidDisplay(ActivityResultManager.USER_GUIDE_ORGANIZE);

                        break;
                    case "水电表抄录":
                        userGuidDisplay(ActivityResultManager.USER_GUIDE_WATER_RECORD);

                        break;
                    case "车辆信息":
                        userGuidDisplay(ActivityResultManager.USER_GUIDE_CAR_INFO);

                        break;
                    case "我的":
                        userGuidDisplay(ActivityResultManager.USER_GUIDE_MINE);

                        break;
                    default:
                        break;
                }
            }
        });
    }
}
