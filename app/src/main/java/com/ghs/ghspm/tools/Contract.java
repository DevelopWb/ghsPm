package com.ghs.ghspm.tools;

/**
 * Author:wang_sir
 * Time:2018/7/9 9:43
 * Description:This is Contract
 */
public class Contract {


    //用户协议地址
    public static String USER_PROTOCAL_URL = "http://image.guangheplus.com/html/protocol.html";//用户协议地址
    /**
     * 图片的绝对路径
     */
    public static String ImageBasePath = "http://image.guangheplus.com/images/";
    /**
     * 制度baseUrl
     */
    public static String INTITUTION_URL = "http://image.guangheplus.com/";

    /*BaseUrl*/
    public static String BASE_URL_DEBUG = "https://rd.pmapi.guangheplus.com";//调试版
    /*BaseUrl*/
    //记得把bugly打开 热修复的请求 切记！！！！！！！！！
    //记得把bugly打开 热修复的请求打开 切记！！！！！！！！！
    //记得把bugly打开 热修复的请求 切记！！！！！！！！！
    public static String BASE_URL_RELEASE = "https://pmapi.guangheplus.com";//生产环境 release路径
    public static String BASE_URL = "";//生产环境 release路径
    /**
     * water web url
     */
//    public static String WATER_WEB_URL = "http://pmapp.guangheplus.com";
    public static String WATER_WEB_URL = "http://192.168.1.179:8080/#/";
    /**
     * oss
     */
    public static final String IMAGE_PATH_SERVER = "images/";//保存到服务端的路径


    /**
     * 万能钥匙
     */
//获取楼号
    public static String UNIVERSIAL_KEY_GET_TOWER_NO = "/roomUtils/getTowerList";
    //获取单元号
    public static String UNIVERSIAL_KEY_GET_CELL_NO = "/roomUtils/getCellList";
    //获取大门门禁列表
    public static String UNIVERSIAL_KEY_GET_DOOR_NO = "/openLockDevice/getLockDeviceList";
    //根据单元门开门
    public static String UNIVERSIAL_KEY_OPEN_BY_CELL = "/openLockDevice/openCellLockDevice";
    //根据设备id开门
    public static String UNIVERSIAL_KEY_OPEN_BY_DEVICE_NO = "/openLockDevice/openLockDevice";
    //获取房间列表
    public static String UNIVERSIAL_GTEROOM = "/roomUtils/getRoomList";
    //根据房间号查询房间list（全匹配查询）
    public static String SEARCH_ROOM_NUM = "/roomUtils/getRoomListByRoomNumber";
    /**
     * 业主信息
     */
    //获取业主信息
    public static String OWNERME_MESSAGE = "/owner/getOwnerMsg";
    //获取水费列表
    public static String WATER_BILL_LiST = "/owner/orderDetailList";
    //获取物业费列表
    public static String PROPRET_LIST = "/owner/propertyOrderList";
    //获取车辆信息
    public static String CAR_MESSAGE = "/owner/carList";
    //违停记录
    public static String CAR_ILLEGALLY = "/owner/carErrorStayList";

    /**
     * 登录
     */
//手机号登录
    public static String LOGIN_BY_MOBILE = "/userCenter/mobileLogin";
    //获取短信验证码
    public static String GEI_SMS_CODE = "/userCenter/getSmsCode";
    //短信验证类型
    public static String SMS_CODE_TYPE_LOGIN = "PM_APP_LOGIN";
    //短信验证类型
    public static String SMS_CODE_TYPE_MODIFY_MOBILE = "PM_UPDATEMOBILE";

    //获取企业列表
    public static String GET_PROPERTY_LIST = "/userCenter/propertyList";
    //获取用户企业关系
    public static String GET_USER_PROPERTY_RELATION = "/userCenter/getUserDeptInfo";
    //选择企业
    public static String UPLOAD_SELECTED_PROPERTY = "/userCenter/setProperty";
    //获取小区列表
    public static String GET_VILLAGE_LIST = "/userCenter/villageList";


    /**
     * 内部通知
     */
    //获取消息列表
    public static String NOTICE_LIST = "/pmNotice/pmNoticeList";
    //获取消息详情
    public static String NOTICE_INFO = "/pmNotice/readPmNotice";
    //保存消息
    public static String SAVE_NOTICE = "/pmNotice/savePmNotice";


    /**
     * 个人中心
     */

    //获取oss token
    public static String GET_OSS_TOKEN = "/userCenter/getOssToken";
    //获取用户详情
    public static String GET_USER_INFO_DETAIL = "/userCenter/getUserVO";
    //修改用户头像
    public static String MODIFY_USER_PIC = "/userCenter/updateUser";
    //修改用户头像
    public static String MODIFY_USER_MOBILE = "/userCenter/updateMobile";
    //修改用户头像
    public static String MINE_SUGGUEST = "/userCenter/suggest";
    //用户权限菜单
    public static String USER_MENU = "/userCenter/getUserRoleMenuList";


