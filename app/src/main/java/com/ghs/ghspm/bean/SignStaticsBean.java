package com.ghs.ghspm.bean;

import java.util.List;

/**
 * Author:wang_sir
 * Time:2018/10/21 18:05
 * Description:This is SignStaticsBean
 */
public class SignStaticsBean {
    /**
     * code : 1000
     * message : 成功
     * data : [{"day":"2018-10-01","result":1},{"day":"2018-10-02","result":1},{"day":"2018-10-03","result":1},{"day":"2018-10-04","result":1},{"day":"2018-10-05","result":1},{"day":"2018-10-06","result":1},{"day":"2018-10-07","result":1},{"day":"2018-10-08","result":1},{"day":"2018-10-09","result":1},{"day":"2018-10-10","result":1},{"day":"2018-10-11","result":1},{"day":"2018-10-12","result":1},{"day":"2018-10-13","result":1},{"day":"2018-10-14","result":1},{"day":"2018-10-15","result":1},{"day":"2018-10-16","result":1},{"day":"2018-10-17","result":1},{"day":"2018-10-18","result":1},{"day":"2018-10-19","result":1},{"day":"2018-10-20","result":1},{"day":"2018-10-21","result":3},{"day":"2018-10-22","result":1},{"day":"2018-10-23","result":1},{"day":"2018-10-24","result":1},{"day":"2018-10-25","result":1},{"day":"2018-10-26","result":1},{"day":"2018-10-27","result":1},{"day":"2018-10-28","result":1},{"day":"2018-10-29","result":1},{"day":"2018-10-30","result":1},{"day":"2018-10-31","result":1}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * day : 2018-10-01
         * result : 1
         */

        private String day;
        private int result;

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public int getResult() {
            return result;
        }

        public void setResult(int result) {
            this.result = result;
        }
    }
}
