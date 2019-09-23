package com.ghs.ghspm.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Author:wang_sir
 * Time:2019/4/23 16:25
 * Description:This is PatrolFormBeanV  巡查巡检表单列表返回的实体类
 */
public class PatrolFormListBean {


    /**
     * code : 1000
     * message : 成功
     * data : [{"id":74,"formName":"测试表单","introduction":"测试专用\r\n","usage":"表单必须认真填写，否则不予审批","icon":"formtask_day_icon.png","formType":1,"subRecordType":1,"fieldJson":"[{\"min\":\"1\",\"cssClass\":\"text\",\"max\":\"500\",\"fieldesc\":\"请输入\",\"title\":\"单文本输入框\",\"required\":\"required\"},{\"min\":\"1\",\"cssClass\":\"textarea\",\"max\":\"3000\",\"fieldesc\":\"请输入\",\"title\":\"多文本输入框\",\"required\":\"\"},{\"min\":\"0\",\"cssClass\":\"number\",\"max\":\"1000000000\",\"fieldesc\":\"请输入数字\",\"title\":\"数字输入框\",\"required\":\"required\"},{\"min\":\"\",\"cssClass\":\"multiple_select\",\"max\":\"\",\"children\":[\"选项一\",\"选项二\",\"选项三\"],\"fieldesc\":\"请选择\",\"title\":\"多项选择框\",\"required\":\"required\"},{\"min\":\"0\",\"cssClass\":\"money\",\"max\":\"1000000000\",\"fieldesc\":\"请输入金额\",\"title\":\"金额输入框\",\"required\":\"required\"},{\"min\":\"\",\"cssClass\":\"user\",\"max\":\"\",\"fieldesc\":\"请选择人员\",\"title\":\"人员选择框\",\"required\":\"required\"},{\"min\":\"\",\"cssClass\":\"image\",\"max\":\"9\",\"fieldesc\":\"请选择图片\",\"title\":\"图片现在框\",\"required\":\"required\"},{\"min\":\"\",\"cssClass\":\"date\",\"max\":\"\",\"format\":\"yyyy-MM-dd\",\"fieldesc\":\"请选择日期\",\"title\":\"日期选择框\",\"required\":\"required\"},{\"min\":\"\",\"cssClass\":\"tower\",\"max\":\"\",\"fieldesc\":\"请选择楼栋\",\"title\":\"楼栋选择框\",\"required\":\"required\"},{\"min\":\"\",\"cssClass\":\"cell\",\"max\":\"\",\"fieldesc\":\"请选择单元\",\"title\":\"单元选择框\",\"required\":\"required\"},{\"min\":\"\",\"cssClass\":\"room\",\"max\":\"\",\"fieldesc\":\"请选择室号\",\"title\":\"室号单选框\",\"required\":\"required\"}]","html":null,"auto":2,"autoType":null,"state":1,"createUserId":null,"createTime":null,"updateUserId":null,"updateTime":null,"showDate":"month","headerTitle1":"记录号","headerTitle2":"口号","headerTitle3":null,"headerTitle4":null,"footerTitle1":"确认人","footerTitle2":"签批人","footerTitle3":null,"footerTitle4":null,"showCode":"KDL-324890","rowNum":5},{"id":68,"formName":"配电室巡查记录表","introduction":"1","usage":"请抄送给维修负责人","icon":"formtask_day_icon.png","formType":1,"subRecordType":2,"fieldJson":"[{\"min\":\"\",\"cssClass\":\"date\",\"max\":\"\",\"format\":\"yyyy-MM-dd\",\"fieldesc\":\"请选择日期\",\"title\":\"时间\",\"required\":\"required\"},{\"min\":\"\",\"cssClass\":\"select\",\"max\":\"\",\"children\":[\"8\",\"10\",\"12\",\"14\",\"16\",\"18\",\"20\",\"22\",\"24\",\"2\",\"4\",\"6\",\"\"],\"fieldesc\":\"请选择\",\"title\":\"巡查时间\",\"required\":\"required\"},{\"min\":\"1\",\"cssClass\":\"text\",\"max\":\"500\",\"fieldesc\":\"请输入温度\",\"title\":\"室内温度\",\"required\":\"required\"},{\"min\":\"1\",\"cssClass\":\"text\",\"max\":\"500\",\"fieldesc\":\"请输入\",\"title\":\"线电压\",\"required\":\"required\"},{\"min\":\"1\",\"cssClass\":\"text\",\"max\":\"500\",\"fieldesc\":\"请输入\",\"title\":\"相电压\",\"required\":\"required\"},{\"min\":\"1\",\"cssClass\":\"text\",\"max\":\"500\",\"fieldesc\":\"请输入\",\"title\":\"电流A\",\"required\":\"required\"},{\"min\":\"1\",\"cssClass\":\"text\",\"max\":\"500\",\"fieldesc\":\"请输入\",\"title\":\"电流B\",\"required\":\"\"},{\"min\":\"1\",\"cssClass\":\"text\",\"max\":\"500\",\"fieldesc\":\"请输入\",\"title\":\"电流C\",\"required\":\"required\"},{\"min\":\"1\",\"cssClass\":\"text\",\"max\":\"500\",\"fieldesc\":\"请输入电表数\",\"title\":\"表数\",\"required\":\"required\"},{\"min\":\"0\",\"cssClass\":\"number\",\"max\":\"1000000000\",\"fieldesc\":\"请输入\",\"title\":\"实行数\",\"required\":\"required\"},{\"min\":\"0\",\"cssClass\":\"number\",\"max\":\"1000000000\",\"fieldesc\":\"请输入\",\"title\":\"倍数\",\"required\":\"required\"},{\"min\":\"0\",\"cssClass\":\"number\",\"max\":\"1000000000\",\"fieldesc\":\"请输入\",\"title\":\"度数\",\"required\":\"required\"},{\"min\":\"1\",\"cssClass\":\"textarea\",\"max\":\"3000\",\"fieldesc\":\"请输入\",\"title\":\"运行情况\",\"required\":\"\"}]","html":null,"auto":1,"autoType":1,"state":1,"createUserId":null,"createTime":null,"updateUserId":null,"updateTime":null,"showDate":null,"headerTitle1":null,"headerTitle2":null,"headerTitle3":null,"headerTitle4":null,"footerTitle1":null,"footerTitle2":null,"footerTitle3":null,"footerTitle4":null,"showCode":null,"rowNum":null}]
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

    public static class DataBean implements Parcelable {
        /**
         * id : 74
         * formName : 测试表单
         * introduction : 测试专用
         * usage : 表单必须认真填写，否则不予审批
         * icon : formtask_day_icon.png
         * formType : 1
         * subRecordType : 1
         * fieldJson : [{"min":"1","cssClass":"text","max":"500","fieldesc":"请输入","title":"单文本输入框","required":"required"},{"min":"1","cssClass":"textarea","max":"3000","fieldesc":"请输入","title":"多文本输入框","required":""},{"min":"0","cssClass":"number","max":"1000000000","fieldesc":"请输入数字","title":"数字输入框","required":"required"},{"min":"","cssClass":"multiple_select","max":"","children":["选项一","选项二","选项三"],"fieldesc":"请选择","title":"多项选择框","required":"required"},{"min":"0","cssClass":"money","max":"1000000000","fieldesc":"请输入金额","title":"金额输入框","required":"required"},{"min":"","cssClass":"user","max":"","fieldesc":"请选择人员","title":"人员选择框","required":"required"},{"min":"","cssClass":"image","max":"9","fieldesc":"请选择图片","title":"图片现在框","required":"required"},{"min":"","cssClass":"date","max":"","format":"yyyy-MM-dd","fieldesc":"请选择日期","title":"日期选择框","required":"required"},{"min":"","cssClass":"tower","max":"","fieldesc":"请选择楼栋","title":"楼栋选择框","required":"required"},{"min":"","cssClass":"cell","max":"","fieldesc":"请选择单元","title":"单元选择框","required":"required"},{"min":"","cssClass":"room","max":"","fieldesc":"请选择室号","title":"室号单选框","required":"required"}]
         * html : null
         * auto : 2
         * autoType : null
         * state : 1
         * createUserId : null
         * createTime : null
         * updateUserId : null
         * updateTime : null
         * showDate : month
         * headerTitle1 : 记录号
         * headerTitle2 : 口号
         * headerTitle3 : null
         * headerTitle4 : null
         * footerTitle1 : 确认人
         * footerTitle2 : 签批人
         * footerTitle3 : null
         * footerTitle4 : null
         * showCode : KDL-324890
         * rowNum : 5
         */