    /**
     * 组织架构
     */

    //获取角色列表
    public static String GET_ROLE_LIST = "/dept/getRoleByVillage";
    //获取成员从角色列表
    public static String GET_USERS_FROM_ROLE_LIST = "/dept/getUserByRole";
    //查询成员
    public static String SEARCH_USERS_FROM_ROLE_LIST = "/dept/getUserBySearchKey";


    /**
     * 临时任务
     */
    //待处理任务列表
    public static String WAIT_TO_DEAL_TASK = "/taskTemporary/todoTaskList";
    //我发起的任务列表
    public static String I_PUBLISHED_TASK = "/taskTemporary/startTaskList";
    //与我相关任务列表
    public static String RELATED_TASK = "/taskTemporary/relationTaskList";
    //已完成任务列表
    public static String COMPLETED_TASK = "/taskTemporary/completeTaskList";
    //发布任务
    public static String PUBLISH_TASK = "/taskTemporary/save";
    //获取任务详情
    public static String GET_TASK_DETAIL = "/taskTemporary/getTask";
    //转发任务
    public static String TRANSFER_TASK = "/taskTemporary/transferTask";
    //完成任务
    public static String COMPLETE_TASK = "/taskTemporary/completeTask";
    //放弃任务
    public static String GIVE_UP_TASK = "/taskTemporary/abandonTask";
    //审核通过
    public static String TASK_AGREE = "/taskTemporary/checkAgree";
    //驳回请求
    public static String TASK_REJECT = "/taskTemporary/checkReject";
    //修改任务
    public static String TASK_UPDATA = "/taskTemporary/edit";
    /**
     * 考勤打卡
     */

    //排班打卡月历
    public static String ARRANGE_CALENDAR = "/pmAttendanceClock/arrangeMonthCalendar";
    //排班详情
    public static String ARRANGE_USER_DETAIL = "/pmAttendanceClock/arrangeUserDetail";
    //打卡
    public static String CHECK_WORK_SIGN = "/pmAttendanceClock/clock";
    //获取打卡人信息
    public static String CHECK_WORK_GET_USER_INFO = "/pmAttendanceClock/getClockMsg";
    //每天考勤记录
    public static String SHIFT_RECORD_EVERYDAY = "/pmAttendanceClock/clockList";
    //考勤统计
    public static String CHECK_WORK_SIGN_STATICS = "/pmAttendanceClock/clockStatis";
    //补卡
    public static String CHECK_WORK_SIGN_REPLACE = "/pmAttendanceClock/replaceClock";
    //更新打卡
    public static String UPDATE_CLOCK = "/pmAttendanceClock/updateClock";
    //考勤规则
    public static String ATTENDANCE_RULES = "/pmAttendanceClock/attendanceRule";
    //准备补卡信息
    public static String READY_FILLCARD = "/pmAttendanceClock/toReplaceClock";
    // 补卡
    public static String REPLACE_CLOCK = "/pmAttendanceClock/replaceClock";
    /**
     * 车牌
     */

//获取车辆信息
    public static String GET_CAR_INFO = "/car/getCarMsg";
    //保存违章信息
    public static String SAVE_CAR_ERRO_INFO = "/car/saveCarErrorStay";


    /**
     * 水表抄录
     */

    //获取房间记录列表
    public static String GET_EOOM_RECORD_LIST = "/waterAmmeterFee/roomList";

    //获取房间记录列表
    public static String GET_EOOM_RECORD = "/waterAmmeterFee/roomWatchNumber";

    //获取房间记录列表
    public static String SAVE_WATER_RECORD = "/waterAmmeterFee/saveRoomWatchNumber";


    /**
     * 内部报修
     */
    //获取内部报修菜单
    public static String INNER_REPAIR_MENU = "/serviceWork/repairLabelList";
    //保存报修内容
    public static String SAVE_INNER_REPAIR = "/serviceWork/savePmRepair";


    /**
     * 小红点
     */

    //获取未读数量
    public static String GET_UNREAD_AMOUNT = "/taskTemporary/getUnreadNum";
    //未读数量清零
    public static String CLEAR_UNREAD_AMOUNT = "/taskTemporary/removeUnreadNum";

