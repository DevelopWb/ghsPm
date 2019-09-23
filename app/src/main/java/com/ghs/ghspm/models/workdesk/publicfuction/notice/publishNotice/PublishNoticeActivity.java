package com.ghs.ghspm.models.workdesk.publicfuction.notice.publishNotice;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.base.network.RequestStatus;
import com.ghs.ghspm.base.promission.CheckPermListener;
import com.ghs.ghspm.models.oss.OssUploadManager;
import com.ghs.ghspm.models.workdesk.publicfuction.notice.NoticeContract;
import com.ghs.ghspm.models.workdesk.publicfuction.notice.NoticePresent;
import com.ghs.ghspm.models.workdesk.publicfuction.notice.publishNotice.selectDeps.SelectSectionActivity;
import com.ghs.ghspm.tools.ActivityResultManager;
import com.ghs.ghspm.tools.FileUtil;
import com.ghs.ghspm.tools.ImageUtil;
import com.ghs.ghspm.tools.MaterialAlterDialogManager;
import com.ghs.ghspm.tools.PubUtil;
import com.ghs.ghspm.tools.RxScheduler;
import com.ghs.ghspm.tools.StrUtils;
import com.ghs.ghspm.tools.UserInfoUtil;

import java.util.ArrayList;
import java.util.List;

import cn.com.mark.multiimage.core.ImageMainActivity;

/**
 * created by  @tobato
 * created date 2018/11/8 9:57.
 * application
 */
public class PublishNoticeActivity extends BaseActivity<NoticeContract.INoticeView, NoticePresent> implements View.OnClickListener, NoticeContract.INoticeView, RequestStatus,View.OnFocusChangeListener {

    /**
     * 请输入标题
     */
    private EditText mPublishNoticeTitleEt;
    /**
     * 反馈内容...
     */
    private EditText mPublishNoticeContentEt;
    private RecyclerView mPublishNoticeRv;
    /**
     * 选择部门
     */
    private TextView mPublishNoticeDepartmentTv;
    private LinearLayout mPublishNoticeSelectDepartmentLl;
    /**
     * 确定
     */
    private TextView mPublishNoticeConfirmTv;
    private ShowSelectedPicsAdapter adapter;
    private List<String> icons = new ArrayList<>();
    private ImageView imageView;
    private BottomSheetDialog bottomSheetDialog;
    private String selectedDeps = "";
    private OssUploadManager ossManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void getDate() {
        icons.add("-1");
        adapter.setNewData(icons);
        ossManager = OssUploadManager.getInstance(this);
    }

