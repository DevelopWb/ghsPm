package com.ghs.ghspm.models.workdesk.publicfuction.notice;

import com.ghs.ghspm.base.BasePresent;
import com.ghs.ghspm.base.network.networkProxy.HttpProxy;
import com.ghs.ghspm.base.network.okgo.NetCallBackInterface;
import com.ghs.ghspm.bean.NoticeBean;
import com.ghs.ghspm.bean.NoticeDetailBean;
import com.ghs.ghspm.bean.RoleBean;
import com.ghs.ghspm.bean.section.SectionBeanNew;
import com.ghs.ghspm.tools.Contract;
import com.ghs.ghspm.tools.GsonManager;
import com.ghs.ghspm.tools.UserInfoUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:wang_sir
 * Time:2018/9/29 11:15
 * Description:This is NoticePresent
 */
public class NoticePresent extends BasePresent<NoticeContract.INoticeView> implements NoticeContract.INoticePresent {
    @Override
    public void getNoticeList(int offset, int limit, int type, int villageId, int pmUserId, final String tag) {

        HttpProxy.getInstance()
                .params("offset", offset)
                .params("limit", limit)
                .params("type", type)
                .params("villageId", villageId)
                .params("pmUserId", pmUserId)
                .postToNetwork(Contract.NOTICE_LIST, new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, NoticeBean.class), tag);
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
    public void getNoticeInfo(int pmNoticeId, final String tag) {

        HttpProxy.getInstance()
                .params("pmNoticeId", pmNoticeId)
                .postToNetwork(Contract.NOTICE_INFO, new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, NoticeDetailBean.class), tag);

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
    public void publishNotice(int villageId, int pmUserId, String title, String content, String imageUrl, String deptIds, final String tag) {
        HttpProxy.getInstance()
                .params("villageId", villageId)
                .params("pmUserId", pmUserId)
                .params("title", title)
                .params("content", content)
                .params("imageUrl", imageUrl)
                .params("deptIds", deptIds)
                .postToNetwork(Contract.SAVE_NOTICE, new NetCallBackInterface() {
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
    public void getRoleList(final String tag) {
       final  UserInfoUtil userInfoUtil = UserInfoUtil.getInstance();
        HttpProxy.getInstance()
                .params("villageId", userInfoUtil.getVillageId())
                .postToNetwork(Contract.GET_ROLE_LIST, new NetCallBackInterface() {

                    @Override
                    public void onSuccess(String content) {
                        SectionBeanNew topSectionBean = null;
                        if (getView() != null) {
                            RoleBean roleBean = GsonManager.getInstance().parseJsonToBean(content, RoleBean.class);
                            List<SectionBeanNew> sectionBeanNewList = new ArrayList<>();

                            if (roleBean != null) {
                                List<RoleBean.DataBean> roles = roleBean.getData();
                                int num = 0;
                                if (roles != null && roles.size() > 0) {
                                    List<SectionBeanNew> roleList = new ArrayList<>();
                                    for (RoleBean.DataBean role : roles) {
                                       num +=role.getUserNum();
                                        SectionBeanNew sectionBeanNew = new SectionBeanNew(role.getRoleId(), role.getRoleName(), userInfoUtil.getVillageId(),role.getUserNum(),false, null);
                                        roleList.add(sectionBeanNew);
                                    }
                                    topSectionBean = new SectionBeanNew(userInfoUtil.getVillageId(), userInfoUtil.getVillageName(), -1,num,false, roleList);
                                }else{
                                    topSectionBean = new SectionBeanNew(userInfoUtil.getVillageId(), userInfoUtil.getVillageName(), -1,0,false, null);

                                }
                            }
                            sectionBeanNewList.add(topSectionBean);
                            getView().updateView(sectionBeanNewList, tag);
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
