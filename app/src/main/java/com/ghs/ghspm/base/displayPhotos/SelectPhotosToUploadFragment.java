package com.ghs.ghspm.base.displayPhotos;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.base.network.RequestStatus;
import com.ghs.ghspm.base.promission.CheckPermListener;
import com.ghs.ghspm.models.oss.OssUploadManager;
import com.ghs.ghspm.models.workdesk.publicfuction.notice.publishNotice.ShowSelectedPicsAdapter;
import com.ghs.ghspm.tools.ActivityResultManager;
import com.ghs.ghspm.tools.Contract;
import com.ghs.ghspm.tools.FileUtil;
import com.ghs.ghspm.tools.ImageUtil;
import com.ghs.ghspm.tools.PubUtil;
import com.ghs.ghspm.tools.StrUtils;

import java.util.ArrayList;
import java.util.List;

import cn.com.mark.multiimage.core.ImageMainActivity;

/**
 * Author:wang_sir
 * Time:2018/11/1 14:13
 * Description:This is SelectPhotosToUploadFragment
 * 仿微信选择图片并上传到oss上
 */
public class SelectPhotosToUploadFragment extends Fragment implements View.OnClickListener, RequestStatus {

    private View view;
    /**
     * 请输入内容...
     */
    private EditText mSelectPhotosContentEt;
    private RecyclerView mSelectPhotosRv;
    private List<String> icons = new ArrayList<>();
    private ShowSelectedPicsAdapter selectedPicsAdapter;
    private BottomSheetDialog bottomSheetDialog;
    private Context mContext;
    private int mSpanCount = 0;//一行的个数，默认0
    private int mMaxCount = 10;//最大个数，默认9个
    private int horSpace = 15;//图片之间的横向间距 默认15
    private int marginLeftParents = 20;//图片距离左边父窗体的距离 dp

    private boolean hindEditText = false;//如果绑定在TableTaskDetailActivity中
    private LinearLayout mSelectPhotosRootLl;
    private OssUploadManager ossManager;
    private List<String> imageUrlForNet = new ArrayList<>();


