package com.ghs.ghspm.models.workdesk.signAndTable;

import com.ghs.ghspm.base.network.RequestStatus;
import com.ghs.ghspm.base.network.networkProxy.HttpProxy;
import com.ghs.ghspm.base.network.okgo.NetCallBackInterface;
import com.ghs.ghspm.bean.TableTaskOutLineBean;
import com.ghs.ghspm.tools.Contract;
import com.ghs.ghspm.tools.GsonManager;
import com.ghs.ghspm.tools.UserInfoUtil;

/**
 * Author:wang_sir
 * Time:2019/1/2 11:04
 * Description:This is SignTableModel
 */
public class SignTableModel implements SignTableContrct.ISignTableModel {
    @Override
    public void getTableTasks(int userId,final  String tag,final  RequestStatus requestStatus) {
        HttpProxy.getInstance()
                .params("userId", userId)
                .params("villageId", UserInfoUtil.getInstance().getVillageId())
                .postToNetwork(Contract.FORM_TASK_TO_DO,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (requestStatus != null) {
                            requestStatus.onSuccess(GsonManager.getInstance().parseJsonToBean(content, TableTaskOutLineBean.class), tag);
                        }
                    }

                    @Override
                    public void onError(String str) {
                        if (requestStatus != null) {
                            requestStatus.onError(str);
                        }
                    }
                });
    }
}
