package com.ghs.ghspm.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:wang_sir
 * Time:2019/1/8 17:04
 * Description:This is AutoFormBean  自动报表
 */
public class AutoFormBean {

    /**
     * code : 1000
     * message : 成功
     * data : [[{"cssClass":"text","title":"姓名","prevalue":"龙洋"},{"cssClass":"textarea","title":"状况描述","prevalue":"楼道已经打扫完毕"},{"cssClass":"select","title":"性别","prevalue":"男"},{"cssClass":"multiple_select","title":"打扫位置","prevalue":"走廊,楼道,花坛"},{"cssClass":"number","title":"数字","prevalue":""},{"cssClass":"money","title":"金额","prevalue":""},{"cssClass":"user","title":"人员","prevalue":""},{"cssClass":"image","title":"图片","prevalue":"2019010811373031410043.jpeg"},{"cssClass":"date","title":"日期","prevalue":"2019-01-08"},{"cssClass":"tower","title":"楼栋","prevalue":"一期1号楼"},{"cssClass":"cell","title":"单元","prevalue":"1单元"},{"cssClass":"room","title":"室号","prevalue":"104"},{"cssClass":"text","title":"执行人","prevalue":"陶龙洋"},{"cssClass":"date","title":"创建时间","prevalue":"2019-01-08 10:58:31"}],[{"cssClass":"text","title":"姓名","prevalue":"王彬"},{"cssClass":"textarea","title":"状况描述","prevalue":"王彬测试666"},{"cssClass":"select","title":"性别","prevalue":"男"},{"cssClass":"multiple_select","title":"打扫位置","prevalue":"楼道"},{"cssClass":"number","title":"数字","prevalue":"111"},{"cssClass":"money","title":"金额","prevalue":"123"},{"cssClass":"user","title":"人员","prevalue":"王斌(2)"},{"cssClass":"image","title":"图片","prevalue":"2019010811000222822143.jpeg,2019010811000223745281.jpeg,2019010811000224612603.jpeg"},{"cssClass":"date","title":"日期","prevalue":"2019-01-08"},{"cssClass":"tower","title":"楼栋","prevalue":"一期1号楼"},{"cssClass":"cell","title":"单元","prevalue":"1单元"},{"cssClass":"room","title":"室号","prevalue":"104室"},{"cssClass":"text","title":"执行人","prevalue":"王斌(2)"},{"cssClass":"date","title":"创建时间","prevalue":"2019-01-08 10:58:31"}]]
     */

    private int code;
    private String message;
    private List<List<DataBean>> data;

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

    public List<List<DataBean>> getData() {
        return data;
    }

    public void setData(List<List<DataBean>> data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {
        /**
         * cssClass : text
         * title : 姓名
         * prevalue : 龙洋
         */

        private String cssClass;
        private String title;
        private String prevalue;
        private String  emptyDataTag;//空数据布局的标识  值为"空"时 空布局
        private boolean selected;
        private String preId;
        private  String required;
        private List<String> children;
        private int recordId;
        private int  presentPosition;//每一行对应的位置  左侧的item的位置和右侧recyclerview对应的位置一致

        public int getPresentPosition() {
            return presentPosition;
        }

        public void setPresentPosition(int presentPosition) {
            this.presentPosition = presentPosition;
        }

        public int getRecordId() {
            return recordId;
        }

        public void setRecordId(int recordId) {
            this.recordId = recordId;
        }

        public List<String> getChildren() {
            if (children == null) {
                return new ArrayList<>();
            }
            return children;
        }

        public void setChildren(List<String> children) {
            this.children = children;
        }

        public String getRequired() {
            return required == null ? "" : required;
        }

        public void setRequired(String required) {
            this.required = required;
        }

        public String getPreId() {
            return preId == null ? "" : preId;
        }

        public void setPreId(String preId) {
            this.preId = preId;
        }

        public String getEmptyDataTag() {
            return emptyDataTag == null ? "" : emptyDataTag;
        }

        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }

        public void setEmptyDataTag(String emptyDataTag) {
            this.emptyDataTag = emptyDataTag;
        }

        public String getCssClass() {
            return cssClass == null ? "" : cssClass;
        }

        public void setCssClass(String cssClass) {
            this.cssClass = cssClass;
        }

        public String getTitle() {
            return title == null ? "" : title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPrevalue() {
            return prevalue == null ? "" : prevalue;
        }

        public void setPrevalue(String prevalue) {
            this.prevalue = prevalue;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.cssClass);
            dest.writeString(this.title);
            dest.writeString(this.prevalue);
            dest.writeString(this.emptyDataTag);
            dest.writeByte(this.selected ? (byte) 1 : (byte) 0);
            dest.writeString(this.preId);
            dest.writeString(this.required);
            dest.writeStringList(this.children);
            dest.writeInt(this.recordId);
            dest.writeInt(this.presentPosition);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.cssClass = in.readString();
            this.title = in.readString();
            this.prevalue = in.readString();
            this.emptyDataTag = in.readString();
            this.selected = in.readByte() != 0;
            this.preId = in.readString();
            this.required = in.readString();
            this.children = in.createStringArrayList();
            this.recordId = in.readInt();
            this.presentPosition = in.readInt();
        }

        public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }
}
