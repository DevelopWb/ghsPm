package com.ghs.ghspm.models.workdesk.patrolForm.creatPatrolForm;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.base.displayPhotos.DisplayPhotosActivity;
import com.ghs.ghspm.bean.AutoFormBean;
import com.ghs.ghspm.bean.PatrolFormTaskBean;
import com.ghs.ghspm.bean.PatrolTaskFormListBean;
import com.ghs.ghspm.bean.TableTaskDetailBean;
import com.ghs.ghspm.customView.CustomLoadMoreView;
import com.ghs.ghspm.customView.SyncHorizontalScrollView;
import com.ghs.ghspm.models.workdesk.patrolForm.PatrolCheckUpdateContract;
import com.ghs.ghspm.models.workdesk.patrolForm.PatrolCheckUpdatePresent;
import com.ghs.ghspm.models.workdesk.patrolForm.formStatusInfo.ModifyPatrolFormInfoActivity;
import com.ghs.ghspm.models.workdesk.patrolForm.formStatusInfo.PatrolFormPreviewActivity;
import com.ghs.ghspm.models.workdesk.signAndTable.tableTaskToDo.createForm.CreateFormActivity;
import com.ghs.ghspm.models.workdesk.signAndTable.tableTaskToDo.formTaskSign.autoForm.LeftMenuAdapter;
import com.ghs.ghspm.models.workdesk.signAndTable.tableTaskToDo.formTaskSign.autoForm.RightPresentAdapter;
import com.ghs.ghspm.tools.ActivityResultManager;
import com.ghs.ghspm.tools.GsonManager;
import com.ghs.ghspm.base.network.NetWorkUtil;
import com.ghs.ghspm.tools.PubUtil;
import com.ghs.ghspm.tools.StrUtils;
import com.ghs.ghspm.tools.UserInfoUtil;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * created by tobato
 * created date 2019/4/25 11:13.
 * application   巡检记录表单的所有信息
 */
public class PatrolTaskFormRecordActivity extends BaseActivity<PatrolCheckUpdateContract.IPatrolCheckUpdateView, PatrolCheckUpdatePresent> implements PatrolCheckUpdateContract.IPatrolCheckUpdateView, View.OnClickListener {
    private RecyclerView mShowTableRecordRv;
    /**
     * 总标题
     */
    private TextView mTvTableTitleLeft;
    private SyncHorizontalScrollView mTitleHorsv;
    private SyncHorizontalScrollView mContentHorsv;
    private NestedScrollView mPullRefreshScroll;
    private RecyclerView mLeftContainerRv;
    private RecyclerView mRightTitleRv;
    private RecyclerView mRightContainerRv;
    private TextView mTableTaskNameTv;
    private TextView mTableTaskFormHeadInfoTv;
    private LeftMenuAdapter leftAdapter;
    private RightTitleStringAdapter rightTitleAdapter;
    private RightPresentAdapter rightContentAdapter;
    private List<AutoFormBean.DataBean> leftMenuData = new ArrayList<>();
    private List<AutoFormBean.DataBean> rightTitleData = new ArrayList<>();
    private List<List<AutoFormBean.DataBean>> rightContent = new ArrayList<>();
    private List<List<AutoFormBean.DataBean>> scrollViewList;//scrollview 滑动一次需要加载的数据
//    private List<List<AutoFormBean.DataBean>> lists;

    private TextView mTableTaskFormTailInfoTv;
    private BottomSheetDialog bottomSheetDialog;
    /**
     * 填写
     */
    private TextView mPatrolFormFillTv;
    /**
     * 修改
     */
    private TextView mPatrolFormModifyTv;
    private int status;//任务状态：1、正填写；2、已上交。
    private PatrolTaskFormListBean.DataBean formHeadTailInfo;
    private int currentRecordId = -1;

