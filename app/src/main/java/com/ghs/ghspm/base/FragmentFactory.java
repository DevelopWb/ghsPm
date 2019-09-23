package com.ghs.ghspm.base;

import android.util.SparseArray;

import com.ghs.ghspm.models.task.completedtask.CompletedTaskFragment;
import com.ghs.ghspm.models.task.createdbymetask.ICreatedTaskFragment;
import com.ghs.ghspm.models.task.pushedTaskShow.CompletedTaskFromPushFragment;
import com.ghs.ghspm.models.task.pushedTaskShow.ICreatedTaskFromPushFragment;
import com.ghs.ghspm.models.task.pushedTaskShow.RelatedToMeTaskFromPushFragment;
import com.ghs.ghspm.models.task.pushedTaskShow.WaitForDealTaskFromPushFragment;
import com.ghs.ghspm.models.task.relatedtometask.RelatedToMeTaskFragment;
import com.ghs.ghspm.models.task.waittodealtask.WaitForDealTaskFragment;
import com.ghs.ghspm.models.workdesk.publicfuction.notice.normalnotice.NormalNoticeFragment;
import com.ghs.ghspm.models.workdesk.publicfuction.notice.unnormalnotice.InstitutionFragment;
import com.ghs.ghspm.models.workdesk.publicfuction.notice.unnormalnotice.VillageNoticeFragment;

/**
 * Author:wang_sir
 * Time:2018/6/20 21:10
 * Description:This is FragmentFactory
 */
public class FragmentFactory {


    /**
     * home界面中任务里面的的fragments
     * @return
     */
    public static SparseArray<android.support.v4.app.Fragment> getmFragmentsOfHomePageOfTask(){
        SparseArray<android.support.v4.app.Fragment> mFragments =  new SparseArray<>();
        mFragments.put(0, WaitForDealTaskFragment.getInstance());
        mFragments.put(1, ICreatedTaskFragment.getInstance());
        mFragments.put(2, RelatedToMeTaskFragment.getInstance());
        mFragments.put(3, CompletedTaskFragment.getInstance());
        return mFragments;
    }
    /**
     * 推送消息跳转的任务界
     * @return
     */
    public static SparseArray<android.support.v4.app.Fragment> getmFragmentsOfPushedTask(){
        SparseArray<android.support.v4.app.Fragment> mFragments =  new SparseArray<>();
        mFragments.put(0, WaitForDealTaskFromPushFragment.getInstance());
        mFragments.put(1, ICreatedTaskFromPushFragment.getInstance());
        mFragments.put(2, RelatedToMeTaskFromPushFragment.getInstance());
        mFragments.put(3, CompletedTaskFromPushFragment.getInstance());
        return mFragments;
    }
    /**
     * notice界面中的fragments
     * @return
     */
    public static SparseArray<android.support.v4.app.Fragment> getmFragmentsOfNotice(){
        SparseArray<android.support.v4.app.Fragment> mFragments =  new SparseArray<>();
        mFragments.put(0, new NormalNoticeFragment());
        mFragments.put(1, new InstitutionFragment());
        mFragments.put(2, new VillageNoticeFragment());
        return mFragments;
    }






}