    @Override
    public void initLayoutView() {
        initView();
        initActionBar("发送通知", null);
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_publish_notice);

    }

    @Override
    public void actionBarRightTvOnClick() {

    }

    @Override
    public NoticePresent creatPresenter() {
        return new NoticePresent();
    }

    private void initView() {
        mPublishNoticeTitleEt = (EditText) findViewById(R.id.publish_notice_title_et);
        mPublishNoticeContentEt = (EditText) findViewById(R.id.publish_notice_content_et);
        mPublishNoticeContentEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = editable.toString();
                if (text.length() > 290) {
                    showNormalToast("已超过最大字数限制");
                }
            }
        });
        mPublishNoticeRv = (RecyclerView) findViewById(R.id.publish_notice_rv);
        adapter = new ShowSelectedPicsAdapter(R.layout.show_selected_pic_item);
        GridLayoutManager managere = new GridLayoutManager(this, 4);
        mPublishNoticeRv.setLayoutManager(managere);
        mPublishNoticeRv.setAdapter(adapter);
        mPublishNoticeDepartmentTv = (TextView) findViewById(R.id.publish_notice_department_tv);
        mPublishNoticeSelectDepartmentLl = (LinearLayout) findViewById(R.id.publish_notice_select_department_ll);
        mPublishNoticeSelectDepartmentLl.setOnClickListener(this);
        mPublishNoticeConfirmTv = (TextView) findViewById(R.id.publish_notice_confirm_tv);
        mPublishNoticeConfirmTv.setOnClickListener(this);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                imageView = (ImageView) adapter.getViewByPosition(mPublishNoticeRv, position, R.id.mine_sugguest_icon_iv);
                List<String> arrays = reSortIconList();
                String icon_path = arrays.get(position);
                switch (view.getId()) {
                    case R.id.mine_sugguest_icon_iv:
                        if ("-1".equals(icon_path)) {
                            View bottomView = LayoutInflater.from(PublishNoticeActivity.this).inflate(R.layout.select_pic_menue, null);
                            bottomSheetDialog = new BottomSheetDialog(PublishNoticeActivity.this);
                            bottomSheetDialog.setCancelable(true);
                            bottomSheetDialog.setContentView(bottomView);
                            bottomSheetDialog.show();
                            bottomView.findViewById(R.id.mine_edit_cancel_pic_tv).setOnClickListener(PublishNoticeActivity.this);
                            bottomView.findViewById(R.id.mine_edit_take_pic_tv).setOnClickListener(PublishNoticeActivity.this);
                            bottomView.findViewById(R.id.mine_edit_select_pic_tv).setOnClickListener(PublishNoticeActivity.this);
                            bottomView.findViewById(R.id.mine_edit_title_tv).setVisibility(View.GONE);
                        }
                        break;
                    case R.id.mine_sugguest_delete_iv:
                        arrays.remove(position);
                        icons.clear();
                        if (arrays.size() < 7) {
                            if (!arrays.contains("-1")) {
                                arrays.add("-1");
                            }
                        }
                        icons = arrays;
                        adapter.setNewData(arrays);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    /**
     * 对icons集合处理
     *
     * @return
     */
    private List<String> reSortIconList() {
        List<String> icons_new = new ArrayList<>();
        for (String icon : icons) {
            if (!"-1".equals(icon)) {
                icons_new.add(icon);
            }
        }
        if (icons.size() < 7) {
            icons_new.add("-1");
        }
        return icons_new;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == ActivityResultManager.TAKE_PICTURE) {
                Bitmap bitmap = ImageUtil.readPictureDegreeToForwordBitmap(FileUtil.getHeadPicRootPath(this));
                String userHeadPic = ImageUtil.saveCroppedImage(FileUtil.getHeadPicDir(this), bitmap);
                icons.add(userHeadPic);
                adapter.setNewData(reSortIconList());

            } else if (requestCode == ActivityResultManager.SYSTEM_PICTURE) {
                String imagePath = "";
                ArrayList<Uri> images = data.getParcelableArrayListExtra("result");
                for (int i = 0; i < images.size(); i++) {
                    imagePath = images.get(i).getPath();
                    icons.add(imagePath);
//                    Bitmap bitmap = ImageUtil.readPictureDegreeToForwordBitmap(imagePath);
//                    String userHeadPic = ImageUtil.saveCroppedImage(FileUtil.getHeadPicDir(PublishNoticeActivity.this), bitmap);
//                    icons.add(userHeadPic);
                }
                adapter.setNewData(reSortIconList());

            }
        } else if (resultCode == ActivityResultManager.PUBLISH_NOTICE_SELECT_DEP) {
            if (data != null) {
                selectedDeps = data.getStringExtra(ActivityResultManager.SELECTED_DEPS_ID);
                String name = data.getStringExtra(ActivityResultManager.SELECTED_DEPS_NAME);
                mPublishNoticeDepartmentTv.setText(name);
            }
        }


    }

    @Override
    public void onBackPressed() {
        final String title = mPublishNoticeTitleEt.getText().toString().trim();
        final String content = mPublishNoticeContentEt.getText().toString().trim();

        if (StrUtils.isStringValueOk(content) || StrUtils.isStringValueOk(title)
                || StrUtils.isStringValueOk(selectedDeps) || icons.size() > 1
                ) {
            MaterialAlterDialogManager.getInstance().showMaterialAlterDialog(this, "提示", "您有未发送的通知，是否放弃？", "是", "否", new MaterialAlterDialogManager.OnAlterDialogCallBack() {
                @Override
                public void leftBtClicked() {
                    setResult(ActivityResultManager.PULISH_NOTICE);
                    finish();
                }

                @Override
                public void rightBtClicked() {

                }
            });

        } else {
            setResult(ActivityResultManager.PULISH_NOTICE);
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.publish_notice_select_department_ll:
                startActivityForResult(new Intent(this, SelectSectionActivity.class), ActivityResultManager.PUBLISH_NOTICE_SELECT_DEP);
                break;
            case R.id.publish_notice_confirm_tv://发布通知
                    final String title = mPublishNoticeTitleEt.getText().toString().trim();
                    if (!StrUtils.isStringValueOk(title)) {
                        showNormalToast("请输入标题");
                        return;
                    }
                    final String content = mPublishNoticeContentEt.getText().toString().trim();
                    if (!StrUtils.isStringValueOk(content)) {
                        showNormalToast("请输入反馈内容");
                        return;
                    }
                    if (!StrUtils.isStringValueOk(selectedDeps)) {
                        showNormalToast("请选择部门");
                        return;
                    }
                    showMaterialProgressDialog(null, "正在提交，请稍后...");
                    RxScheduler.doOnIoThread(new RxScheduler.IOTask<Void>() {
                        @Override
                        public void doOnIOThread() {
                            String url = ossManager.getPhotoPathOfUploadedToOssServer(PublishNoticeActivity.this,icons);

                            getPresenter().publishNotice(UserInfoUtil.getInstance().getVillageId(), UserInfoUtil.getInstance().getUserId(), title, content, url, selectedDeps, NoticeContract.PULISH_NOTICE);

                        }
                    });
                break;
            case R.id.mine_edit_cancel_pic_tv://替换头像中的取消按钮
                if (bottomSheetDialog.isShowing()) {
                    bottomSheetDialog.dismiss();
                }
                break;
            case R.id.mine_edit_take_pic_tv://替换头像中的拍照按钮
                takePicturesFromActivity(bottomSheetDialog);

                break;
            case R.id.mine_edit_select_pic_tv:////替换头像中的从相册中选取按钮
                checkAppPermissions(new CheckPermListener() {
                    @Override
                    public void agreeAllPermission() {
                        if (bottomSheetDialog != null) {
                            if (bottomSheetDialog.isShowing()) {
                                bottomSheetDialog.dismiss();
                            }
                        }
                        Intent intent = new Intent(PublishNoticeActivity.this, ImageMainActivity.class);
                        intent.putExtra("action-original", true);
                        intent.putExtra("MAX_SEND", 7 - icons.size());
                        startActivityForResult(intent, ActivityResultManager.SYSTEM_PICTURE);
                    }

                    @Override
                    public void selectedAllPermission() {

                    }
                }, R.string.perm_camera_store, PubUtil.promissions[1],PubUtil.promissions[3]);
                break;
        }
    }



    @Override
    public void startLoading(String tag) {

    }

    @Override
    public void stopLoading(String tag) {

    }

    @Override
    public void updateView(Object o, String tag) {
        stopMaterialProgressDialog();
        switch (tag) {
            case NoticeContract.PULISH_NOTICE:
                showNormalToast("发布成功");
                setResult(ActivityResultManager.PULISH_NOTICE);
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onError(String tag) {
        stopMaterialProgressDialog();
        showToast(tag);
    }

    @Override
    public void onStart(String tag) {


    }

    @Override
    public void onSuccess(Object o, String tag) {
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.publish_notice_title_et:
                hideKeyboard(v);
                break;
            case R.id.publish_notice_content_et:
                hideKeyboard(v);

                break;
            default:
                break;
        }
    }
}