        private int id;
        private String introduction;
        private String usage;
        private String icon;
        private int formType;
        private int subRecordType;
        private String fieldJson;
        private String html;
        private int auto;
        private int autoType;
        private int state;
        private int createUserId;
        private String createTime;
        private int updateUserId;
        private String updateTime;
        private String showDate;
        private String headerTitle1;
        private String headerTitle2;
        private String headerTitle3;
        private String headerTitle4;
        private String footerTitle1;
        private String footerTitle2;
        private String footerTitle3;
        private String footerTitle4;
        private String showCode;
        private int rowNum;
        private String formName;


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFormName() {
            return formName == null ? "" : formName;
        }

        public void setFormName(String formName) {
            this.formName = formName;
        }

        public String getIntroduction() {
            return introduction == null ? "" : introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public String getUsage() {
            return usage == null ? "" : usage;
        }

        public void setUsage(String usage) {
            this.usage = usage;
        }

        public String getIcon() {
            return icon == null ? "" : icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getFormType() {
            return formType;
        }

        public void setFormType(int formType) {
            this.formType = formType;
        }

        public int getSubRecordType() {
            return subRecordType;
        }

        public void setSubRecordType(int subRecordType) {
            this.subRecordType = subRecordType;
        }

        public String getFieldJson() {
            return fieldJson == null ? "" : fieldJson;
        }

        public void setFieldJson(String fieldJson) {
            this.fieldJson = fieldJson;
        }

        public String getHtml() {
            return html == null ? "" : html;
        }

        public void setHtml(String html) {
            this.html = html;
        }

        public int getAuto() {
            return auto;
        }

        public void setAuto(int auto) {
            this.auto = auto;
        }

        public int getAutoType() {
            return autoType;
        }

        public void setAutoType(int autoType) {
            this.autoType = autoType;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int getCreateUserId() {
            return createUserId;
        }

        public void setCreateUserId(int createUserId) {
            this.createUserId = createUserId;
        }

        public String getCreateTime() {
            return createTime == null ? "" : createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getUpdateUserId() {
            return updateUserId;
        }

        public void setUpdateUserId(int updateUserId) {
            this.updateUserId = updateUserId;
        }

        public String getUpdateTime() {
            return updateTime == null ? "" : updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getShowDate() {
            return showDate == null ? "" : showDate;
        }

        public void setShowDate(String showDate) {
            this.showDate = showDate;
        }

        public String getHeaderTitle1() {
            return headerTitle1 == null ? "" : headerTitle1;
        }

        public void setHeaderTitle1(String headerTitle1) {
            this.headerTitle1 = headerTitle1;
        }

        public String getHeaderTitle2() {
            return headerTitle2 == null ? "" : headerTitle2;
        }

        public void setHeaderTitle2(String headerTitle2) {
            this.headerTitle2 = headerTitle2;
        }

        public String getHeaderTitle3() {
            return headerTitle3 == null ? "" : headerTitle3;
        }

        public void setHeaderTitle3(String headerTitle3) {
            this.headerTitle3 = headerTitle3;
        }

        public String getHeaderTitle4() {
            return headerTitle4 == null ? "" : headerTitle4;
        }

        public void setHeaderTitle4(String headerTitle4) {
            this.headerTitle4 = headerTitle4;
        }

        public String getFooterTitle1() {
            return footerTitle1 == null ? "" : footerTitle1;
        }

        public void setFooterTitle1(String footerTitle1) {
            this.footerTitle1 = footerTitle1;
        }

        public String getFooterTitle2() {
            return footerTitle2 == null ? "" : footerTitle2;
        }

        public void setFooterTitle2(String footerTitle2) {
            this.footerTitle2 = footerTitle2;
        }

        public String getFooterTitle3() {
            return footerTitle3 == null ? "" : footerTitle3;
        }

        public void setFooterTitle3(String footerTitle3) {
            this.footerTitle3 = footerTitle3;
        }

        public String getFooterTitle4() {
            return footerTitle4 == null ? "" : footerTitle4;
        }

        public void setFooterTitle4(String footerTitle4) {
            this.footerTitle4 = footerTitle4;
        }

        public String getShowCode() {
            return showCode == null ? "" : showCode;
        }

        public void setShowCode(String showCode) {
            this.showCode = showCode;
        }

        public int getRowNum() {
            return rowNum;
        }

        public void setRowNum(int rowNum) {
            this.rowNum = rowNum;
        }

        public DataBean() {
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.formName);
            dest.writeString(this.introduction);
            dest.writeString(this.usage);
            dest.writeString(this.icon);
            dest.writeInt(this.formType);
            dest.writeInt(this.subRecordType);
            dest.writeString(this.fieldJson);
            dest.writeString(this.html);
            dest.writeInt(this.auto);
            dest.writeInt(this.autoType);
            dest.writeInt(this.state);
            dest.writeInt(this.createUserId);
            dest.writeString(this.createTime);
            dest.writeInt(this.updateUserId);
            dest.writeString(this.updateTime);
            dest.writeString(this.showDate);
            dest.writeString(this.headerTitle1);
            dest.writeString(this.headerTitle2);
            dest.writeString(this.headerTitle3);
            dest.writeString(this.headerTitle4);
            dest.writeString(this.footerTitle1);
            dest.writeString(this.footerTitle2);
            dest.writeString(this.footerTitle3);
            dest.writeString(this.footerTitle4);
            dest.writeString(this.showCode);
            dest.writeInt(this.rowNum);
        }

        protected DataBean(Parcel in) {
            this.id = in.readInt();
            this.formName = in.readString();
            this.introduction = in.readString();
            this.usage = in.readString();
            this.icon = in.readString();
            this.formType = in.readInt();
            this.subRecordType = in.readInt();
            this.fieldJson = in.readString();
            this.html = in.readString();
            this.auto = in.readInt();
            this.autoType = in.readInt();
            this.state = in.readInt();
            this.createUserId = in.readInt();
            this.createTime = in.readString();
            this.updateUserId = in.readInt();
            this.updateTime = in.readString();
            this.showDate = in.readString();
            this.headerTitle1 = in.readString();
            this.headerTitle2 = in.readString();
            this.headerTitle3 = in.readString();
            this.headerTitle4 = in.readString();
            this.footerTitle1 = in.readString();
            this.footerTitle2 = in.readString();
            this.footerTitle3 = in.readString();
            this.footerTitle4 = in.readString();
            this.showCode = in.readString();
            this.rowNum = in.readInt();
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
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
