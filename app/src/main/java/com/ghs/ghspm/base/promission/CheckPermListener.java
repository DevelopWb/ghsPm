package com.ghs.ghspm.base.promission;

/**
 * Author:wang_sir
 * Time:2019/5/14 13:53
 * Description:This is CheckPermListener
 */
public interface CheckPermListener {
    //权限通过后的回调方法
    void agreeAllPermission();
    //选择完所有的权限，包括同意的和拒绝的
    void selectedAllPermission();
}