    private List<AutoFormBean.DataBean> currentSelectedRightData;//当前选中的右侧的内容
    private List<AutoFormBean.DataBean> preSelectedRightData;//上一个选中的右侧的内容
    private AutoFormBean.DataBean currentSelectedLefttData;//当前选中的左侧的内容
    private AutoFormBean.DataBean preSelectedLefttData;//当前选中的左侧的内容
    private List<List<AutoFormBean.DataBean>> autoDataBeanList;//所有数据 空数据和实体数据
    private int loadLimit = 10;//展示数据时 一次展示的数据
    private int scrollViewLimit = 10;//scrollview 滑动一次需要加载的数据的条数，数据分割
    // 次加载15条数据
    private int scrollOffset = 0;//滑动时加载数据的起点
    private int displayOffset = 0;//展示时的起点
    private int currentSelectedPosition = -1;//当前选中的item的位置
    private ImageView mPatrolFormBackIv;
    private TextView mPatrolFormTitleTv;
    private ImageView mPatrolFormOperateIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public PatrolCheckUpdatePresent creatPresenter() {
        return new PatrolCheckUpdatePresent();
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_creat_patrol_form);

    }

    @Override
    public void initLayoutView() {
        initView();
    }

    private void initView() {
        mTvTableTitleLeft = (TextView) findViewById(R.id.tv_table_title_left);
        mTitleHorsv = (SyncHorizontalScrollView) findViewById(R.id.title_horsv);
        mContentHorsv = (SyncHorizontalScrollView) findViewById(R.id.content_horsv);
        mPullRefreshScroll = (NestedScrollView) findViewById(R.id.pull_refresh_scroll);
        // 设置两个水平控件的联动
        mTitleHorsv.setScrollView(mContentHorsv);
        mContentHorsv.setScrollView(mTitleHorsv);
        mLeftContainerRv = (RecyclerView) findViewById(R.id.left_container_Rv);
        mRightTitleRv = (RecyclerView) findViewById(R.id.right_title_rv);
        mRightContainerRv = (RecyclerView) findViewById(R.id.right_container_Rv);
        mPullRefreshScroll.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY > oldScrollY) {
                    Log.i(TAG, "Scroll DOWN");
                }
                if (scrollY < oldScrollY) {
                    Log.i(TAG, "Scroll UP");
                }

                if (scrollY == 0) {
                    Log.i(TAG, "TOP SCROLL");
                }

                if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                    Log.i(TAG, "BOTTOM SCROLL");
//每滑动一次，加载5条数据
                    scrollViewList = getToShowData(autoDataBeanList, scrollOffset, scrollViewLimit, 0);
                    if (scrollViewList.size() > 0) {
                        rightContentAdapter.loadMoreComplete();
                        leftAdapter.loadMoreComplete();
                    }
                }
            }
        });
        //左侧菜单
        leftAdapter = new LeftMenuAdapter(R.layout.test_text);
        leftAdapter.setLoadMoreView(new CustomLoadMoreView());

        initRecyclerview(mLeftContainerRv, leftAdapter, LinearLayoutManager.VERTICAL, false, true);
//右侧标题
        rightTitleAdapter = new RightTitleStringAdapter(R.layout.test_text);
        initRecyclerview(mRightTitleRv, rightTitleAdapter, LinearLayoutManager.HORIZONTAL, false, true);
