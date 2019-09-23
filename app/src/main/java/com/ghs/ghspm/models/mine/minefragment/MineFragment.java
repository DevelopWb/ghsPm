package com.ghs.ghspm.models.mine.minefragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseFragment;
import com.ghs.ghspm.base.BasePresent;
import com.ghs.ghspm.base.network.RequestStatus;
import com.ghs.ghspm.bean.LoginBean;
import com.ghs.ghspm.bean.MineMenuBean;
import com.ghs.ghspm.bean.UserInfoBean;
import com.ghs.ghspm.models.login.LoginActivity;
import com.ghs.ghspm.models.main.MainContact;
import com.ghs.ghspm.models.mine.personalInfo.PersonalInfoModel;
import com.ghs.ghspm.models.mine.set.SetActivity;
import com.ghs.ghspm.models.mine.systemNotice.SystemNoticeActivity;
import com.ghs.ghspm.models.mine.systemNotice.SystemNoticeContract;
import com.ghs.ghspm.models.mine.personalInfo.PersonalInfoActivity;
import com.ghs.ghspm.models.unread.UnReadTagModel;
import com.ghs.ghspm.tools.ActivityResultManager;
import com.ghs.ghspm.tools.Contract;
import com.ghs.ghspm.tools.EventManager;
import com.ghs.ghspm.tools.glide.GlideCircleTransform;
import com.ghs.ghspm.tools.HawkProperty;
import com.ghs.ghspm.tools.StrUtils;
import com.ghs.ghspm.tools.UserInfoUtil;
import com.orhanobut.hawk.Hawk;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;



/**
 * Author:wang_sir
 * Time:2018/6/20 21:18
 * Description:This is FirstFragment
 */


public class MineFragment extends BaseFragment implements RequestStatus, View.OnClickListener {

    private View view;
    private RelativeLayout mMineUserInfoRl;
    private ImageView mMineUserHeadIv;
    private ImageView mMineUserArrow_iv;
    /**
     * 平静如水
     */
    private TextView mMineUserNameTv;
    /**
     * 123456
     */
    private TextView mMineUserMobileTv;
    private RecyclerView mMineSystemNoticeRv;
    private MineFragmentAdapter mineFragmentAdapter;

