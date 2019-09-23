package com.ghs.ghspm.models.workdesk.carnum;

import com.ghs.ghspm.base.BasePresent;
import com.ghs.ghspm.base.network.networkProxy.HttpProxy;
import com.ghs.ghspm.base.network.okgo.NetCallBackInterface;
import com.ghs.ghspm.bean.CarInfoBean;
import com.ghs.ghspm.tools.Contract;
import com.ghs.ghspm.tools.GsonManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:wang_sir
 * Time:2018/10/23 15:13
 * Description:This is CarPresent
 */
public class CarPresent extends BasePresent<CarContract.ICarView> implements CarContract.ICarPresent {
    @Override
    public void getCarInfo(int propertyId, int villageId, final String carNum, final String tag) {

        HttpProxy.getInstance()
                .params("propertyId", propertyId)
                .params("villageId", villageId)
                .params("carNum", carNum)
                .postToNetwork(Contract.GET_CAR_INFO,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, CarInfoBean.class), tag);
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
    public void saveCarErrorInfo(int villageId,int propertyId, int pmUserId,  String carNum, String content, String imageUrl, final String tag) {

        HttpProxy.getInstance()
                .params("villageId", villageId)
                .params("propertyId", propertyId)
                .params("pmUserId", pmUserId)
                .params("carNum", carNum)
                .params("content", content)
                .params("imageUrl", imageUrl)
                .postToNetwork(Contract.SAVE_CAR_ERRO_INFO,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView("提交成功",tag);
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
     * 获取车牌信息的第一组数据
     *
     * @return
     */
    public List<String> getCarInfoFirstData() {
        List<String> array = new ArrayList<>();
        array.add("京");
        array.add("津");
        array.add("冀");
        array.add("晋");
        array.add("蒙");
        array.add("辽");
        array.add("吉");
        array.add("黑");
        array.add("沪");
        array.add("苏");
        array.add("浙");
        array.add("皖");
        array.add("闽");
        array.add("赣");
        array.add("鲁");
        array.add("豫");
        array.add("鄂");
        array.add("湘");
        array.add("粤");
        array.add("桂");
        array.add("琼");
        array.add("渝");
        array.add("川");
        array.add("贵");
        array.add("云");
        array.add("藏");
        array.add("陕");
        array.add("甘");
        array.add("青");
        array.add("宁");
        array.add("新");
        array.add("台");
        array.add("");
        array.add("");
        return array;
    }

    /**
     * 获取车牌信息的第二组数据
     *
     * @return
     */
    public List<String> getCarInfoSecendData() {
        List<String> array = new ArrayList<>();
        array.add("A");
        array.add("B");
        array.add("C");
        array.add("D");
        array.add("E");
        array.add("F");
        array.add("G");
        array.add("H");
        array.add("I");
        array.add("J");
        array.add("K");
        array.add("L");
        array.add("M");
        array.add("N");
        array.add("O");
        array.add("P");
        array.add("Q");
        array.add("R");
        array.add("S");
        array.add("T");
        array.add("U");
        array.add("V");
        array.add("W");
        array.add("X");
        array.add("Y");
        array.add("Z");
        array.add("");
        array.add("");
        return array;
    }

    /**
     * 获取车牌信息的第二组数据
     * position ==7  添加警 挂
     *
     * @return
     */
    public ArrayList<String> getCarInfoOtherData(int position) {
        ArrayList<String> array = new ArrayList<>();
        array.add("A");
        array.add("B");
        array.add("C");
        array.add("D");
        array.add("E");
        array.add("F");
        array.add("G");
        array.add("H");
        array.add("I");
        array.add("J");
        array.add("K");
        array.add("L");
        array.add("M");
        array.add("N");
        array.add("O");
        array.add("P");
        array.add("Q");
        array.add("R");
        array.add("S");
        array.add("T");
        array.add("U");
        array.add("V");
        array.add("W");
        array.add("X");
        array.add("Y");
        array.add("Z");
        array.add("1");
        array.add("2");
        array.add("3");
        array.add("4");
        array.add("5");
        array.add("6");
        array.add("7");
        array.add("8");
        array.add("9");
        array.add("0");
        if (7 == position) {
            array.add("警");
            array.add("挂");
        } else {
            array.add("");
            array.add("");
        }

        array.add("");
        array.add("");
        return array;
    }

}
