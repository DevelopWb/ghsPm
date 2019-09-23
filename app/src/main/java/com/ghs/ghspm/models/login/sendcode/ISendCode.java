package com.ghs.ghspm.models.login.sendcode;

/**
 * Author:wang_sir
 * Time:2018/7/16 18:20
 * Description:This is ISendCode
 */
public interface ISendCode {
    /**
     * 同步获取验证码按钮的状态
     */
    void initGetTestCodeButtonStatus();

    void checkCodeReceived();

    interface IUpdateView{
        //开始计时
        void  startTiming(long value);
        //结束计时
        void endTiming(long value);
    }
}