    private boolean isStop = false;//fragment被完全覆盖了
    private boolean isSystemNoticeActivityBack = false;//从SystemNoticeActivity这个类中退出
    private TextView mMineUserHeadTv;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine_fragment, container, false);
        initView(view);
        return view;
    }

    @Override
    protected void lazyLoad() {
        Log.d("lazyLoad", "lazyLoad---------------" + TAG);
        new UnReadTagModel().getUnreadSysNoticeAmount(UserInfoUtil.getInstance().getUserId(), this, SystemNoticeContract.GET_SYS_NOTICE_UNREAD);

    }

    private void initView(View view) {
        mMineUserInfoRl = (RelativeLayout) view.findViewById(R.id.mine_user_info_rl);
        mMineUserInfoRl.setOnClickListener(this);
        mMineUserHeadIv = (ImageView) view.findViewById(R.id.mine_user_head_iv);
        mMineUserHeadIv.setOnClickListener(this);

        mMineUserArrow_iv = (ImageView) view.findViewById(R.id.mine_user_arrow_right_iv);
        mMineUserNameTv = (TextView) view.findViewById(R.id.mine_user_name_tv);
        mMineUserNameTv.setOnClickListener(this);
        mMineUserMobileTv = (TextView) view.findViewById(R.id.mine_user_mobile_tv);

        mMineSystemNoticeRv = (RecyclerView) view.findViewById(R.id.mine_system_notice_rv);
        mineFragmentAdapter = new MineFragmentAdapter(R.layout.mine_fragment_item);
        initRecyclerview(mMineSystemNoticeRv, mineFragmentAdapter, LinearLayoutManager.VERTICAL, false, false);
        mineFragmentAdapter.setNewData(getAdapterData());
        addDivider(true, mMineSystemNoticeRv, false, false);

        mineFragmentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MineMenuBean bean = (MineMenuBean) adapter.getData().get(position);
                switch (bean.getTitle()) {
                    case "系统消息":
                        startActivityForResult(new Intent(getContext(), SystemNoticeActivity.class), ActivityResultManager.MINE_SYSTEM_NOTICE_BACK);
                        break;
                    case "设置":
                        startActivityForResult(new Intent(getContext(), SetActivity.class), ActivityResultManager.MINE_SET_QUIT_APP);
                        break;
                    default:
                        break;
                }

            }
        });
        mMineUserHeadTv = (TextView) view.findViewById(R.id.mine_user_head_tv);
    }

    /**
     * 获取adapter数据
     *
     * @return
     */
    private List<MineMenuBean> getAdapterData() {
        List<MineMenuBean> arrays = new ArrayList<>(20);
        arrays.add(new MineMenuBean("系统消息", -1, R.mipmap.mine_about_us));
        arrays.add(new MineMenuBean("设置", -1, R.mipmap.mine_set));
        return arrays;
    }

    @Override
    protected View initFragmentViewLayout(LayoutInflater inflater, ViewGroup container) {
        return null;
    }

    @Override
    protected void initFragmentView(View view) {

    }

    @Override
    protected void initFragmentData() {
        initLoginStatus();
        new PersonalInfoModel().getUserInfoDetail(UserInfoUtil.getInstance().getPropertyId(), UserInfoUtil.getInstance().getVillageId(), UserInfoUtil.getInstance().getUserId(), "", this);
    }

    @Override
    protected BasePresent createPresenter() {
        return null;
    }

    @Override
    public void onResume() {
        if (Hawk.contains(HawkProperty.LOGIN_BEAN )) {
            if (isStop) {
                if (!isSystemNoticeActivityBack) {
                    isStop = false;
                    new UnReadTagModel().getUnreadSysNoticeAmount(UserInfoUtil.getInstance().getUserId(), this, SystemNoticeContract.GET_SYS_NOTICE_UNREAD);
                }
            }
        }

        super.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == ActivityResultManager.MODIFY_USER_INFO) {
            new PersonalInfoModel().getUserInfoDetail(UserInfoUtil.getInstance().getPropertyId(), UserInfoUtil.getInstance().getVillageId(), UserInfoUtil.getInstance().getUserId(), "", this);
        } else if (resultCode == ActivityResultManager.MINE_SET_QUIT_APP) {
            //将用户名赋值为登录，并将手机号和>图表隐藏
            initLoginStatus();
        } else if (resultCode == ActivityResultManager.QUIT_CURRENT_ACCOUNT_FOR_LOGIN) {
            initLoginStatus();
        } else if (resultCode == ActivityResultManager.QUIT_CURRENT_ACCOUNT_FOR_LOGIN_CANCEL) {//注销登录过程中取消登录
            initLoginStatus();
        } else if (resultCode == ActivityResultManager.MINE_SYSTEM_NOTICE_BACK) {//退出系统消息
            //  重新请求红点数量
            isSystemNoticeActivityBack = true;
            if (Hawk.contains(HawkProperty.LOGIN_BEAN )) {
                new UnReadTagModel().getUnreadSysNoticeAmount(UserInfoUtil.getInstance().getUserId(), this, SystemNoticeContract.GET_SYS_NOTICE_UNREAD);
            }
        }
    }

    private void initLoginStatus() {
        if (Hawk.contains(HawkProperty.LOGIN_BEAN )) {
            LoginBean userInfoBean = Hawk.get(HawkProperty.LOGIN_BEAN );
            if (userInfoBean != null) {
                if (userInfoBean.getData() != null) {
                    String picPath = userInfoBean.getData().getHeadImage();
                    mMineUserNameTv.setText(userInfoBean.getData().getName());
                    //将用户名赋值为登录，并将手机号和>图表隐藏
                    mMineUserMobileTv.setVisibility(View.VISIBLE);
                    mMineUserArrow_iv.setVisibility(View.VISIBLE);
                    mMineUserInfoRl.setFocusable(true);
                    mMineUserInfoRl.setClickable(true);
                    mMineUserNameTv.setFocusable(false);
                    mMineUserNameTv.setClickable(false);
                    mMineUserMobileTv.setText(userInfoBean.getData().getMobile());
                    if (mUserInfoUtil != null) {
                        getBaseActivity().setHeadPicBgResource(mMineUserHeadIv,mMineUserHeadTv,mUserInfoUtil.getUserName(),picPath,mUserInfoUtil.getHeadDefaultBgColor(),false);
                    }
                } else {
                    readyForLogin();
                }
            }

        } else {
            readyForLogin();
        }


    }

    /**
     * 这个逻辑可以放弃  现阶段没有这种情况
     */
    private void readyForLogin() {
        clearSysNoticeRedPointStatus();
        mMineUserNameTv.setText("登录");
        mMineUserMobileTv.setVisibility(View.INVISIBLE);
        mMineUserArrow_iv.setVisibility(View.INVISIBLE);
        mMineUserInfoRl.setFocusable(false);
        mMineUserInfoRl.setClickable(false);
        mMineUserNameTv.setFocusable(true);
        mMineUserNameTv.setClickable(true);
        Glide.with(this).load(Contract.ImageBasePath + "")
                .skipMemoryCache(false)
                // 加载图片
//                        .error(errorimg)
//                        // 设置错误图片
//                        .crossFade()
//                        // 设置淡入淡出效果，默认300ms，可以传参
                .placeholder(R.mipmap.default_user_head_icon)
                // 设置占位图
                .transform(new GlideCircleTransform(getContext()))//圆角
                .into(mMineUserHeadIv);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        isStop = true;
        isSystemNoticeActivityBack = false;
        super.onStop();
    }

    @Override
    public void onStart(String tag) {

    }

    /**
     * 系统消息
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receivedMsgAboutSystemNotice(String str) {
        if (MainContact.CHANGE_VILLAGE.equals(str)) {//收到系统消息的notification
            //读取未读系统消息数
            new UnReadTagModel().getUnreadSysNoticeAmount(UserInfoUtil.getInstance().getUserId(), this, SystemNoticeContract.GET_SYS_NOTICE_UNREAD);

        } else if (ActivityResultManager.TASK_FRAGMENT_REFRESH.equals(str)) {
            initLoginStatus();
        }
    }

    @Override
    public void onSuccess(Object o, String tag) {
        if (SystemNoticeContract.GET_SYS_NOTICE_UNREAD.equals(tag)) {
            String data = (String) o;
            if (StrUtils.isStringValueOk(data)) {
                int amount = Integer.parseInt(data);
                if (amount > 0) {
                    updateSysNoticeRedPointStatus(amount);
                } else {
                    clearSysNoticeRedPointStatus();
                }

            }
        } else {
            UserInfoBean userInfoBean = (UserInfoBean) o;

            if (userInfoBean != null) {
                if (userInfoBean.getData() != null) {
                    LoginBean loginBean = Hawk.get(HawkProperty.LOGIN_BEAN );
                    LoginBean.DataBean dataBean = loginBean.getData();
                    dataBean.setHeadImage(userInfoBean.getData().getHeadImage());
                    dataBean.setMobile(userInfoBean.getData().getMobile());
                    Hawk.put(HawkProperty.LOGIN_BEAN , loginBean);
                    initLoginStatus();
                }
            }
        }


    }

    @Override
    public void onError(String tag) {

    }

    /**
     * 更新系统消息红点状态
     * EventManager.getLibraryEvent().post(new MineMenuBean("",6,0));
     */
    public void updateSysNoticeRedPointStatus(int amount) {
        if (mineFragmentAdapter != null) {
            List<MineMenuBean> arrays = mineFragmentAdapter.getData();
            for (int i = 0; i < arrays.size(); i++) {
                MineMenuBean array = arrays.get(i);
                String title = array.getTitle();
                if ("系统消息".equals(title)) {
                    array.setUnReadAmount(amount);
                    mineFragmentAdapter.notifyItemChanged(i);
                    break;
                }
            }
        }

    }

    /**
     * 清空系统消息红点状态
     */
    public void clearSysNoticeRedPointStatus() {
        if (mineFragmentAdapter != null) {
            List<MineMenuBean> arrays = mineFragmentAdapter.getData();
            for (int i = 0; i < arrays.size(); i++) {
                MineMenuBean array = arrays.get(i);
                String title = array.getTitle();
                if ("系统消息".equals(title)) {
                    array.setUnReadAmount(-1);
                    mineFragmentAdapter.notifyItemChanged(i);
                    break;
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.mine_user_info_rl:
                startActivityForResult(new Intent(getContext(), PersonalInfoActivity.class), ActivityResultManager.MODIFY_USER_INFO);
                break;

            case R.id.mine_user_name_tv://登录的时候可以点击
                String name = mMineUserNameTv.getText().toString().trim();
                if ("登录".equals(name)) {
                    //跳转到登录界面
                    startActivityForResult(new Intent(getContext(), LoginActivity.class), ActivityResultManager.QUIT_CURRENT_ACCOUNT_FOR_LOGIN);

                }
                break;
            case R.id.mine_user_head_iv://点击头像
                EventManager.getLibraryEvent().post(mMineUserHeadIv);
                break;
        }
    }


}