//右侧内容
        rightContentAdapter = new RightPresentAdapter(R.layout.test_right_text);
        rightContentAdapter.setLoadMoreView(new CustomLoadMoreView());
        rightContentAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMoreRequested();

            }
        }, mRightContainerRv);
        leftAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
            }
        }, mLeftContainerRv);
        initRecyclerview(mRightContainerRv, rightContentAdapter, LinearLayoutManager.VERTICAL, false, true);
        mTableTaskNameTv = (TextView) findViewById(R.id.table_task_name_tv);
        mTableTaskFormHeadInfoTv = (TextView) findViewById(R.id.table_task_form_head_info_tv);

        mTableTaskFormTailInfoTv = (TextView) findViewById(R.id.table_task_form_tail_info_tv);
        mPatrolFormFillTv = (TextView) findViewById(R.id.patrol_form_fill_tv);
        mPatrolFormFillTv.setOnClickListener(this);
        mPatrolFormModifyTv = (TextView) findViewById(R.id.patrol_form_modify_tv);
        mPatrolFormModifyTv.setOnClickListener(this);
        leftAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                AutoFormBean.DataBean dataBean = (AutoFormBean.DataBean) adapter.getData().get(position);
                if ("image".equals(dataBean.getCssClass())) {
                    String prevalue = dataBean.getPrevalue();
                    if (StrUtils.isStringValueOk(prevalue)) {
                        new DisplayPhotosActivity().startDisplayPhotosActivity(PatrolTaskFormRecordActivity.this, dataBean.getPrevalue(), 0);
                    } else {
                        notifyAdapterStatus(position);

                    }
                } else {
                    notifyAdapterStatus(position);

                }
            }
        });
        rightContentAdapter.setOnRightContentAdapterItemChildClickListener(new RightPresentAdapter.RightContentAdapterItemChildClick() {
            @Override
            public void onRightContentChildClick(AutoFormBean.DataBean dataBean, int position) {

                notifyAdapterStatus(position);
            }

            @Override
            public void loadFinished() {
                stopMaterialProgressDialog();
            }
        });
        mPatrolFormBackIv = (ImageView) findViewById(R.id.patrol_form_back_iv);
        mPatrolFormBackIv.setOnClickListener(this);
        mPatrolFormTitleTv = (TextView) findViewById(R.id.patrol_form_title_tv);
        mPatrolFormOperateIv = (ImageView) findViewById(R.id.patrol_form_operate_iv);
        mPatrolFormOperateIv.setOnClickListener(this);
    }

    /**
     * //通知左右两个adapter刷新数据
     *
     * @param position
     */
    private void notifyAdapterStatus(int position) {
        List<AutoFormBean.DataBean> leftData = leftAdapter.getData();
        AutoFormBean.DataBean leftBean = leftData.get(position);
        String emptyTag = leftBean.getEmptyDataTag();
        if ("空".equals(emptyTag)) {
            return;
        }
        currentSelectedPosition = position;
        setSelectedPreData();
        currentSelectedLefttData = leftBean;
        notifyDataSelecteStatus(leftBean, position);
    }

    /**
     * 给上一个选中的实体赋值
     */
    private void setSelectedPreData() {
        if (currentSelectedLefttData != null) {
            if (preSelectedLefttData != null) {
                if (preSelectedLefttData.getRecordId() != currentSelectedLefttData.getRecordId()) {
                    preSelectedLefttData = currentSelectedLefttData;
                }
            } else {
                preSelectedLefttData = currentSelectedLefttData;

            }
        }
        if (currentSelectedRightData != null) {
            if (preSelectedRightData != null) {
                if (preSelectedRightData.size() > 0 && currentSelectedRightData.size() > 0) {
                    if (preSelectedRightData.get(0).getRecordId() != currentSelectedRightData.get(0).getRecordId()) {
                        preSelectedRightData = currentSelectedRightData;
                    }
                }
            } else {
                preSelectedRightData = currentSelectedRightData;
            }
        }
    }

    //将上一个左右数据选中状态清零
    private void clearPreDataSelecteStatus() {

        if (preSelectedLefttData != null) {
            //如果当前点击的实体类和上一个实体类一样，返回
            if (preSelectedLefttData.getRecordId() == currentSelectedLefttData.getRecordId()) {
                return;
            }
            preSelectedLefttData.setSelected(false);
            int position = preSelectedLefttData.getPresentPosition();
            leftAdapter.notifyItemChanged(position);
            if (preSelectedRightData != null) {
                if (preSelectedRightData.size() > 0) {
                    for (AutoFormBean.DataBean preSelectedRightDatum : preSelectedRightData) {
                        preSelectedRightDatum.setSelected(false);
                    }
                }
            }
            rightContentAdapter.notifyItemChanged(position);
        }
    }

    ////通知左右两个adapter刷新数据
    private void notifyDataSelecteStatus(AutoFormBean.DataBean leftBean, int position) {
        PubUtil.CURRENT_SELECTED_VALUE_LIST.clear();
        PubUtil.patrolFormSelectedTowerId = "";
        PubUtil.patrolFormSelectedCellId = "";
        List<List<AutoFormBean.DataBean>> rightData = rightContentAdapter.getData();
        List<AutoFormBean.DataBean> rightChildData = rightData.get(position);
        currentSelectedRightData = rightChildData;
        currentRecordId = leftBean.getRecordId();
        PubUtil.CURRENT_SELECTED_VALUE_LIST.add(leftBean.getPrevalue());
        clearPreDataSelecteStatus();
        if (leftBean.isSelected()) {
            leftBean.setSelected(false);
        } else {
            leftBean.setSelected(true);
        }


        leftAdapter.notifyItemChanged(position);
        for (AutoFormBean.DataBean rightChildDatum : rightChildData) {
            String cssName = rightChildDatum.getCssClass();
            if ("cell".equals(cssName)) {///单元对应的房间id
                PubUtil.patrolFormSelectedTowerId = rightChildDatum.getPreId();
            }
            if ("room".equals(cssName)) {//房间对应的单元id
                PubUtil.patrolFormSelectedCellId = rightChildDatum.getPreId();
            }
            if (rightChildDatum.isSelected()) {
                rightChildDatum.setSelected(false);
                mPatrolFormFillTv.setBackground(ContextCompat.getDrawable(this, R.drawable.sp_default_color));
                mPatrolFormFillTv.setClickable(true);
                mPatrolFormModifyTv.setBackground(ContextCompat.getDrawable(this, R.drawable.rv_gray_shadow_shape));
                mPatrolFormModifyTv.setClickable(false);
            } else {
                rightChildDatum.setSelected(true);
                mPatrolFormFillTv.setBackground(ContextCompat.getDrawable(this, R.drawable.rv_gray_shadow_shape));
                mPatrolFormFillTv.setClickable(false);
                mPatrolFormModifyTv.setBackground(ContextCompat.getDrawable(this, R.drawable.sp_default_color));
                mPatrolFormModifyTv.setClickable(true);
            }
            PubUtil.CURRENT_SELECTED_VALUE_LIST.add(rightChildDatum.getPrevalue());
        }
        rightContentAdapter.notifyItemChanged(position);
    }

    @Override
    public void getDate() {

        Intent intent = getIntent();
        if (intent != null) {
            initFormINfo(intent);
            initBottomBtStatus();
        }
    }

    @Override
    public void actionBarRightTvOnClick() {

    }

    /**
     * 初始化底部按钮的状态
     */
    private void initBottomBtStatus() {
        if (status == 2) {
            mPatrolFormFillTv.setVisibility(View.GONE);
            mPatrolFormModifyTv.setVisibility(View.GONE);
        } else {
            mPatrolFormFillTv.setVisibility(View.VISIBLE);
            mPatrolFormModifyTv.setVisibility(View.VISIBLE);
            mPatrolFormModifyTv.setBackground(ContextCompat.getDrawable(this, R.drawable.rv_gray_shadow_shape));
            mPatrolFormModifyTv.setClickable(false);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (intent != null) {
            initFormINfo(intent);
        }
        super.onNewIntent(intent);

    }

    /**
     * 初始化表头 表尾  表单的头标题
     */
    private void initFormINfo(Intent intent) {
        formHeadTailInfo = intent.getParcelableExtra(PatrolCheckUpdateContract.PATROL_FORM_TASK_DEAL_BEAN);
        if (formHeadTailInfo != null) {
            Log.i("TAG", formHeadTailInfo.getId() + "-----");
            showMaterialProgressDialog();
            getPresenter().getPatrolFormTaskRecordList(formHeadTailInfo.getId(), "");
            status = formHeadTailInfo.getStatus();
            if (PubUtil.PATROL_FORM_LIST_DATABEAN != null) {
                String formTitle = PubUtil.PATROL_FORM_LIST_DATABEAN.getFormName();
                if (StrUtils.isStringValueOk(formTitle)) {
                    mPatrolFormTitleTv.setText(formTitle);
                }
            }

            mTableTaskNameTv.setText(formHeadTailInfo.getTaskName());
            initTableHeadInfoViewStatus(formHeadTailInfo);
            initTableTailInfoViewStatus(formHeadTailInfo);
            initRowTitle();
        }
    }

    /**
     * 初始化行标题数据
     */
    private void initRowTitle() {
        if (PubUtil.PATROL_FORM_TASK_ROW_TITLE.size() > 0) {
            List<String> rightTitleData = new ArrayList<>();
            for (int i = 0; i < PubUtil.PATROL_FORM_TASK_ROW_TITLE.size(); i++) {
                String title = PubUtil.PATROL_FORM_TASK_ROW_TITLE.get(i);
                if (0 == i) {
                    mTvTableTitleLeft.setText(title);
                } else {
                    rightTitleData.add(title);
                }
            }
            rightTitleAdapter.setNewData(rightTitleData);
        }
    }

    /**
     * 配置表单任务尾信息
     *
     * @param dealBean
     */
    private void initTableTailInfoViewStatus(PatrolTaskFormListBean.DataBean dealBean) {
        String tailInfo = getStringBuilderOfFootInfo(dealBean);
        if (StrUtils.isStringValueOk(tailInfo)) {
            mTableTaskFormTailInfoTv.setVisibility(View.VISIBLE);
            mTableTaskFormTailInfoTv.setText(tailInfo);
        } else {
            mTableTaskFormTailInfoTv.setVisibility(View.GONE);
        }
    }

    /**
     * 配置表单任务头信息
     *
     * @param dealBean
     */
    private void initTableHeadInfoViewStatus(PatrolTaskFormListBean.DataBean dealBean) {
        String headInfo = getStringBuilderOfHeadInfo(dealBean);
        if (StrUtils.isStringValueOk(headInfo)) {
            mTableTaskFormHeadInfoTv.setVisibility(View.VISIBLE);
            mTableTaskFormHeadInfoTv.setText(headInfo);
        } else {
            mTableTaskFormHeadInfoTv.setVisibility(View.GONE);
        }
    }


    /**
     * 获取表单头信息
     *
     * @return
     */
    private String getStringBuilderOfHeadInfo(PatrolTaskFormListBean.DataBean dataBean) {
        StringBuilder sb = new StringBuilder();

        String showData = dataBean.getShowDateValue();
        if (StrUtils.isStringValueOk(showData)) {
            sb.append("日期:");
            sb.append(showData + "     ");
        }
        String headTitle1 = dataBean.getHeaderTitle1Value();
        if (StrUtils.isStringValueOk(headTitle1)) {
            sb.append(PubUtil.PATROL_FORM_LIST_DATABEAN.getHeaderTitle1() + ":");
            sb.append(headTitle1 + "     ");
        }
        String headTitle2 = dataBean.getHeaderTitle2Value();
        if (StrUtils.isStringValueOk(headTitle2)) {
            sb.append(PubUtil.PATROL_FORM_LIST_DATABEAN.getHeaderTitle2() + ":");
            sb.append(headTitle2 + "     ");
        }
        String headTitle3 = dataBean.getHeaderTitle3Value();
        if (StrUtils.isStringValueOk(headTitle3)) {
            sb.append(PubUtil.PATROL_FORM_LIST_DATABEAN.getHeaderTitle3() + ":");
            sb.append(headTitle3 + "     ");
        }
        String headTitle4 = dataBean.getHeaderTitle4Value();
        if (StrUtils.isStringValueOk(headTitle4)) {
            sb.append(PubUtil.PATROL_FORM_LIST_DATABEAN.getHeaderTitle4() + ":");
            sb.append(headTitle4 + "     ");
        }
        return sb.toString();
    }

    /**
     * 获取表单尾信息
     *
     * @return
     */
    private String getStringBuilderOfFootInfo(PatrolTaskFormListBean.DataBean dataBean) {

        StringBuilder sb = new StringBuilder();
        String usage = PubUtil.PATROL_FORM_LIST_DATABEAN.getUsage();
        if (StrUtils.isStringValueOk(usage)) {
            sb.append("注:");
            sb.append(usage + "     ");
        }
        String tailTitle1 = dataBean.getFooterTitle1Value();
        if (StrUtils.isStringValueOk(tailTitle1)) {
            sb.append(PubUtil.PATROL_FORM_LIST_DATABEAN.getFooterTitle1() + ":");
            sb.append(tailTitle1 + "     ");
        }
        String tailTitle2 = dataBean.getFooterTitle2Value();
        if (StrUtils.isStringValueOk(tailTitle2)) {
            sb.append(PubUtil.PATROL_FORM_LIST_DATABEAN.getFooterTitle2() + ":");
            sb.append(tailTitle2 + "     ");
        }
        String tailTitle3 = dataBean.getFooterTitle3Value();
        if (StrUtils.isStringValueOk(tailTitle3)) {
            sb.append(PubUtil.PATROL_FORM_LIST_DATABEAN.getFooterTitle3() + ":");
            sb.append(tailTitle3 + "     ");
        }
        String tailTitle4 = dataBean.getFooterTitle4Value();
        if (StrUtils.isStringValueOk(tailTitle4)) {
            sb.append(PubUtil.PATROL_FORM_LIST_DATABEAN.getFooterTitle4() + ":");
            sb.append(tailTitle4 + "     ");
        }
        String codeNum = PubUtil.PATROL_FORM_LIST_DATABEAN.getShowCode();
        if (StrUtils.isStringValueOk(codeNum)) {
            sb.append("编号:");
            sb.append(codeNum + "     ");
        }
        return sb.toString();
    }


    @Override
    public void startLoading(String tag) {

    }

    @Override
    public void stopLoading(String tag) {

    }

    @Override
    public void updateView(Object o, String tag) {

        switch (tag) {
            case PatrolCheckUpdateContract.FORM_CONTINUE:
                stopMaterialProgressDialog();

                //更改底部两个按钮的状态
                status = 1;
                initBottomBtStatus();

                break;

            case PatrolCheckUpdateContract.FORM_SUBMIT_SUCCEEF:
                stopMaterialProgressDialog();

                String msg = (String) o;
                showToast(msg);
                //提交成功后 直接跳转到表单模块预览的界面
                startActivity(new Intent(this, PatrolFormPreviewActivity.class));
                break;
            default:
                analysisRecordData((PatrolFormTaskBean) o);
                break;
        }


    }

    /**
     * 解析表单记录
     *
     * @param o
     */
    private void analysisRecordData(PatrolFormTaskBean o) {
        PatrolFormTaskBean patrolFormTaskBean = o;
        if (patrolFormTaskBean != null) {
            List<PatrolFormTaskBean.DataBean> arrays = patrolFormTaskBean.getData();
            if (arrays != null) {
                initFillBtStatus(arrays.size());
                autoDataBeanList = getAllData(arrays);
                scrollOffset = 0;//滑动时加载数据的起点
                displayOffset = 0;//展示时的起点
                leftAdapter.setNewData(null);
                rightContentAdapter.setNewData(null);
                //第一次 截取待展示的数据  截取数据 分段展示 不然会一直调用onloadmore的方法 知道把所有数据展示完成
                scrollViewList = getToShowData(autoDataBeanList, scrollOffset, scrollViewLimit, 0);
                //每次展示5条数据 分阶段展示
                getAdapterData(getToShowData(scrollViewList, displayOffset, loadLimit, 1));
            }
        }
    }

    /**
     * 获取所以数据  空数据和实体数据
     *
     * @param arrays
     */
    private List<List<AutoFormBean.DataBean>> getAllData(List<PatrolFormTaskBean.DataBean> arrays) {
        List<List<AutoFormBean.DataBean>> autoDataBeanList = new ArrayList<>();
        for (PatrolFormTaskBean.DataBean array : arrays) {
            String contentJson = array.getContentJson();
            int recordId = array.getId();
            if (StrUtils.isStringValueOk(contentJson)) {
                List<AutoFormBean.DataBean> autoDataBeans = (List<AutoFormBean.DataBean>) GsonManager.getInstance().parseJsonToList(contentJson, new TypeToken<List<AutoFormBean.DataBean>>() {
                }.getType());
                for (AutoFormBean.DataBean autoDataBean : autoDataBeans) {
                    autoDataBean.setRecordId(recordId);
                }
                autoDataBeanList.add(autoDataBeans);
            }
        }
        int hasDataSize = autoDataBeanList.size();
        if (PubUtil.PATROL_FORM_LIST_DATABEAN.getRowNum() > hasDataSize) {
            int size = PubUtil.PATROL_FORM_LIST_DATABEAN.getRowNum() - hasDataSize;
            for (int i = 0; i < size; i++) {
                List<AutoFormBean.DataBean> item = new ArrayList<>();
                AutoFormBean.DataBean bean = new AutoFormBean.DataBean();
                bean.setEmptyDataTag("空");
                item.add(bean);
                for (int x = 0; x < PubUtil.PATROL_FORM_TASK_ROW_TITLE.size() - 1; x++) {
                    AutoFormBean.DataBean bean2 = new AutoFormBean.DataBean();
                    bean2.setEmptyDataTag("空");
                    item.add(bean2);

                }
                autoDataBeanList.add(item);
            }

        }
        if (autoDataBeanList.size() == 0) {
            stopMaterialProgressDialog();
        }
        return autoDataBeanList;
    }

    /**
     * 初始化填写按钮的状态
     */
    private void initFillBtStatus(int arraysSize) {
        if (arraysSize >= PubUtil.PATROL_FORM_LIST_DATABEAN.getRowNum()) {
            mPatrolFormFillTv.setBackground(ContextCompat.getDrawable(this, R.drawable.rv_gray_shadow_shape));
            mPatrolFormFillTv.setClickable(false);

        } else {
            mPatrolFormFillTv.setBackground(ContextCompat.getDrawable(this, R.drawable.sp_default_color));
            mPatrolFormFillTv.setClickable(true);
        }
        mPatrolFormModifyTv.setBackground(ContextCompat.getDrawable(this, R.drawable.rv_gray_shadow_shape));
        mPatrolFormModifyTv.setClickable(false);
    }


    @Override
    public void onError(String tag) {
        showToast(tag);
        Log.i("TAG", tag);
    }

    @Override
    public void onClick(View v) {
        if (bottomSheetDialog != null) {
            if (bottomSheetDialog.isShowing()) {
                bottomSheetDialog.dismiss();
            }
        }

        switch (v.getId()) {
            default:
                break;
            case R.id.patrol_form_fill_tv:
                //填写
                startToDailyRecordFormActivity(2);
                break;
            case R.id.patrol_form_modify_tv:
                startToDailyRecordFormActivity(3);

                break;
            case R.id.mine_edit_cancel_pic_tv:
                //取消

                break;
            case R.id.mine_edit_take_pic_tv:
                //编辑表单信息
                PubUtil.MODIFY_PATROL_FORM_INFO = 1;

                if (formHeadTailInfo != null) {
                    Intent intent = new Intent(this, ModifyPatrolFormInfoActivity.class);
                    intent.putExtra(PatrolCheckUpdateContract.PATROL_FORM_TASK_DEAL_BEAN, formHeadTailInfo);
                    startActivityForResult(intent, ActivityResultManager.OLD_FORM_UPDATA);
//                    overridePendingTransition(R.anim.bottom_in, R.anim.bottom_silent);


                }


                break;
            case R.id.mine_edit_select_pic_tv:
                ////继续填写，或者上交表单
                    showMaterialProgressDialog();
                    if (2 == status) {
                        //继续填写
                        if (formHeadTailInfo != null) {
                            getPresenter().continueForm(formHeadTailInfo.getId(), mUserInfoUtil.getUserId(), PatrolCheckUpdateContract.FORM_CONTINUE);
                        }
                    } else {
                        //上交表单
                        if (formHeadTailInfo != null) {
                            getPresenter().submitForm(formHeadTailInfo.getId(), UserInfoUtil.getInstance().getUserId(), PatrolCheckUpdateContract.FORM_SUBMIT_SUCCEEF);
                        }
                    }

                break;
            case R.id.patrol_form_back_iv:
                onBackPressed();
                break;
            case R.id.patrol_form_operate_iv:
                View bottomView = LayoutInflater.from(this).inflate(R.layout.select_pic_menue, null);
                bottomSheetDialog = new BottomSheetDialog(this);
//        bottomSheetDialog.setCanceledOnTouchOutside(false);
//        bottomSheetDialog.setCancelable(false);
                bottomSheetDialog.setContentView(bottomView);
                bottomSheetDialog.show();
                TextView editFormDes = bottomView.findViewById(R.id.mine_edit_take_pic_tv);
                bottomView.findViewById(R.id.mine_edit_cancel_pic_tv).setOnClickListener(this);
                editFormDes.setText("编辑表单信息");
                editFormDes.setOnClickListener(this);
                TextView commitForm = bottomView.findViewById(R.id.mine_edit_select_pic_tv);
                if (2 == status) {
                    //已提交的表单任务
                    commitForm.setText("继续填写");
                } else {
                    commitForm.setText("上交表单");
                }
                commitForm.setOnClickListener(this);
                bottomView.findViewById(R.id.mine_edit_title_tv).setVisibility(View.GONE);
                break;
        }
    }


    /**
     * 跳转到日常记录表单
     * entryTag  入口标识   //2代表填写，3代表修改
     */
    private void startToDailyRecordFormActivity(int entryTag) {
        TableTaskDetailBean.DataBean tableDetailBean = new TableTaskDetailBean.DataBean();
        PubUtil.PATROL_FORM_LIST_DATABEAN.getRowNum();
        if (formHeadTailInfo != null) {
            tableDetailBean.setId(formHeadTailInfo.getFormId());
            tableDetailBean.setFieldJson(PubUtil.PATROL_FORM_LIST_DATABEAN.getFieldJson());
            tableDetailBean.setUsage(PubUtil.PATROL_FORM_LIST_DATABEAN.getUsage());
            tableDetailBean.setFormName(PubUtil.PATROL_FORM_LIST_DATABEAN.getFormName());
            tableDetailBean.setTaskId(formHeadTailInfo.getId());
        }
        tableDetailBean.setRecordId(currentRecordId);
        Intent intentFill = new Intent(this, CreateFormActivity.class);
        intentFill.putExtra(ActivityResultManager.TABLE_TASK_DAILY_RECORD, tableDetailBean);
        PubUtil.DailyRecordActivityEntry = entryTag;
        startActivityForResult(intentFill, ActivityResultManager.PATROL_FORM);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == ActivityResultManager.PATROL_FORM) {
            if (data != null) {
                ArrayList<AutoFormBean.DataBean> arrays = data.getParcelableArrayListExtra(ActivityResultManager.PATROL_FORM_TASK_RECORD_ACTIVITY);
                int position = -1;
                if (arrays != null && arrays.size() > 0) {
                    List<AutoFormBean.DataBean> leftData = leftAdapter.getData();
                    AutoFormBean.DataBean leftItemData = arrays.get(0);

                    if (2 == PubUtil.DailyRecordActivityEntry) {
                        //填写时刷新数据
                        position = getFirstEmptyDataPosition();
                        //这时候将数据插入到所有数据autoDataBeanList中
                        autoDataBeanList.remove(position);
                        autoDataBeanList.add(position, arrays);
                        if (position == autoDataBeanList.size() - 1) {
                            //更改按钮的状态 无空布局了
                            initFillBtStatus(1000);
                        }
                    }
                    if (position > leftData.size() - 1) {
                        //需要插入的位置还没有加载出来  这时候将数据插入到所有数据autoDataBeanList中
                        return;
                    }
                    arrays.remove(0);
                    if (3 == PubUtil.DailyRecordActivityEntry) {
//                        leftItemData.setRecordId(currentRecordId);
                        //编辑状态  获取位置
                        position = currentSelectedPosition;
                        //左侧选中状态复位
                        leftItemData.setSelected(true);

                        PubUtil.CURRENT_SELECTED_VALUE_LIST.clear();
                        PubUtil.CURRENT_SELECTED_VALUE_LIST.add(leftItemData.getPrevalue());
                        //右侧选中状态复位
                        for (AutoFormBean.DataBean rightChildDatum : arrays) {
//                            rightChildDatum.setRecordId(currentRecordId);
                            rightChildDatum.setSelected(true);
                            PubUtil.CURRENT_SELECTED_VALUE_LIST.add(rightChildDatum.getPrevalue());
                            String cssName = rightChildDatum.getCssClass();
                            if ("cell".equals(cssName)) {///单元对应的楼id
                                PubUtil.patrolFormSelectedTowerId = rightChildDatum.getPreId();
                            }
                            if ("room".equals(cssName)) {//房间对应的单元id
                                PubUtil.patrolFormSelectedCellId = rightChildDatum.getPreId();
                            }
                        }
                    }

                    leftData.remove(position);
                    List<List<AutoFormBean.DataBean>> rightData = rightContentAdapter.getData();
                    rightData.remove(position);
                    currentSelectedRightData = arrays;
                    currentSelectedLefttData = leftItemData;
                    leftData.add(position, leftItemData);
                    rightData.add(position, arrays);
                    leftAdapter.notifyItemChanged(position);
                    rightContentAdapter.notifyItemChanged(position);

                }

            }
        }
    }

    /**
     * //获取第一个空布局对应的位置
     */
    private int getFirstEmptyDataPosition() {
        int position = 0;
        boolean canBreak = false;

        for (int i = 0; i < autoDataBeanList.size(); i++) {
            if (canBreak) {
                position = i - 1;
                break;
            }
            List<AutoFormBean.DataBean> dataBeans = autoDataBeanList.get(i);
            for (AutoFormBean.DataBean dataBean : dataBeans) {
                if ("空".equals(dataBean.getEmptyDataTag())) {
                    canBreak = true;
                    if (i == autoDataBeanList.size() - 1) {
                        position = i;
                    }
                    break;
                }
            }
        }
        return position;
    }


    /**
     * 截取需要展示的数据
     *
     * @param lists
     * @param offset
     * @param limit
     * @param type   0代表滑动时加载的数据 15条，1代表展示时每次展示的数据 2条
     * @return
     */
    private List<List<AutoFormBean.DataBean>> getToShowData(List<List<AutoFormBean.DataBean>> lists, int offset, int limit, int type) {
        List<List<AutoFormBean.DataBean>> arrasy = new ArrayList<>(lists.size());
        for (int i = 0; i < lists.size(); i++) {
            if (i > offset - 1 && i < limit + offset) {
                arrasy.add(lists.get(i));
            }
        }
        if (0 == type) {
            this.scrollOffset += arrasy.size();

        } else {
            this.displayOffset += arrasy.size();

        }
        return arrasy;
    }

    /**
     * 获取数据
     *
     * @param lists
     */
    private void getAdapterData(List<List<AutoFormBean.DataBean>> lists) {
        if (lists != null) {
            if (lists.size() > 0) {
                rightTitleData.clear();
                leftMenuData.clear();
                rightContent.clear();
                for (int i = 0; i < lists.size(); i++) {
                    List<AutoFormBean.DataBean> arrays = lists.get(i);
                    List<AutoFormBean.DataBean> rightContentItem = new ArrayList<>();

                    for (int x = 0; x < arrays.size(); x++) {
                        AutoFormBean.DataBean dataBean = arrays.get(x);
                        if (0 == x) {//获取左侧数据
                            leftMenuData.add(dataBean);
                        } else {
                            rightContentItem.add(dataBean);
                        }
                    }
                    rightContent.add(rightContentItem);
                }

                leftAdapter.addData(leftMenuData);
                rightContentAdapter.addData(rightContent);
                if (lists.size() < loadLimit) {
                    displayDataComplete();

                } else {
                    rightContentAdapter.loadMoreComplete();
                    leftAdapter.loadMoreComplete();
                }
            } else {
                displayDataComplete();
            }
        }
    }

    /**
     * 数据展示完毕
     */
    private void displayDataComplete() {
        //第一页如果不够一页就不显示没有更多数据布局
        rightContentAdapter.loadMoreEnd(false);
        leftAdapter.loadMoreEnd(false);
        displayOffset = 0;
        if (leftAdapter.getData().size() == autoDataBeanList.size()) {
            rightContentAdapter.loadMoreEnd(true);
            leftAdapter.loadMoreEnd(true);
        }
    }

    /**
     * 加载更多数据
     */
    private void loadMoreRequested() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //每次展示loadLimit条数据
                getAdapterData(getToShowData(scrollViewList, displayOffset, loadLimit, 1));
            }
        }, 500);
    }

}
