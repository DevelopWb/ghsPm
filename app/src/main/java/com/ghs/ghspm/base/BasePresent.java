package com.ghs.ghspm.base;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;

/**
 * Author:wang_sir
 * Time:2018/5/13 14:14
 * Description:This is BasePresent
 */
public abstract class BasePresent<T> {

    protected Reference<T> mReference;

    /**
     * 和view创建关联 使用软引用，防止内存泄漏
     * @param view
     */
    public void onAttachView(T view){
        mReference = new SoftReference<>(view);
    }

    /**
     * 获取绑定的view
     * @return
     */
    public T getView (){
        return mReference.get();

    }

    /**
     * 判断view和presenter是否绑定
     * @return
     */
    public boolean isOnAttachedView(){
        return mReference!=null&&mReference.get()!=null;
    }

    /**
     * 取消关联
     */
    public void detachView(){
        if (mReference != null) {
            mReference.clear();
        }
    }
}
