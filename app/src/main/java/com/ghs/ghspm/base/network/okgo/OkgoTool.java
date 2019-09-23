package com.ghs.ghspm.base.network.okgo;

import com.ghs.ghspm.base.network.networkProxy.HttpStaticProxyInterface;
import com.ghs.ghspm.tools.Contract;
import com.ghs.ghspm.tools.PubUtil;
import com.ghs.ghspm.tools.UserInfoUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.Map;

/**
 * Author:wang_sir
 * Time:2019/4/3 11:36
 * Description:This is OkgoTool  网络请求库的封装
 */
public class OkgoTool<T> implements HttpStaticProxyInterface {
    @Override
    public void postToNetwork(String urlPath, Map map, final NetCallBackInterface netCallBackInterface) {
        OkGo.<String>post(Contract.BASE_URL + urlPath)
                .params(map, false)
                .headers("Authorization", "bearer " + UserInfoUtil.getInstance().getUserToken())
                .headers("api-version", "1.5.0")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String content = response.body();
                        requestOnSuccess(content, netCallBackInterface, PubUtil.getServerMessage(content));
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        requestOnError(response, netCallBackInterface);
                    }
                });
    }


    @Override
    public void getToNetwork(String urlPath, Map map, final NetCallBackInterface netCallBackInterface) {
        OkGo.<String>get(Contract.BASE_URL + urlPath)
                .params(map, false)
                .headers("Authorization", "bearer " + UserInfoUtil.getInstance().getUserToken())
                .headers("api-version", "1.5.0")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String content = response.body();
                        requestOnSuccess(content, netCallBackInterface, PubUtil.getServerMessage(content));
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        requestOnError(response, netCallBackInterface);
                    }
                });
    }


    @Override
    public void getToNetwork(String urlPath, Map map, final NetCallBackInterface netCallBackInterface, boolean returnErrorCode) {
        OkGo.<String>get(Contract.BASE_URL + urlPath)
                .params(map, false)
                .headers("Authorization", "bearer " + UserInfoUtil.getInstance().getUserToken())
                .headers("api-version", "1.5.0")

                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String content = response.body();
                        requestOnSuccess(content, netCallBackInterface, String.valueOf(PubUtil.getServerCode(content)));
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        requestOnError(response, netCallBackInterface);
                    }
                });
    }

    @Override
    public void postToNetwork(String urlPath, Map map, final NetCallBackInterface netCallBackInterface, boolean returnErrorCode) {
        OkGo.<String>post(Contract.BASE_URL + urlPath)
                .params(map, false)
                .headers("Authorization", "bearer " + UserInfoUtil.getInstance().getUserToken())
                .headers("api-version", "1.5.0")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String content = response.body();
                        requestOnSuccess(content, netCallBackInterface, String.valueOf(PubUtil.getServerCode(content)));
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        requestOnError(response, netCallBackInterface);
                    }
                });
    }

    /**
     * 请求异常
     *
     * @param response
     * @param netCallBackInterface
     */
    private void requestOnError(Response<String> response, NetCallBackInterface netCallBackInterface) {
        if (netCallBackInterface != null) {
            if (response.body() != null) {
                netCallBackInterface.onError(response.body());
            } else {
                netCallBackInterface.onError("服务器错误");

            }
        }
    }

    /**
     * 请求成功
     *
     * @param content
     * @param netCallBackInterface
     * @param errMsg
     */
    private void requestOnSuccess(String content, NetCallBackInterface netCallBackInterface, String errMsg) {

        if (PubUtil.initContent(content)) {
            if (netCallBackInterface != null) {
                if (netCallBackInterface != null) {
                    netCallBackInterface.onSuccess(content);
                }
            }
        } else {
            if (netCallBackInterface != null) {
                netCallBackInterface.onError(errMsg);
            }
        }
    }
}
