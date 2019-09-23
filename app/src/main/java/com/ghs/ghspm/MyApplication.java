package com.ghs.ghspm;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;

import com.alibaba.sdk.android.push.register.HuaWeiRegister;
import com.alibaba.sdk.android.push.register.MiPushRegister;
import com.ghs.ghspm.base.network.networkProxy.HttpProxy;
import com.ghs.ghspm.base.network.okgo.OkGoInterceptor;
import com.ghs.ghspm.base.network.okgo.OkgoTool;
import com.ghs.ghspm.models.push.AliPushManager;
import com.ghs.ghspm.tools.BuglyConfig;
import com.ghs.ghspm.tools.Contract;
import com.ghs.ghspm.tools.CrashHandler;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.orhanobut.hawk.Hawk;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

import java.util.concurrent.TimeUnit;

import cn.com.mark.multiimage.core.PreferencesUtils;
import okhttp3.OkHttpClient;

/**
 * Author:wang_sir
 * Time:2018/7/7 18:34
 * Description:This is MyApplication
 */
public class MyApplication extends Application {
    private static final String MIPUSH_APP_ID = "2882303761517881839";
    private static final String MIPUSH_APP_KEY = "5281788166839";
    public   static final boolean IsDeBugMoDe = false;//是否是调试模式,上线的时候改成false就可以了
    public   static final boolean OpenRefWatcher = false;//开启leakCanary
    private RefWatcher refWatcher;
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        HttpProxy.getInstance().initNetProxyType(new OkgoTool());
        //仿微信选择照片的（PicSelecterLibrary）存储数据的sp初始化
        PreferencesUtils.init(this, "multiimage");
        setOkGoConfig();
        /**
         * 需要在buyly初始化之前初始化 要不影响bugly异常的上报
         */
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());

        if (!IsDeBugMoDe) {
            BuglyConfig.init(this);
            Contract.BASE_URL = Contract.BASE_URL_RELEASE;
        }else{
            Contract.BASE_URL = Contract.BASE_URL_DEBUG;
        }

        Hawk.init(this).build();
        initUMConfig();
        //初始化阿里推送
        AliPushManager.getInstance().initPush(getApplicationContext());
        //华为辅助推送通道
        HuaWeiRegister.register(this);
        // 小米辅助推送通道
        MiPushRegister.register(this, MIPUSH_APP_ID, MIPUSH_APP_KEY);
        initNotification();
        if (OpenRefWatcher) {
            if (LeakCanary.isInAnalyzerProcess(this)) {
                //此过程专用于LeakCanary进行堆分析。在此过程中不应初始化应用程序。
                return;
            }
            refWatcher = LeakCanary.install(this);
        }

    }
    public static RefWatcher getRefWatcher(Context context){
        MyApplication application = (MyApplication)context.getApplicationContext();
        return application.refWatcher;
    }
    /**
     * 获取App上下文
     * @return
     */
    public static Context getAppContext() {
        return MyApplication.mContext;
    }
    /**
     * 定义notification渠道
     */
    private void initNotification() {
        createNotificationChannel(AliPushManager.PUSH_PM_INNER_NOTICE,"内部消息","物管发布内部通知", NotificationManager.IMPORTANCE_HIGH);
        createNotificationChannel(AliPushManager.PUSH_FORM_TASK,"表单消息","物管发布的表单任务通知",NotificationManager.IMPORTANCE_HIGH);
        createNotificationChannel(AliPushManager.PUSH_ARRANGE_NOTICE,"排班消息","物管发布的排班通知",NotificationManager.IMPORTANCE_HIGH);
        createNotificationChannel(AliPushManager.PUSH_WORK_ORDER_NOTICE,"工单消息","物管发布的工单通知",NotificationManager.IMPORTANCE_HIGH);
    }

    /**
     * 配置okgo
     */
    private void setOkGoConfig() {
        //OKGO
        /**
         * 配置OkHttpClient
         */
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(3000, TimeUnit.SECONDS)
                .writeTimeout(3000, TimeUnit.SECONDS)
                .addInterceptor(new OkGoInterceptor("TokenInterceptor"))//添加获取token的拦截器
                .build();
        OkGo.getInstance().init(this)                       //必须调用初始化
                .setOkHttpClient(client)               //建议设置OkHttpClient，不设置将使用默认的
                .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(3);                           //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
//                .addCommonHeaders(headers)                      //全局公共头
//                .addCommonParams(params);
    }
    /**
     * 初始化友盟
     */
    private void initUMConfig() {
        MobclickAgent.openActivityDurationTrack(false);
//        //初始化组件化基础库, 统计SDK/推送SDK/分享SDK都必须调用此初始化接口
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "");
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    /**
     * 针对8.0及以上系统消息通知收不到时的处理
     * @param channelId
     * @param channelName
     * @param channelDes
     * @param ImportantLevel
     */
    private void createNotificationChannel(String  channelId,String  channelName,String  channelDes,int ImportantLevel) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            // 通知渠道的id
            String id = channelId;
            // 用户可以看到的通知渠道的名字.
            CharSequence name = channelName;
            // 用户可以看到的通知渠道的描述
            String description = channelDes;
            int importance = ImportantLevel;
            NotificationChannel mChannel = new NotificationChannel(id, name, importance);
            // 配置通知渠道的属性
            mChannel.setDescription(description);
            // 设置通知出现时的闪灯（如果 android 设备支持的话）
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            // 设置通知出现时的震动（如果 android 设备支持的话）
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            //最后在notificationmanager中创建该通知渠道
            mNotificationManager.createNotificationChannel(mChannel);
        }
    }



}
