package com.ghs.ghspm.tools;

import com.ghs.ghspm.bean.LoginBean;
import com.orhanobut.hawk.Hawk;

/**
 * Author:wang_sir
 * Time:2018/8/10 15:33
 * Description:This is UserInfoUtil
 */
public class UserInfoUtil {


    public UserInfoUtil() {
    }

    public static UserInfoUtil getInstance() {
        return UserInfoUtilHolder.instance;
    }

    private static class UserInfoUtilHolder {
        private static UserInfoUtil instance = new UserInfoUtil();
    }

    /**
     * 获取登录返回的实体类
     *
     * @return
     */
    public LoginBean.DataBean getLoginDataBean() {
        LoginBean.DataBean dataBean = null;
        LoginBean loginBean = Hawk.get(HawkProperty.LOGIN_BEAN);
        if (loginBean != null) {
            dataBean = loginBean.getData();
        }
        return dataBean;
    }

    /**
     * 获取UserId
     *
     * @return
     */
    public int getUserId() {
        int userId = -1;
        if (Hawk.contains(HawkProperty.USERID)) {
            return Hawk.get(HawkProperty.USERID);
        }
        return userId;
    }


    /**
     * 获取VillageId
     *
     * @return
     */
    public int getVillageId() {
        int villageId = -1;
        LoginBean.DataBean bean = getLoginDataBean();
        if (bean != null) {
            villageId = bean.getVillageId();
        }
        return villageId;
    }

    /**
     * 获取VillageId
     *
     * @return
     */
    public String getVillageName() {
        String villageName = "";
        LoginBean.DataBean bean = getLoginDataBean();
        if (bean != null) {
            villageName = bean.getVillageName();
        }
        return villageName;
    }
    /**
     * 获取头像默认颜色
     *
     * @return
     */
    public String getHeadDefaultBgColor() {
        String bgColor = "";
        LoginBean.DataBean bean = getLoginDataBean();
        if (bean != null) {
            bgColor = bean.getHeadImageBackGroudColor();
        }
        return bgColor;
    }

    /**
     * getDepId
     *
     * @return
     */
    public int getDeptId() {
        int depId = -1;
        LoginBean.DataBean bean = getLoginDataBean();
        if (bean != null) {
            depId = bean.getDeptId();
        }
        return depId;
    }

    /**
     * getDepId
     *
     * @return
     */
    public String getDeptName() {
        String depName = "";
        LoginBean.DataBean bean = getLoginDataBean();
        if (bean != null) {
            depName = bean.getDeptName();
        }
        return depName;
    }

    /**
     * getDepId
     *
     * @return
     */
    public int getPropertyId() {
        int depId = -1;
        LoginBean.DataBean bean = getLoginDataBean();
        if (bean != null) {
            depId = bean.getPropertyId();
        }
        return depId;
    }

    /**
     * getDepId
     *
     * @return
     */
    public String getPropertyName() {
        String propertyName = "";
        LoginBean.DataBean bean = getLoginDataBean();
        if (bean != null) {
            propertyName = bean.getPropertyName();
        }
        return propertyName;
    }

    /**
     * 获取name
     *
     * @return
     */
    public String getUserName() {
        if (getLoginDataBean() != null) {
            return getLoginDataBean().getName();
        } else {
            return "";

        }

    }

    /**
     * 获取headImage
     *
     * @return
     */
    public String getUserHeadImage() {
        if (getLoginDataBean() != null) {
            return getLoginDataBean().getHeadImage();
        } else {
            return "";

        }

    }

    /**
     * 获取token
     *
     * @return
     */
    public String getUserToken() {
        return Hawk.get(HawkProperty.TOKEN);

    }
}
