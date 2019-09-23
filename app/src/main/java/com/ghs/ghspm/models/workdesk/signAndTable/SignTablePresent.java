package com.ghs.ghspm.models.workdesk.signAndTable;

import com.ghs.ghspm.base.BasePresent;
import com.ghs.ghspm.base.network.RequestStatus;
import com.ghs.ghspm.base.network.networkProxy.HttpProxy;
import com.ghs.ghspm.base.network.okgo.NetCallBackInterface;
import com.ghs.ghspm.bean.AutoFormBean;
import com.ghs.ghspm.bean.DynamicLayoutBean;
import com.ghs.ghspm.bean.FormTaskRecordBean;
import com.ghs.ghspm.bean.PatrolFormTaskBean;
import com.ghs.ghspm.bean.TableMultipleItem;
import com.ghs.ghspm.bean.TableTaskDetailBean;
import com.ghs.ghspm.tools.Contract;
import com.ghs.ghspm.tools.GsonManager;
import com.ghs.ghspm.tools.PubUtil;
import com.ghs.ghspm.tools.StrUtils;
import com.ghs.ghspm.tools.UserInfoUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:wang_sir
 * Time:2019/1/2 11:11
 * Description:This is SignTablePresent
 */
public class SignTablePresent  extends BasePresent<SignTableContrct.ISignTableView> implements SignTableContrct.ISignTablePresent,RequestStatus {

    private final SignTableModel signTableModel;

    public SignTablePresent() {

        signTableModel = new SignTableModel();
    }

    @Override
    public void onStart(String tag) {
    }

    @Override
    public void onSuccess(Object o, String tag) {
        if (getView() != null) {
            getView().updateView(o,tag);
        }
    }

    @Override
    public void onError(String tag) {
        if (getView() != null) {
            getView().onError(tag);
        }
    }

    @Override
    public void getTableTasks(int userId, String tag) {
        signTableModel.getTableTasks(userId,tag,this);
    }

