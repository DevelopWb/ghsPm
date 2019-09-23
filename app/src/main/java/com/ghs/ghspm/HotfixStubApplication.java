package com.ghs.ghspm;

import android.content.Context;
import android.support.annotation.Keep;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.taobao.sophix.PatchStatus;
import com.taobao.sophix.SophixApplication;
import com.taobao.sophix.SophixEntry;
import com.taobao.sophix.SophixManager;
import com.taobao.sophix.listener.PatchLoadStatusListener;


/**
 *
 * Author:wang_sir
 * Time:2019/3/26 15:45
 * Description:This is HotfixStubApplication
 */
public class HotfixStubApplication extends SophixApplication {
    private final String TAG = "HotfixStubApplication";

    private static String  HOTFIX_APPID = "25233966";
    private static String  HOTFIX_APP_SECRET = "f8d61a918d53024d081fb71133ed4b64";
    private static String  HOTFIX_RSA = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDD/SYSzAbUeJkCTzewAA+da/BKa0WfbKXZJVmA182uTbZOs3EgBA6nXqgIGHpHgsglFB8XxgRblw86GxMM3kIB/7+tRvhFI4pUJnasgdYTVDrOPUSXIVix5hxmqivea86iKx4jFD+nPz6aK9XFhdr686YOyH/clSl1AGCp5VdkG0pYBmKGPfLwYSEaa/S0btL8KhNxotQ3Lv1WwSyAiaElJH9I4n1Bpv8Np8zNarjGvy4gWsEcVS4ZzTTUpsGzzI14igRbOtIDGiBLdzx+lT6Qf4hcIx/5+hsSIlFtJsnQ1umYDpj+n+k3Dl/iPBqB12m1A2gCHOD0ehoQ0ZF+89qvAgMBAAECggEAZ8bNuDpMi4joDC8CH7GIkySqcd1nWklfcCG0yOJ8SIVhuT6O8hLz52/ZrZ+4b9EyRV/oLuBtQko0h+H6LmApt4FCb3Aa2hE9l91eDW4aetWo5bfPb+76VgiwlTQaUoXnPjtyk8SPB/IXJe98HEEKeMM29LD580UQVKHEjXGg2qIjfGTSLHPFODFWawHGYOPYUsPBupeUOhTX52WlNMNNw6p0tQDivrJJLoJRCd1xC7dMQG1nugkEmgg9YGhl/p3K4dCnZ/efgieEK4DtUZNm/MPROGJONvQCzBlE8/8OawnMDUdJ3IBNQMjJprZA0wPuhD5u3D52Uj15OVzn6zHrYQKBgQDh+RGABoL4LVGKWFlojN3GXXGkwA2er5NnN6TWivk+Pn/K+SsvxZw52obd74yRA1Kfm3mcJO70u7Lezds/IBuTnq5sw/jFO2iacB0kkZGpA2O1/GSQdFojZS0R04hVBFGfMq4nkfCD5PV7FcvMM2xhbDv8LGEwtELt/F+UxQfj5wKBgQDeCBxzibvw5ES6n3y5IpuH0+ihddVeeaCtPVSiIdKkveQDB2xpiQwzzlixSAVKnnZWx51+y0deTelMJrW+oj2Sh8EOG1CwoIN1OKoCx4St4S/X4/1dZIeQ3hqN1Qx9be4RHL8oFUAmptDFpDRoQ6vlLsoznc4VMaAIwI1Nchl5+QKBgAO7EvjkIqFqTiBUmRVKzhaKUGgB67ek+nAH0SbhoH/QlFeLiZT+VDf4TaW4YL+8KE8OSBG14mQT/T4EdAw0wX/MbODK036eHnn+Q9zosljx/2Fvy1E/OoW/E+FpdqTAr4BLW+i3Vhs3Wn4vu++hIbbyDIN7UBd3wmsfcYyJPDodAoGAPXPrRAM8NGlJqImaALfWalq5iW7Oto5dbXWLJjDMV3KDWF9Gn1MiQHh9TdwMMuH3CP/I1J44y54u0zfnl7LZ7eOLCHeA9tIGcHxVed11GJc6IbvllWLMrsj6eoUcJt81RCE7u0FedxQ2tm1DYBRS0GUJ7A1iS7VngOSid2v8JykCgYEAswiaCKgxQ4HepzOc2CI+Jq4ca/mgZEqXga7SsUfwHdGsYhkBK4NGcLAc75CQmvRlXNeHIbCQtln7+ajd3bMXr9Rdp4vzrez9b1uT3Ypgai2UaXAfJbkaymZ9SJOdTGKsoabT5pgUWRsQA1EBBsUn/ej+xcUpSkw701d4oBaN2fk=";
    // 此处SophixEntry应指定真正的Application，并且保证RealApplicationStub类名不被混淆。
    @Keep
    @SophixEntry(MyApplication.class)
    static class RealApplicationStub {}
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//         如果需要使用MultiDex，需要在此处调用。
         MultiDex.install(this);
        initSophix();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (!MyApplication.IsDeBugMoDe) {
            SophixManager.getInstance().queryAndLoadNewPatch();
        }

    }

    private void initSophix() {
        String appVersion = "0.0.0";
        try {
            appVersion = this.getPackageManager()
                    .getPackageInfo(this.getPackageName(), 0)
                    .versionName;
        } catch (Exception e) {
        }
        final SophixManager instance = SophixManager.getInstance();
        instance.setContext(this)
                .setAppVersion(appVersion)
                .setSecretMetaData(HOTFIX_APPID, HOTFIX_APP_SECRET, HOTFIX_RSA)
                .setEnableDebug(true)
                .setEnableFullLog()
                .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                    @Override
                    public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {
                        if (code == PatchStatus.CODE_LOAD_SUCCESS) {
                            Log.i(TAG, "sophix load patch success!");
                        } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
                            // 如果需要在后台重启，建议此处用SharePreference保存状态。
                            Log.i(TAG, "sophix preload patch success. restart app to make effect.");
                        }
                    }
                }).initialize();
    }
}
