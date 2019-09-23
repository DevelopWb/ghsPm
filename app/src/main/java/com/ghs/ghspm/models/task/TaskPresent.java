package com.ghs.ghspm.models.task;

import android.util.Log;

import com.ghs.ghspm.base.BasePresent;
import com.ghs.ghspm.base.network.networkProxy.HttpProxy;
import com.ghs.ghspm.base.network.okgo.NetCallBackInterface;
import com.ghs.ghspm.bean.TaskInfoBean;
import com.ghs.ghspm.bean.TasksBean;
import com.ghs.ghspm.tools.Contract;
import com.ghs.ghspm.tools.GsonManager;
import com.ghs.ghspm.tools.UserInfoUtil;

import java.util.Map;

/**
 * Author:wang_sir
 * Time:2018/6/29 10:17
 * Description:This is WorkDeskPresent
 */
public class TaskPresent extends BasePresent<TaskContract.ITaskView> implements TaskContract.ITaskPresent {


    @Override
    public void getTasks(String taskType, int userId, int offset, int limit, final String tag) {

        if (0 == offset) {
            if (getView() != null) {
                getView().startLoading(tag);
            }
        }
        HttpProxy.getInstance()
                .params("userId", userId)
                .params("offset", offset)
                .params("limit", limit)
                .params("villageId", UserInfoUtil.getInstance().getVillageId())


                .getToNetwork(taskType, new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, TasksBean.class), tag);
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
    public void publishTask(String content, String checkUserId, int propertyId, int createUserId, String createUserName, String handlerUsersId, String ccUsersId, Map<String, String> map, final String tag) {

        HttpProxy.getInstance()
                .params("content", content)
                .params("checkUserId", checkUserId)
                .params("propertyId", propertyId)
                .params("createUserId", createUserId)
                .params("createUserName", createUserName)
                .params("handlerUsersId", handlerUsersId)
                .params("ccUsersId", ccUsersId)
                .params("villageId", UserInfoUtil.getInstance().getVillageId())

                .params(map)
                .postToNetwork(Contract.PUBLISH_TASK, new NetCallBackInterface() {
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
    public void getTaskDetailInfo(int taskId, int userId, final String tag) {

        HttpProxy.getInstance()
                .params("taskId", taskId)
                .params("userId", userId)
                .getToNetwork(Contract.GET_TASK_DETAIL, new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, TaskInfoBean.class), tag);
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
    public void dealTask(String urlPath, int taskId, int userId, String remark, String picture, final String tag) {

        HttpProxy.getInstance()
                .params("userId", userId)
                .params("taskId", taskId)
                .params("remark", remark)
                .params("picture", picture)
                .getToNetwork(urlPath, new NetCallBackInterface() {
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
    public void transferTask(int taskId, int userId, int toUserId, String remark, String picture, final String tag) {

        HttpProxy.getInstance()
                .params("taskId", taskId)
                .params("userId", userId)
                .params("toUserId", toUserId)
                .params("remark", remark)
                .params("picture", picture)
                .getToNetwork(Contract.TRANSFER_TASK, new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView("转发成功", tag);
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
    public void updataTask(int taskId, int updateUserId, Long checkUserId, String handlerUsersId, String ccUsersId, final String tag) {
        Log.i("TAG",updateUserId+"---"+taskId+"---"+checkUserId+"---"+handlerUsersId+"----"+ccUsersId);

        HttpProxy.getInstance()
                .params("id", taskId)
                .params("updateUserId", updateUserId)
                .params("checkUserId", checkUserId)
                .params("handlerUsersId", handlerUsersId)
                .params("ccUsersId", ccUsersId)
                .postToNetwork(Contract.TASK_UPDATA, new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {

                        Log.i("TAG",content);
                        if (getView() != null) {
                            getView().updateView("修改成功", tag);
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
