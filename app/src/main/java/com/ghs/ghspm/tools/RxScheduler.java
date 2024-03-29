package com.ghs.ghspm.tools;


import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Author:wang_sir
 * Time:2019/2/12 17:49
 * Description:This is RxScheduler
 */
public class RxScheduler {
    public static <T> void doOnIoThread(IOTask<T> ioTask) {
        Observable.just(ioTask).observeOn(Schedulers.io())
                .subscribe(new Consumer<IOTask<T>>() {
                    @Override
                    public void accept(IOTask<T> ioTask) throws Exception {
                        ioTask.doOnIOThread();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }

    /**
     * 引用
     * //                RxScheduler.doTask(new RxTask<String>("") {
     //                    @Override
     //                    public void doOnIoThread() {
     //
     //                    }
     //
     //                    @Override
     //                    public void doOnUIThread() {
     //
     //                    }
     //                });
     * @param task
     * @param <T>
     */
    public static <T> void doTask(final RxTask<T> task) {

        Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> e) throws Exception {
                task.doOnIoThread();
                e.onNext(task.getT());
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<T>() {
                    @Override
                    public void accept(T t) throws Exception {
                        task.doOnUIThread(t);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }

    public interface IOTask<T> {
        void doOnIOThread();
    }
}
