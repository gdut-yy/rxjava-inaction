package com.devyy.chapter2;

import io.reactivex.Notification;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * do 操作符（生命周期）
 * <p>
 * OUTPUT：
 * doOnSubscribe:
 * doFinally: false
 * doOnNext: Hello
 * doOnEach: onNext
 * 收到消息: Hello
 * doAfterNext: Hello
 * doOnComplete:
 * doOnEach: onComplete
 * doFinally:
 * doAfterTerminate:
 */
public class DoXxx {
    public static void main(String... args) {
        Observable.just("Hello")
                // 它产生的 Observable 每发射一项数据就会调用它一次，
                // 它的 Consumer 接受发射的数据项。一般用于在 subscribe 之前对数据进行处理
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println("doOnNext: " + s);
                    }
                })
                // 在 onNext之后执行，而 doOnNext 是在 onNext 之前执行
                .doAfterNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println("doAfterNext: " + s);
                    }
                })
                // 当它产生的 Observable 在正常终止调用 onComplete 时会被调用
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("doOnComplete: ");
                    }
                })
                // 一旦观察者订阅了 Observable ，它就会被调用
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        System.out.println("doOnSubscribe: ");
                    }
                })
                // 注册一个 Action ，当 Observable 调用 onComplete 和 onError 时触发
                .doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("doAfterTerminate: ");
                    }
                })
                // 在当它产生的 Observable 终止之后会被调用，无论是正常终止还是异常终止。
                // doFinally 优先于 doAfterTerminate 的调用
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("doFinally: ");
                    }
                })
                // 它产生的 Observable 每发射一项数据就会调用它一次，不仅包括 onNext，还包括 onError 和 onCompleted
                .doOnEach(new Consumer<Notification<String>>() {
                    @Override
                    public void accept(Notification<String> stringNotification) throws Exception {
                        System.out.println("doOnEach: "
                                + (stringNotification.isOnNext() ? "onNext" :
                                stringNotification.isOnComplete() ? "onComplete" : "onError"));
                    }
                })
                // 可以在观察者订阅之后，设置是否取消订阅
                .doOnLifecycle(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        System.out.println("doFinally: " + disposable.isDisposed());
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("doOnLifecycle run: ");
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println("收到消息: " + s);
                    }
                });
    }
}
