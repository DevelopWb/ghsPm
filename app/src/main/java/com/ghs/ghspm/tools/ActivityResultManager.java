package com.ghs.ghspm.tools;

/**
 * Author:wang_sir
 * Time:2018/7/9 11:23
 * Description:This is ActivityResultManager
 */
public interface ActivityResultManager {

    int LOGIN_MOBILE_SUCESSED = 99;//手机号码登录成功
    int BOUND_MOBILE = 97;//绑定手机
    int TAKE_PICTURE = 96;//调用系统相机拍照
    int SYSTEM_PICTURE = 95;//系统相册中选择图片
    int PULISH_NOTICE = 94;//发布通知
    int MODIFY_USER_INFO = 93;//修改用户资料
    int EDIT_USER_MODIFY_MOBILE = 92;//修改用户手机号
    int MINE_SET_QUIT_APP = 91;//设置 退出应用
    int QUIT_CURRENT_ACCOUNT_FOR_LOGIN = 90;//注销登录
    int QUIT_CURRENT_ACCOUNT_FOR_LOGIN_CANCEL = 89;//注销登录后，登录界面取消
    int LOGIN_MOBILE_CANCEL = 88;//手机号码登录取消
    int PUBLISH_TASK_SELECT_PEOPLE = 87;//发布任务中选择人
    int DEAL_TASK_DETAIL = 86;//处理任务的详情页
    int PUBLISH_TASK = 85;//发布任务
    int QUIT_CURRENT_ACCOUNT_FOR_LOGIN_OTHER = 84;//注销登录,登入接口不是我的界面
    int QUIT_CURRENT_ACCOUNT_FOR_TOKEN_MAINACTIVITY = 82;//注销登录,登入接口不是我的界面
    int SAVE_WATER_RECORD = 83;//保存水表记录
    int PUBLISH_NOTICE_SELECT_DEP = 81;//发布通知 选择部门
    int NOEMAL_NOTICE_DETAIL = 80;//消息详情
    int TABLE_TASK_DETAIL = 79;//表单任务详情
    int FINISH_FORM_TASK_DETAIL_ACTIVITY = 78;//关闭TableTaskDetailActivity
    int MINE_SYSTEM_NOTICE_BACK = 77;//退出系统消息
    int RECARD_SUCCESS = 76;//补卡成功
    int COMPILE_SUCCESS = 75;//编辑任务成功
    int PATROL_FORM = 70;//巡检表单
    int TABLE_FORM_CONTINUE = 69;//正在填写的表单
    int TABLE_FORM_SUBMITTED = 68;//已经上交的表单
    int NEW_FORM_UPDATA = 67;//编辑表单信息
    int OLD_FORM_UPDATA = 66;//编辑表单信息
    int CREATE_ORDER_SEACHROOM = 65;//搜索房间
    int CREATE_ORDER_COMPLETE =64;//提交工单
    int UPDATE_ORDER_COMPLETE =63;//编辑工单
    int WORK_ORDER_DETAIL =62;//工单详情
    int WORK_ORDER_DETAIL_WRITE_PROGRESS =61;//写进展
    int WORK_ORDER_DETAIL_RECEIPT =60;//工单回执



    String INSTITUTION_URL = "institution_url";//制度URL 的key
    String NORMAL_NOTICE_ID = "normal_notice_id";//通知的id
    String RULEID = "rule_id";//考勤id
    String WATER_ROOM_ID = "water_room_id";//水表抄录 roomid
    String USER_GUID_TAG = "user_guid_tag";
    String MAINATIVITY_TAG = "main_activity";
    String EXCEPT_MAINATIVITY_TAG = "except_main_activity";//非mainactivity Tag
    String TOKEN_EXPIRED_ACTIVITY_TAG = "token_expired_activity_tag";//token过期的类标识
    String TASK_FRAGMENT_REFRESH = "task_fragment_refresh";//待处理任务 刷新列表
    String SELECTED_DEPS_ID = "selected_deps_id";//发布通知 选择部门
    String SELECTED_DEPS_NAME = "selected_deps_name";//发布通知 选择部门


    String CAR_NO = "car_no";//车牌号
    String DISPLAY_PHOTOS = "display_photos";//展示的图片
    String DISPLAY_PHOTO_POSITION = "display_photo_position";//展示的图片的起点

    String AC_TO_FG_DEP_ID = "ac_to_fg_dep_id";//activity传递给fragment的depid


    String USER_GUIDE_TASK = "user_guide_task";
    String USER_GUIDE_SIGN = "user_guide_sign";
    String USER_GUIDE_KEY = "user_guide_key";
    String USER_GUIDE_NOTICE = "user_guide_notice";
    String USER_GUIDE_ORGANIZE = "user_guide_organize";
    String USER_GUIDE_WATER_RECORD = "user_guide_water";
    String USER_GUIDE_CAR_INFO = "user_guide_car";
    String USER_GUIDE_MINE = "user_guide_mine";


    /**
     * 表单任务
     */
    String TABLE_TASK_DETAIL_ID = "table_task_detail_id";
    String TABLE_TASK_DETAIL_TYPE = "table_task_detail_type";
    String TABLE_TASK_DETAIL_STATUS = "table_task_detail_status";
    String TABLE_TASK_DAILY_RECORD = "table_task_daily_record";
    String COMMON_SIGN_ACTIVITY = "common_sign_activity";
    String AUTO_FORM_SIGN_ACTIVITY = "auto_form_sign_activity";
    String PATROL_FORM_TASK_RECORD_ACTIVITY = "patrol_form_task_record_activity";
    String CREATE_ORDER = "create_order";//创建工单


}
