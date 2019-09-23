package com.ghs.ghspm.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Author:wang_sir
 * Time:2019/1/9 14:46
 * Description:This is ToolFormBean  工具类表单
 */
public class ToolFormBean {
    /**
     * code : 1000
     * message : 成功
     * data : [{"id":31,"formName":"1111","introduction":null,"usage":"1111","icon":"2019010815564386241916.png","formType":2,"subRecordType":1,"fieldJson":"[{\"min\":\"1\",\"cssClass\":\"text\",\"max\":\"500\",\"fieldesc\":\"测试\",\"title\":\"测试\",\"required\":\"\"},{\"min\":\"\",\"cssClass\":\"check_user\",\"max\":\"\",\"fieldesc\":\"请选择对应的签批人\",\"title\":\"签批人\",\"required\":\"required\"},{\"min\":\"\",\"cssClass\":\"copy_user\",\"max\":\"\",\"fieldesc\":\"请选择对应的抄送人\",\"title\":\"抄送人\",\"required\":\"required\"}]","html":"\r\n\t\t\t\t\t\t<form id=\"target\" class=\"form-horizontal\" style=\"background-color: rgb(255, 255, 255);\">\r\n\t\t\t\t\t\t\t<!--设计器内容-->\r\n\t\t\t\t\t\t\t<fieldset id=\"fieldset\">\r\n\t\t\t\t\t\t\t\t<legend class=\"leipiplugins-orgvalue\">表单设计<\/legend>\r\n\t\t\t\t\t\t\t<div class=\"form-group component\" rel=\"popover\" title=\"\" data-html=\"true\" trigger=\"manual\" data-content=\"\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t  <form class='form'>\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<div class='controls'>\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  <label class='control-label'>标题(最多20个字)<\/label> <input type='text' id='orgname' placeholder='单行文本输入框'>\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  <label class='control-label'>提示文字(最多20个字)<\/label> <input type='text' id='orgvalue' placeholder='请输入'>\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  <label class='control-label'>是否必填<\/label>\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \t<input type='radio'   name='orgrequired' value='required' checked>必填\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \t<input type='radio'   name='orgrequired' value=''>非必填\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  <hr/>\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  <button class='btn btn-info' type='button'>确定<\/button><button class='btn btn-danger' type='button'>取消<\/button>\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<\/div>\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t  <\/form>\" data-original-title=\"单行文本输入框\">\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t<!-- Text -->\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t<label class=\"col-sm-4 control-label leipiplugins-orgname\">测试<\/label>\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t<div class=\"col-sm-8 controls\">\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<!--class=\"leipiplugins\"  leipiplugins=\"text\"  必须有-->\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<input style=\"text-align: center;\" name=\"leipiNewField\" type=\"text\" cssclass=\"text\" placeholder=\"测试\" max=\"500\" min=\"1\" orgrequired=\"\" class=\"form-control leipiplugins\" leipiplugins=\"text\" title=\"测试\" value=\"测试\">\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t<\/div>\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t<\/div>\r\n\r\n    \r\n\t\t\t\t\t\t\t\t\t\t\t<div class=\"form-group component\" rel=\"popover\" title=\"\" data-html=\"true\" trigger=\"manual\" data-content=\"\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t  <form class='form'>\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<div class='controls'>\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  <label class='control-label'>标题(最多20个字)<\/label> <input type='text' id='orgname' placeholder='人员输入框'>\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  <label class='control-label'>提示文字(最多20个字)<\/label> <input type='text' id='orgvalue' placeholder='请输入'>\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  <label class='control-label'>是否必填<\/label>\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \t<input type='radio'   name='orgrequired' value='required' checked>必填\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \t<input type='radio'   name='orgrequired' value=''>非必填\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  <hr/>\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  <button class='btn btn-info' type='button'>确定<\/button><button class='btn btn-danger' type='button'>取消<\/button>\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<\/div>\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t  <\/form>\" data-original-title=\"人员输入框\" style=\"border-top: 1px solid white; border-bottom: none;\">\r\n\t\t\t\t\t\t\t\t\t\t\t\t<!-- Text -->\r\n\t\t\t\t\t\t\t\t\t\t\t\t<label class=\"col-sm-4 control-label leipiplugins-orgname\">签批人<\/label>\r\n\t\t\t\t\t\t\t\t\t\t\t\t<div class=\"col-sm-8 controls\">\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t<!--class=\"leipiplugins\"  leipiplugins=\"text\"  必须有-->\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t<input style=\"text-align: center;\" name=\"leipiNewField\" type=\"text\" cssclass=\"check_user\" placeholder=\"请选择对应的签批人\" orgrequired=\"required\" class=\"form-control leipiplugins\" leipiplugins=\"text\" title=\"签批人\" value=\"请选择对应的签批人\">\r\n\t\t\t\t\t\t\t\t\t\t\t\t<\/div>\r\n\t\t\t\t\t\t\t\t\t\t\t<\/div>\r\n\r\n\t\t\t\t\t\t\t\t\t\t\t<div class=\"form-group component\" rel=\"popover\" title=\"\" data-html=\"true\" trigger=\"manual\" data-content=\"\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t  <form class='form'>\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<div class='controls'>\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  <label class='control-label'>标题(最多20个字)<\/label> <input type='text' id='orgname' placeholder='人员输入框'>\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  <label class='control-label'>提示文字(最多20个字)<\/label> <input type='text' id='orgvalue' placeholder='请输入'>\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  <label class='control-label'>是否必填<\/label>\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \t<input type='radio'   name='orgrequired' value='required' checked>必填\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \t<input type='radio'   name='orgrequired' value=''>非必填\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  <hr/>\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  <button class='btn btn-info' type='button'>确定<\/button><button class='btn btn-danger' type='button'>取消<\/button>\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<\/div>\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t  <\/form>\" data-original-title=\"人员输入框\">\r\n\t\t\t\t\t\t\t\t\t\t\t\t<!-- Text -->\r\n\t\t\t\t\t\t\t\t\t\t\t\t<label class=\"col-sm-4 control-label leipiplugins-orgname\">抄送人<\/label>\r\n\t\t\t\t\t\t\t\t\t\t\t\t<div class=\"col-sm-8 controls\">\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t<!--class=\"leipiplugins\"  leipiplugins=\"text\"  必须有-->\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t<input style=\"text-align: center;\" name=\"leipiNewField\" type=\"text\" cssclass=\"copy_user\" placeholder=\"请选择对应的抄送人\" orgrequired=\"required\" class=\"form-control leipiplugins\" leipiplugins=\"text\" title=\"抄送人\" value=\"请选择对应的抄送人\">\r\n\t\t\t\t\t\t\t\t\t\t\t\t<\/div>\r\n\t\t\t\t\t\t\t\t\t\t\t<\/div>\r\n\t\t\t\t\t\t\t\t\t\t<\/fieldset>\r\n\t\t\t\t\t\t<\/form>\r\n\t\t\t\t\t","auto":null,"autoType":null,"state":2,"createUserId":183,"createTime":"2019-01-08 15:56:58","updateUserId":183,"updateTime":"2019-01-08 15:59:11"}]
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
         * id : 31
         * formName : 1111
         * introduction : null
         * usage : 1111
         * icon : 2019010815564386241916.png
         * formType : 2
         * subRecordType : 1
         * fieldJson : [{"min":"1","cssClass":"text","max":"500","fieldesc":"测试","title":"测试","required":""},{"min":"","cssClass":"check_user","max":"","fieldesc":"请选择对应的签批人","title":"签批人","required":"required"},{"min":"","cssClass":"copy_user","max":"","fieldesc":"请选择对应的抄送人","title":"抄送人","required":"required"}]
         * html :
         <form id="target" class="form-horizontal" style="background-color: rgb(255, 255, 255);">
         <!--设计器内容-->
         <fieldset id="fieldset">
         <legend class="leipiplugins-orgvalue">表单设计</legend>
         <div class="form-group component" rel="popover" title="" data-html="true" trigger="manual" data-content="
         <form class='form'>
         <div class='controls'>
         <label class='control-label'>标题(最多20个字)</label> <input type='text' id='orgname' placeholder='单行文本输入框'>
         <label class='control-label'>提示文字(最多20个字)</label> <input type='text' id='orgvalue' placeholder='请输入'>
         <label class='control-label'>是否必填</label>
         <input type='radio'   name='orgrequired' value='required' checked>必填
         <input type='radio'   name='orgrequired' value=''>非必填
         <hr/>
         <button class='btn btn-info' type='button'>确定</button><button class='btn btn-danger' type='button'>取消</button>
         </div>
         </form>" data-original-title="单行文本输入框">
         <!-- Text -->
         <label class="col-sm-4 control-label leipiplugins-orgname">测试</label>
         <div class="col-sm-8 controls">
         <!--class="leipiplugins"  leipiplugins="text"  必须有-->
         <input style="text-align: center;" name="leipiNewField" type="text" cssclass="text" placeholder="测试" max="500" min="1" orgrequired="" class="form-control leipiplugins" leipiplugins="text" title="测试" value="测试">
         </div>
         </div>


         <div class="form-group component" rel="popover" title="" data-html="true" trigger="manual" data-content="
         <form class='form'>
         <div class='controls'>
         <label class='control-label'>标题(最多20个字)</label> <input type='text' id='orgname' placeholder='人员输入框'>
         <label class='control-label'>提示文字(最多20个字)</label> <input type='text' id='orgvalue' placeholder='请输入'>
         <label class='control-label'>是否必填</label>
         <input type='radio'   name='orgrequired' value='required' checked>必填
         <input type='radio'   name='orgrequired' value=''>非必填
         <hr/>
         <button class='btn btn-info' type='button'>确定</button><button class='btn btn-danger' type='button'>取消</button>
         </div>
         </form>" data-original-title="人员输入框" style="border-top: 1px solid white; border-bottom: none;">
         <!-- Text -->
         <label class="col-sm-4 control-label leipiplugins-orgname">签批人</label>
         <div class="col-sm-8 controls">
         <!--class="leipiplugins"  leipiplugins="text"  必须有-->
         <input style="text-align: center;" name="leipiNewField" type="text" cssclass="check_user" placeholder="请选择对应的签批人" orgrequired="required" class="form-control leipiplugins" leipiplugins="text" title="签批人" value="请选择对应的签批人">
         </div>
         </div>

         <div class="form-group component" rel="popover" title="" data-html="true" trigger="manual" data-content="
         <form class='form'>
         <div class='controls'>
         <label class='control-label'>标题(最多20个字)</label> <input type='text' id='orgname' placeholder='人员输入框'>
         <label class='control-label'>提示文字(最多20个字)</label> <input type='text' id='orgvalue' placeholder='请输入'>
         <label class='control-label'>是否必填</label>
         <input type='radio'   name='orgrequired' value='required' checked>必填
         <input type='radio'   name='orgrequired' value=''>非必填
         <hr/>
         <button class='btn btn-info' type='button'>确定</button><button class='btn btn-danger' type='button'>取消</button>
         </div>
         </form>" data-original-title="人员输入框">
         <!-- Text -->
         <label class="col-sm-4 control-label leipiplugins-orgname">抄送人</label>
         <div class="col-sm-8 controls">
         <!--class="leipiplugins"  leipiplugins="text"  必须有-->
         <input style="text-align: center;" name="leipiNewField" type="text" cssclass="copy_user" placeholder="请选择对应的抄送人" orgrequired="required" class="form-control leipiplugins" leipiplugins="text" title="抄送人" value="请选择对应的抄送人">
         </div>
         </div>
         </fieldset>
         </form>

         * auto : null
         * autoType : null
         * state : 2
         * createUserId : 183
         * createTime : 2019-01-08 15:56:58
         * updateUserId : 183
         * updateTime : 2019-01-08 15:59:11
         */

        private int id;
        private String formName;
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
        }

        public DataBean() {
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
