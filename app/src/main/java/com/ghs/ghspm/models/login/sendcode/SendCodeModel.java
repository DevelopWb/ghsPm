package com.ghs.ghspm.models.login.sendcode;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Author:wang_sir
 * Time:2018/7/16 18:18
 * Description:This is SendCodeModel
 */
public class SendCodeModel implements ISendCode{


    private ISendCode.IUpdateView iUpdateView;
    private Disposable disposable;

    public SendCodeModel(IUpdateView iUpdateView) {
        this.iUpdateView = iUpdateView;
    }

    @Override
    public void initGetTestCodeButtonStatus() {
        disposable = Observable.interval(0,1000, TimeUnit.MILLISECONDS)
                .take(60)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        iUpdateView.startTiming(59-aLong);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                    }
                });
            }

    @Override
    public void checkCodeReceived() {
        if (disposable != null) {
            disposable.dispose();
        }
    }


}
