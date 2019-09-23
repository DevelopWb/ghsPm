package com.ghs.ghspm.bean;

import java.util.List;

/**
 * Author:wang_sir
 * Time:2018/9/29 11:03
 * Description:This is NoticeBean
 */
public class NoticeBean {


    /**
     * code : 1000
     * message : 成功
     * data : {"offset":0,"limit":2,"total":10,"params":{},"param":"","rows":[{"id":76,"title":"999","createUserName":"王斌","createTime":"10.9 19:03:46","readType":1,"url":null},{"id":75,"title":"你","createUserName":"王明申","createTime":"10.9 18:50:46","readType":1,"url":null}]}
     */

    private int code;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * offset : 0
         * limit : 2
         * total : 10
         * params : {}
         * param :
         * rows : [{"id":76,"title":"999","createUserName":"王斌","createTime":"10.9 19:03:46","readType":1,"url":null},{"id":75,"title":"你","createUserName":"王明申","createTime":"10.9 18:50:46","readType":1,"url":null}]
         */

        private int offset;
        private int limit;
        private int total;
        private ParamsBean params;
        private String param;
        private List<RowsBean> rows;

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public ParamsBean getParams() {
            return params;
        }

        public void setParams(ParamsBean params) {
            this.params = params;
        }

        public String getParam() {
            return param;
        }

        public void setParam(String param) {
            this.param = param;
        }

        public List<RowsBean> getRows() {
            return rows;
        }

        public void setRows(List<RowsBean> rows) {
            this.rows = rows;
        }

        public static class ParamsBean {
        }

        public static class RowsBean {
            /**
             * id : 76
             * title : 999
             * createUserName : 王斌
             * createTime : 10.9 19:03:46
             * readType : 1
             * url : null
             */

            private int id;
            private String title;
            private String createUserName;
            private String createTime;
            private int readType;
            private String url;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getCreateUserName() {
                return createUserName;
            }

            public void setCreateUserName(String createUserName) {
                this.createUserName = createUserName;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public int getReadType() {
                return readType;
            }

            public void setReadType(int readType) {
                this.readType = readType;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
