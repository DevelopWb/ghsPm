package com.ghs.ghspm.models.workdesk.patrolForm;

import android.util.Log;

import com.ghs.ghspm.base.BasePresent;
import com.ghs.ghspm.base.network.networkProxy.HttpProxy;
import com.ghs.ghspm.base.network.okgo.NetCallBackInterface;
import com.ghs.ghspm.bean.PatrolFormListBean;
import com.ghs.ghspm.bean.PatrolFormTaskBean;
import com.ghs.ghspm.bean.PatrolTaskFormListBean;
import com.ghs.ghspm.tools.Contract;
import com.ghs.ghspm.tools.GsonManager;
import com.ghs.ghspm.tools.PubUtil;
import com.ghs.ghspm.tools.StrUtils;

import java.util.Map;

/**
 * Author:wang_sir
 * Time:2019/4/23 14:12
 * Description:This is PatrolCheckUpdatePresent
 */
public class PatrolCheckUpdatePresent extends BasePresent<PatrolCheckUpdateContract.IPatrolCheckUpdateView> implements PatrolCheckUpdateContract.IPatrolCheckUpdatePresent {

    @Override
    public void getPatrolFormList(int propertyId, int villageId, int userId, final String tag) {
        HttpProxy.getInstance()
                .params("propertyId", propertyId)
                .params("villageId", villageId)
                .params("userId", userId)
                .postToNetwork(Contract.GET_PATROL_FORM_LIST, new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {

                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, PatrolFormListBean.class), tag);

                        }
                    }

                    @Override
                    public void onError(String str) {
                        if (getView() != null) {
                            getView().onError(str);
                        }
                    }
                });
    }

    @Override
    public void savePatrolFormTask(Map<String, String> map, final String tag) {
        HttpProxy.getInstance()
                .params(map)
                .postToNetwork(Contract.SAVE_PATROL_FORM_TASK, new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            String data = PubUtil.getServerData(content);
                            if (StrUtils.isStringValueOk(data)) {
                                getView().updateView(GsonManager.getInstance().parseJsonToBean(data, PatrolTaskFormListBean.DataBean.class), tag);

                            }

                        }
                    }

                    @Override
                    public void onError(String str) {
                        if (getView() != null) {
                            getView().onError(str);
                        }
                    }
                });
    }

    @Override
    public void getFormFillingList(int villageId, int formId, final String tag) {


        HttpProxy.getInstance()
                .params("formId", formId)
                .params("villageId", villageId)
                .postToNetwork(Contract.GET_FORMFILLING_LIST, new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        Log.i("TAG", content);
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, PatrolTaskFormListBean.class), tag);

                        }
                    }

                    @Override
                    public void onError(String str) {
                        if (getView() != null) {
                            getView().onError(str);
                        }
                    }
                });


    }

    @Override
    public void getPatrolFormTaskRecordList(int taskId, final String tag) {
        HttpProxy.getInstance()
                .params("taskId", taskId)
                .postToNetwork(Contract.GET_PATROL_FORM_RECORD_LIST, new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, PatrolFormTaskBean.class), tag);

                        }
                    }

                    @Override
                    public void onError(String str) {
                        if (getView() != null) {
                            getView().onError(str);
                        }
                    }
                });


    }

    @Override
    public void getFormSubmitList(int villageId, int formId, final String tag) {

        HttpProxy.getInstance()
                .params("formId", formId)
                .params("villageId", villageId)
                .postToNetwork(Contract.GET_SUBMIT_LIST, new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        Log.i("TAG", content);
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, PatrolTaskFormListBean.class), tag);

                        }
                    }

                    @Override
                    public void onError(String str) {
                        if (getView() != null) {
                            getView().onError(str);
                        }
                    }
                });


    }

    @Override
    public void submitForm(int taskId, int handlerId, final String tag) {
        HttpProxy.getInstance()
                .params("taskId", taskId)
                .params("handler", handlerId)
                .postToNetwork(Contract.SUBMIT_FROM, new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        Log.i("TAG", content);
                        if (getView() != null) {
                            getView().updateView("上交表单成功", tag);

                        }
                    }

                    @Override
                    public void onError(String str) {
                        if (getView() != null) {
                            getView().onError(str);
                        }
                    }
                });

    }


    @Override
    public void continueForm(int taskId, int handler,final String tag) {


        HttpProxy.getInstance()
                .params("taskId", taskId)
                .params("handler", handler)
                .postToNetwork(Contract.CONTINUE_FORM, new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        Log.i("TAG", content);
                        if (getView() != null) {
                            getView().updateView("", tag);

                        }
                    }

                    @Override
                    public void onError(String str) {
                        if (getView() != null) {
                            getView().onError(str);
                        }
                    }
                });

    }


    @Override
    public void updataForm(int id, String taskName, int formId,
                           String showDateValue, String headerTitle1Value, String headerTitle2Value,
                           String headerTitle3Value, String headerTitle4Value,
                           String footerTitle1Value, String footerTitle2Value,
                           String footerTitle3Value, String footerTitle4Value, String updateUserId, final String tag) {


        HttpProxy.getInstance()
                .params("id", id)
                .params("taskName", taskName)
                .params("formId", formId)
                .params("showDateValue", showDateValue)
                .params("headerTitle1Value", headerTitle1Value)
                .params("headerTitle2Value", headerTitle2Value)
                .params("headerTitle3Value", headerTitle3Value)
                .params("headerTitle4Value", headerTitle4Value)
                .params("footerTitle1Value", footerTitle1Value)
                .params("footerTitle2Value", footerTitle2Value)
                .params("footerTitle3Value", footerTitle3Value)
                .params("footerTitle4Value", footerTitle4Value)
                .params("updateUserId", updateUserId)
                .postToNetwork(Contract.UPDATA_FORM, new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        Log.i("TAG","修改"+ content);
                        if (getView() != null) {
                            String data = PubUtil.getServerData(content);
                            if (StrUtils.isStringValueOk(data)) {
                                getView().updateView(GsonManager.getInstance().parseJsonToBean(data, PatrolTaskFormListBean.DataBean.class), tag);
                            }

                        }
                    }

                    @Override
                    public void onError(String str) {
                        if (getView() != null) {
                            getView().onError(str);
                        }
                    }
                });


    }


}
