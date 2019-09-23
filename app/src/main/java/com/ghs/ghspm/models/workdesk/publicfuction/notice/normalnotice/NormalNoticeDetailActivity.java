package com.ghs.ghspm.models.workdesk.publicfuction.notice.normalnotice;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.base.displayPhotos.DisplayPhotosActivity;
import com.ghs.ghspm.bean.NoticeDetailBean;
import com.ghs.ghspm.models.workdesk.publicfuction.notice.NoticeContract;
import com.ghs.ghspm.models.workdesk.publicfuction.notice.NoticePresent;
import com.ghs.ghspm.tools.ActivityResultManager;
import com.ghs.ghspm.tools.StrUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * created by 8级大的狂风
 * created date 2018/9/29 17:31.
 * application  公告中的通知里面的详情
 */
public class NormalNoticeDetailActivity extends BaseActivity<NoticeContract.INoticeView, NoticePresent> implements NoticeContract.INoticeView {

    /**
     * 通知：南门门禁
     */
    private TextView mNormalNoticeTitleTv;
    /**
     * 光头强
     */
    private TextView mNormalNoticeCreaterTv;
    /**
     * 张三，李四，王五
     */
    private TextView mNormalNoticeIncludePeopleTv;
    /**
     * 9:00
     */
    private TextView mNormalNoticeCreaterTimeTv;
    /**
     * 内容
     */
    private TextView mNormalNoticeContentTv;
    private RecyclerView mNormalNoticeRv;
    private NoticeDetailPicDisplayAdapter adapter;
    private LinearLayout mLl;
    private String imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_normal_notice_detail);

    }

    @Override
    public void initLayoutView() {
        initView();
        initActionBar("通知", null);
    }

    @Override
    public NoticePresent creatPresenter() {
        return new NoticePresent();
    }

    @Override
    public void getDate() {
        int id = getIntent().getIntExtra(ActivityResultManager.NORMAL_NOTICE_ID, 0);
        getPresenter().getNoticeInfo(id, NoticeContract.NOTICE_DETAIL_INFO);
    }

    @Override
    public void actionBarRightTvOnClick() {

    }

    private void initView() {
        mNormalNoticeTitleTv = (TextView) findViewById(R.id.normal_notice_title_tv);
        mNormalNoticeCreaterTv = (TextView) findViewById(R.id.normal_notice_creater_tv);
        mNormalNoticeIncludePeopleTv = (TextView) findViewById(R.id.normal_notice_include_people_tv);
        mNormalNoticeCreaterTimeTv = (TextView) findViewById(R.id.normal_notice_creater_time_tv);
        mNormalNoticeContentTv = (TextView) findViewById(R.id.normal_notice_content_tv);
        mNormalNoticeRv = (RecyclerView) findViewById(R.id.normal_notice_rv);
        adapter = new NoticeDetailPicDisplayAdapter(R.layout.notice_detail_pics_display_item);

        GridLayoutManager managere = new GridLayoutManager(this, 3);
        mNormalNoticeRv.setLayoutManager(managere);
        mNormalNoticeRv.setAdapter(adapter);
        mLl = (LinearLayout) findViewById(R.id.ll);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                new DisplayPhotosActivity().startDisplayPhotosActivity(NormalNoticeDetailActivity.this, imageUrl,position);

            }
        });
    }

    @Override
    public void startLoading(String tag) {

    }

    @Override
    public void stopLoading(String tag) {

    }

    @Override
    public void updateView(Object o, String tag) {
        NoticeDetailBean noticeDetailBean = (NoticeDetailBean) o;
        if (noticeDetailBean != null) {
            NoticeDetailBean.DataBean dataBean = noticeDetailBean.getData();
            if (dataBean != null) {
                imageUrl = dataBean.getImageUrl();
                if (StrUtils.isStringValueOk(imageUrl)) {
                    if (imageUrl.contains(",")) {
                        String[] pics = imageUrl.split(",");
                        adapter.setNewData(Arrays.asList(pics));
                    } else {
                        List<String> arrays = new ArrayList<>();
                        arrays.add(imageUrl);
                        adapter.setNewData(arrays);
                    }

                }
                mNormalNoticeTitleTv.setText(dataBean.getTitle());
                mNormalNoticeCreaterTv.setText("发起人：" + dataBean.getCreateUserName());
                mNormalNoticeIncludePeopleTv.setText("提及：" + dataBean.getDeptNames());
                mNormalNoticeCreaterTimeTv.setText("" + dataBean.getCreateTime());
                mNormalNoticeContentTv.setText("" + dataBean.getContent());
            }
        }
    }

    @Override
    public void onError(String tag) {
        stopMaterialProgressDialog();
        showToast(tag);
    }

    @Override
    public void onBackPressed() {
        setResult(ActivityResultManager.NOEMAL_NOTICE_DETAIL);
        super.onBackPressed();
    }
}