    @Override
    public void getTableTaskDetail(int recordId, int formType, int status,final  String tag) {
        HttpProxy.getInstance()
                .params("recordId", recordId)
                .params("formType", formType)
                .params("status", status)
                .postToNetwork(Contract.FORM_TASK_DETAIL,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, TableTaskDetailBean.class), tag);
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
    public void saveCommonFormTask(int recordId, String contentJson, int handler, final String tag) {
        HttpProxy.getInstance()
                .params("recordId", recordId)
                .params("contentJson", contentJson)
                .params("handler", handler)
                .postToNetwork(Contract.SAVE_COMMON_FORM_TASK,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
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
    public void savePatrolFormTaskRecord(int villageId, int formId, int taskId, String contentJson, int handler,final String tag) {
        HttpProxy.getInstance()
                .params("villageId", villageId)
                .params("formId", formId)
                .params("taskId", taskId)
                .params("handler", handler)
                .params("contentJson", contentJson)
                .postToNetwork(Contract.SAVE_PATROL_FORM_TASK_RECORD,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            String  data = PubUtil.getServerData(content);
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(data, PatrolFormTaskBean.DataBean.class), tag);

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
    public void editPatrolFormTaskRecord(int recordId, final String contentJson, int handler,final String tag) {
        HttpProxy.getInstance()
                .params("recordId", recordId)
                .params("handler", handler)
                .params("contentJson", contentJson)
                .postToNetwork(Contract.EDIE_PATROL_FORM_TASK_RECORD,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            String  data = PubUtil.getServerData(content);
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(data, PatrolFormTaskBean.DataBean.class), tag);


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
    public void saveToolFormTask(int villageId, int formId, int handler, int checkUser, String ccUser, String contentJson, final String tag) {
        HttpProxy.getInstance()
                .params("villageId", villageId)
                .params("formId", formId)
                .params("handler", handler)
                .params("checkUser", checkUser)
                .params("ccUser", ccUser)
                .params("contentJson", contentJson)
                .postToNetwork(Contract.SAVE_TOOL_FORM_TASK,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
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
    public int getLayoutType(String cssClass) {
        int type = 0;
        if (!StrUtils.isStringValueOk(cssClass)) {
            return type;
        }
        switch (cssClass) {
            case "text":
                type = 1;
                break;
            case "textarea":
                type = 2;
                break;
            case "select":
                type = 3;
                break;
            case "multiple_select":
                type = 4;
                break;
            case "number":
                type = 5;
                break;
            case "money":
                type = 6;
                break;
            case "user":
                type = 7;
                break;
            case "copy_user":
                type = 8;
                break;
            case "check_user":
                type = 9;
                break;
            case "image":
                type = 10;
                break;
            case "date":
                type = 11;
                break;
            case "device":
                type = 12;
                break;
            case "tower":
                type = 13;
                break;
            case "cell":
                type = 14;
                break;
            case "room":
                type = 15;
                break;
            default:
                break;
        }
        return type;
    }

    @Override
    public List<TableMultipleItem> loadDynamicLayoutData(List<DynamicLayoutBean> arrays,String  formTitle) {
        List<TableMultipleItem> data = new ArrayList<>();
        if (arrays != null) {
            if (arrays.size() > 0) {
                if (StrUtils.isStringValueOk(formTitle)) {
                    DynamicLayoutBean bean = new DynamicLayoutBean("","","","",formTitle);
                    data.add(new TableMultipleItem(TableMultipleItem.TABLE_TEXT_SHOW, bean));
                }else{
                    DynamicLayoutBean bean = new DynamicLayoutBean("","","","","");
                    data.add(new TableMultipleItem(TableMultipleItem.TABLE_TEXT_SHOW, bean));
                }
                for (DynamicLayoutBean array : arrays) {
                    data.add(new TableMultipleItem(getLayoutType(array.getCssClass()), array));
                }
            }
        }
        return data;
    }

    @Override
    public void signCommonFormTask(int recordId, int checker, final String tag) {

        HttpProxy.getInstance()
                .params("recordId", recordId)
                .params("checker", checker)
                .postToNetwork(Contract.SIGN_COMMON_FORM_TASK,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
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
    public void formTaskCopyedToMe(int userId, int offset, int limit, String tag) {
        getRecordOfFormTask(Contract.FORM_TASK_COPY_TO_ME,userId,offset,limit,tag);
    }

    @Override
    public void formTaskSignedToMe(int userId, int offset, int limit, String tag) {
        getRecordOfFormTask(Contract.FORM_TASK_SINGED_BY_ME,userId,offset,limit,tag);


    }

    @Override
    public void formTaskCommitOfMine(int userId, int offset, int limit, String tag) {
        getRecordOfFormTask(Contract.FORM_TASK_COMMIT_BY_ME,userId,offset,limit,tag);


    }

    @Override
    public void getTaskListOfAutoForm(int autoCheckId, final String tag) {
        HttpProxy.getInstance()
                .params("autoCheckId", autoCheckId)
                .postToNetwork(Contract.AUTO_FORM_TASK_LIST,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, AutoFormBean.class), tag);
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
    public void signAutoForm(int autoCheckId, int checkUser, final String tag) {
        HttpProxy.getInstance()
                .params("autoCheckId", autoCheckId)
                .params("checkUser", checkUser)
                .postToNetwork(Contract.SIGN_AUTO_FORM_TASK,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
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

    /**
     * 获取记录
     * @param userId
     * @param offset
     * @param limit
     * @param tag
     */
    private  void  getRecordOfFormTask(String url ,int userId, int offset, int limit, final String tag){
        HttpProxy.getInstance()
                .params("userId", userId)
                .params("offset", offset)
                .params("limit", limit)
                .params("villageId", UserInfoUtil.getInstance().getVillageId())
                .postToNetwork(url,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, FormTaskRecordBean.class), tag);
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