    /**
     * @param spanCount         一行的个数，默认0
     * @param maxCount          //最大个数，默认9个
     * @param marginLeftParents //图片距离左边父窗体的距离 dp  以距离左边父窗口和距离右边父窗口相同为标准
     * @param hindEditText
     * @return
     */
    public SelectPhotosToUploadFragment setSpanCount(int spanCount, int maxCount, int marginLeftParents, boolean hindEditText) {
        this.hindEditText = hindEditText;
        this.marginLeftParents = marginLeftParents;
        if (hindEditText) {
            mSelectPhotosContentEt.setVisibility(View.GONE);
            mSelectPhotosRootLl.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.app_white));
        } else {
            mSelectPhotosContentEt.setVisibility(View.VISIBLE);
            mSelectPhotosRootLl.setBackgroundResource(R.drawable.rv_white_shadow_shape);
        }
        this.mSpanCount = spanCount;
        this.mMaxCount = maxCount+1;
        GridLayoutManager managere = new GridLayoutManager(mContext, spanCount);
        mSelectPhotosRv.setLayoutManager(managere);
        mSelectPhotosRv.setAdapter(selectedPicsAdapter);
        selectedPicsAdapter.setWidthAndHeigh(calculateImageHeight());
        return this;
    }

    /**
     * 设置et的最小高度和hint
     *
     * @param minHeight
     * @return
     */
    public SelectPhotosToUploadFragment setEtMinHeightAndHint(int minHeight, String hintStr) {
        mSelectPhotosContentEt.setMinHeight(PubUtil.dip2px(getContext(), minHeight));
        if (StrUtils.isStringValueOk(hintStr)) {
            mSelectPhotosContentEt.setHint(hintStr);
        }
        return this;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mine_edit_cancel_pic_tv://替换头像中的取消按钮
                dismissBottomSheetDialog();

                break;
            case R.id.mine_edit_take_pic_tv://替换头像中的拍照按钮
                ((BaseActivity) getActivity()).takePicturesFromFragment(bottomSheetDialog, this);

                break;
            case R.id.mine_edit_select_pic_tv:////替换头像中的从相册中选取按钮
                ((BaseActivity) getActivity()).checkAppPermissions(new CheckPermListener() {
                    @Override
                    public void agreeAllPermission() {
                        dismissBottomSheetDialog();
                        Intent intent = new Intent(mContext, ImageMainActivity.class);
                        intent.putExtra("action-original", true);
                        intent.putExtra("MAX_SEND", mMaxCount - icons.size());
                        startActivityForResult(intent, ActivityResultManager.SYSTEM_PICTURE);
                    }

                    @Override
                    public void selectedAllPermission() {

                    }
                }, R.string.perm_camera_store, PubUtil.promissions[1], PubUtil.promissions[3]);

                break;
            default:
                break;
        }
    }

    /**
     * 关闭dialog
     */
    private void dismissBottomSheetDialog() {
        if (bottomSheetDialog != null) {
            if (bottomSheetDialog.isShowing()) {
                bottomSheetDialog.dismiss();
            }
        }

    }


    @Override
    public void onStart(String tag) {

    }

    @Override
    public void onSuccess(Object o, String tag) {
    }

    @Override
    public void onError(String tag) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, final Intent data) {
        if (-1 == resultCode) {
            if (requestCode == ActivityResultManager.TAKE_PICTURE) {
                Bitmap bitmap = ImageUtil.readPictureDegreeToForwordBitmap(FileUtil.getHeadPicRootPath(getContext()));
                String userHeadPic = ImageUtil.saveCroppedImage(FileUtil.getHeadPicDir(mContext), bitmap);
                icons.add(userHeadPic);
                selectedPicsAdapter.setNewData(reSortIconList());

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
                selectedPicsAdapter.setNewData(reSortIconList());

            }
        }


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        selectedPicsAdapter = new ShowSelectedPicsAdapter(R.layout.show_selected_pic_item);
        initContentAndIcons();
        ossManager = OssUploadManager.getInstance(mContext);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.select_photo_layout, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mSelectPhotosContentEt = (EditText) view.findViewById(R.id.select_photos_content_et);
        mSelectPhotosRv = (RecyclerView) view.findViewById(R.id.select_photos_rv);
        mSelectPhotosRootLl = (LinearLayout) view.findViewById(R.id.select_photos_root_ll);


        selectedPicsAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {


            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                imageView = (ImageView) selectedPicsAdapter.getViewByPosition(mPublishNoticeRv, position, R.id.mine_sugguest_icon_iv);
                List<String> arrays = reSortIconList();
                String icon_path = arrays.get(position);
                switch (view.getId()) {
                    case R.id.mine_sugguest_icon_iv:

                        if ("-1".equals(icon_path)) {

                            View bottomView = LayoutInflater.from(mContext).inflate(R.layout.select_pic_menue, null);
                            bottomSheetDialog = new BottomSheetDialog(mContext);
                            bottomSheetDialog.setCanceledOnTouchOutside(true);
                            bottomSheetDialog.setContentView(bottomView);
                            bottomSheetDialog.show();
                            bottomView.findViewById(R.id.mine_edit_cancel_pic_tv).setOnClickListener(SelectPhotosToUploadFragment.this);
                            bottomView.findViewById(R.id.mine_edit_take_pic_tv).setOnClickListener(SelectPhotosToUploadFragment.this);
                            bottomView.findViewById(R.id.mine_edit_select_pic_tv).setOnClickListener(SelectPhotosToUploadFragment.this);
                            bottomView.findViewById(R.id.mine_edit_title_tv).setVisibility(View.GONE);

                        }
                        break;
                    case R.id.mine_sugguest_delete_iv:
                        arrays.remove(position);
                        if (imageUrlForNet != null) {
                            if (imageUrlForNet.size()>0) {
                                imageUrlForNet.remove(position);
                            }
                        }
                        icons.clear();
                        if (arrays.size() < mMaxCount) {
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
        if (icons.size() < mMaxCount) {
            icons_new.add("-1");
        }
        return icons_new;
    }

    /**
     * 计算图片的宽高
     */

    private int calculateImageHeight() {
        //横向所有的间距
        int spaces = PubUtil.dip2px(mContext, horSpace) * (mSpanCount - 1);
        //左右间距
        int marginPresent = PubUtil.dip2px(mContext, marginLeftParents) * 2;
        return PubUtil.px2dip(mContext, (PubUtil.ScreenWidth - spaces - marginPresent) / mSpanCount);
    }

    /**
     * 获取上传到oss上图片的路径，多个图片用逗号分隔()
     * <p>
     * (最好在子线程中调用次方法，比较耗时)
     *
     * @return
     */
    public String uploadPhotosToOssForPath() {
        return ossManager.getPhotoPathOfUploadedToOssServer(mContext, icons);
    }


    /**
     * 是否有选择的照片
     *
     * @return
     */
    public boolean hasSelectedPhotos() {
        return icons.size() > 1;
    }

    /**
     * 清空数据
     */
    public void initContentAndIcons() {
        icons.clear();
        icons.add("-1");
        selectedPicsAdapter.setNewData(icons);
        if (mSelectPhotosContentEt != null) {
            mSelectPhotosContentEt.setText("");
        }
    }

    /**
     * 获取内容数据
     */
    public String getContent() {
        return mSelectPhotosContentEt.getText().toString().trim();
    }

    /**
     * 设置内容数据
     *
     * @param content
     */
    public void setContent(String content) {
        mSelectPhotosContentEt.setText(content);

    }

    public String getNetWorkImage() {
        StringBuffer stringBuffer = new StringBuffer();
        if(imageUrlForNet.size()>0){
            for (String str : imageUrlForNet) {
                stringBuffer.append(str + ",");
            }
        }

        return stringBuffer.toString();
    }

    /**
     * 设置适配器数据  传入网络图片路径的集合
     */
    public void setAdapterData(String images) {
        if (StrUtils.isStringValueOk(images)) {
            if (images.contains(",")) {
                String[] arrs = images.split(",");
                for (String arr : arrs) {
                    imageUrlForNet.add(arr);
                    icons.add(Contract.ImageBasePath + arr);
                }
            } else {
                icons.add(Contract.ImageBasePath + images);
                imageUrlForNet.add(images);
            }
            selectedPicsAdapter.setNewData(reSortIconList());
        }
    }

    /**
     * 设置背景
     * @param bgRec
     */
    public void setBackGround(int  bgRec){
        mSelectPhotosRootLl.setBackgroundResource(bgRec);
    }

}