    /**
     * 表单任务
     */
    //待处理表单任务
    public static String FORM_TASK_TO_DO = "/formTask/todoTaskList";
    //待处理表单任务详情
    public static String FORM_TASK_DETAIL = "/formTask/taskDetail";
    //保存日常任务
    public static String SAVE_COMMON_FORM_TASK = "/formTask/saveCommonFormTask";
    //保存工具类任务
    public static String SAVE_TOOL_FORM_TASK = "/formTask/saveToolFormTask";
    //签批日常表单或者工具类表单
    public static String SIGN_COMMON_FORM_TASK = "/formTask/checkFormTask";
    //签批自动表单
    public static String SIGN_AUTO_FORM_TASK = "/formTask/checkAutoFormTask";
    //抄送我的
    public static String FORM_TASK_COPY_TO_ME = "/formTask/ccTaskList";
    //抄送我的
    public static String FORM_TASK_SINGED_BY_ME = "/formTask/checkTaskList";
    //我提交的
    public static String FORM_TASK_COMMIT_BY_ME = "/formTask/startTaskList";
    //自动表单 任务列表
    public static String AUTO_FORM_TASK_LIST = "/formTask/autoCheckTaskList";
    //获取工具类表单列表  在工作台展示
    public static String GET_TOOL_FORM_LIST = "/formTask/toolFormList";

    /**
     * 系统消息
     */
    //获取消息
    public static String GET_SYS_NOTICE = "/systemMsg/msgList";
    //获取未读消息数量
    public static String GET_SYS_NOTICE_UNREAD = "/systemMsg/getUnreadNum";
    //删除未读消息数量
    public static String RV_SYS_NOTICE_UNREAD = "/systemMsg/removeUnreadNum";


    /**
     * 巡检巡更表单
     */
    //巡查巡检接口
    public static String GET_PATROL_FORM_LIST = "/formTask/patrolFormList";
    //保存巡查表单任务
    public static String SAVE_PATROL_FORM_TASK = "/formTask/savePatrolFormTask";
    //保存巡查表单任务记录
    public static String SAVE_PATROL_FORM_TASK_RECORD = "/formTask/savePatrolTaskRecord";
    //编辑巡查表单任务记录
    public static String EDIE_PATROL_FORM_TASK_RECORD = "/formTask/updatePatrolTaskRecord";
    //获取正在填写表单列表
    public static String GET_FORMFILLING_LIST = "/formTask/writePatrolFormTaskList";
    //获取已经上交的表单列表
    public static String GET_SUBMIT_LIST = "/formTask/submitPatrolFormTaskList";
    //获取巡检表单任务记录
    public static String GET_PATROL_FORM_RECORD_LIST = "/formTask/patrolTaskRecordList";
    //上交表单
    public static String SUBMIT_FROM = "/formTask/submitPatrolFormTask";
    //继续填写
    public static String CONTINUE_FORM = "/formTask/writePatrolFormTask";
    //更新巡检巡查表单记录
    public static String UPDATA_FORM = "/formTask/editPatrolFormTask";


    /**
     * 工单
     */

    //待验收工单
    public static String WAIT_CHECK_ORDER = "/serviceWork/checkServiceWorkList";
    //已完成工单
    public static String FINISHED_ORDERS = "/serviceWork/completeServiceWorkList";
    //未指派工单
    public static String UNASSIGN_ORDER = "/serviceWork/notAssignedServiceWorkList";
    //抄送我的
    public static String COPYED_TO_ME_ORDER = "/serviceWork/relationServiceWorkList";
    //我创建的
    public static String I_CREATED_ORDER = "/serviceWork/startServiceWorkList";
    //待跟进
    public static String WAIT_FOLLOW_ORDER = "/serviceWork/todoServiceWorkList";
    //新建工单
    public static String CREATE_ORDER = "/serviceWork/save";
    //修改工单内容
    public static String UPDATE_ORDER = "/serviceWork/updateContent";
    //修改工单回执
    public static String UPDATA_RECEIPT = "/serviceWork/updateReceipt";
    //激活工单
    public static String ACTIVITE_WORK_ORDER = "/serviceWork/activate";
    //删除工单
    public static String DELETE_WORK_ORDER = "/serviceWork/remove";


    //获取工单未读数
    public static String GET_WORK_ORDER_UNREAD_NUM = "/serviceWork/getUnreadNum";
    //移除小红点
    public static String DEL_WORK_ORDER_UNREAD_NUM = "/serviceWork/removeUnreadNum";
    //工单详情
    public static String WORK_ORDER_DETAIL = "/serviceWork/detail";
    //审核通过
    public static String WORK_ORDER_DETAIL_AGREE = "/serviceWork/checkAgree";
    //审核不通过
    public static String WORK_ORDER_DETAIL_REJECT = "/serviceWork/checkReject";
    //工单详情完成
    public static String WORK_ORDER_DETAIL_FINISH = "/serviceWork/finish";
    //写进展
    public static String WORK_ORDER_WRITE_PROGRESS = "/serviceWork/progress";

    /**
     * 访客申请
     */

    //访客列表
    public static String PROPERTY_RECORD_LIST = "/visitor/visitorList";
    //申请通过
    public static String PROPERTY_RECORD_CHECKAGREE = "/visitor/checkAgree";
    //驳回
    public static String PROPERTY_RECORD_CHECKREJECT="/visitor/checkReject";

}
