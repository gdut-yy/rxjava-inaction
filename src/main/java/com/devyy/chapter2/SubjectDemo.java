package com.devyy.chapter2;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;

/**
 * AsyncSubject：不论订阅发生在什么时候，只发射最后一个数据
 * BehaviorSubject：发送订阅之前的一个数据和订阅之后的全部数据
 * ReplaySubject：不论订阅发生在什么时候，都发送全部数据
 * PublishSubject：发送订阅之后全部数据
 */
public class SubjectDemo {
    public static void main(String... args) {
//        funAsyncSubject1();
//        funAsyncSubject2();

//        funBehaviorSubject1();
//        funBehaviorSubject2();

//        funReplaySubject1();
//        funReplaySubject2();

//        funPublishSubject1();
        funPublishSubject2();
    }

    /**
     * AsyncSubject
     * <p>
     * Observer 会接收 AsyncSubject 的 onComplete() 之前的最后一个数据
     */
    private static void funAsyncSubject1() {
        AsyncSubject<String> subject = AsyncSubject.create();

        subject.onNext("asyncSubject1");
        subject.onNext("asyncSubject2");
        subject.onComplete();
        subject.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("asyncSubject:" + s);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                System.out.println("asyncSubject onError");
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                System.out.println("asyncSubject:complete");
            }
        });

        subject.onNext("asyncSubject3");
        subject.onNext("asyncSubject4");
    }

    /**
     * AsyncSubject
     * <p>
     * Observer 会接收 AsyncSubject 的 onComplete() 之前的最后一个数据
     */
    private static void funAsyncSubject2() {
        AsyncSubject<String> subject = AsyncSubject.create();

        subject.onNext("asyncSubject1");
        subject.onNext("asyncSubject2");
//        subject.onComplete();
        subject.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("asyncSubject:" + s);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                System.out.println("asyncSubject onError");
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                System.out.println("asyncSubject:complete");
            }
        });

        subject.onNext("asyncSubject3");
        subject.onNext("asyncSubject4");
        subject.onComplete();
    }

    /**
     * BehaviorSubject
     * <p>
     * Observer 会接收 BehaviorSubject 被订阅之前的最后一个数据，再接收订阅之后发射过来的数据
     */
    private static void funBehaviorSubject1() {
        BehaviorSubject<String> subject = BehaviorSubject.createDefault("behaviorSubject1");

        subject.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("behaviorSubject:" + s);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                System.out.println("behaviorSubject onError");
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                System.out.println("behaviorSubject:complete");
            }
        });

        subject.onNext("behaviorSubject2");
        subject.onNext("behaviorSubject3");
    }

    /**
     * BehaviorSubject
     * <p>
     * Observer 会接收 BehaviorSubject 被订阅之前的最后一个数据，再接收订阅之后发射过来的数据
     */
    private static void funBehaviorSubject2() {
        BehaviorSubject<String> subject = BehaviorSubject.createDefault("behaviorSubject1");
        subject.onNext("behaviorSubject2");

        subject.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("behaviorSubject:" + s);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                System.out.println("behaviorSubject onError");
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                System.out.println("behaviorSubject:complete");
            }
        });

        subject.onNext("behaviorSubject3");
        subject.onNext("behaviorSubject4");
    }

    /**
     * ReplaySubject
     * <p>
     * ReplaySubject 会发射所有来自原始 Observable 的数据给观察者，无论它们是何时订阅的
     */
    private static void funReplaySubject1() {
        ReplaySubject<String> subject = ReplaySubject.create();
        subject.onNext("replaySubject1");
        subject.onNext("replaySubject2");

        subject.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("replaySubject:" + s);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                System.out.println("replaySubject onError");
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                System.out.println("replaySubject:complete");
            }
        });

        subject.onNext("replaySubject3");
        subject.onNext("replaySubject4");
    }

    /**
     * ReplaySubject
     * <p>
     * ReplaySubject 会发射所有来自原始 Observable 的数据给观察者，无论它们是何时订阅的
     */
    private static void funReplaySubject2() {
        ReplaySubject<String> subject = ReplaySubject.createWithSize(1);
        subject.onNext("replaySubject1");
        subject.onNext("replaySubject2");

        subject.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("replaySubject:" + s);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                System.out.println("replaySubject onError");
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                System.out.println("replaySubject:complete");
            }
        });

        subject.onNext("replaySubject3");
        subject.onNext("replaySubject4");
    }

    /**
     * PublishSubject
     * <p>
     * Observer 只接收 PublishSubject 被订阅之后发送的数据
     */
    private static void funPublishSubject1() {
        PublishSubject<String> subject = PublishSubject.create();
        subject.onNext("publishSubject1");
        subject.onNext("publishSubject2");
        subject.onComplete();

        subject.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("publishSubject:" + s);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                System.out.println("publishSubject onError");
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                System.out.println("publishSubject:complete");
            }
        });

        subject.onNext("publishSubject3");
        subject.onNext("publishSubject4");
    }

    /**
     * PublishSubject
     * <p>
     * Observer 只接收 PublishSubject 被订阅之后发送的数据
     */
    private static void funPublishSubject2() {
        PublishSubject<String> subject = PublishSubject.create();
        subject.onNext("publishSubject1");
        subject.onNext("publishSubject2");

        subject.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("publishSubject:" + s);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                System.out.println("publishSubject onError");
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                System.out.println("publishSubject:complete");
            }
        });

        subject.onNext("publishSubject3");
        subject.onNext("publishSubject4");
        subject.onComplete();
    }
}
