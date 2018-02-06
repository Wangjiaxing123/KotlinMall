package newtrekwang.com.baselibrary.rx

import rx.Subscriber

/**
 * Created by WJX on 2018/2/3.
 * 封装的基本观察者
 */
open class  BaseSubscriber<T> :Subscriber<T>(){
    override fun onCompleted() {

    }

    override fun onError(e: Throwable?) {

    }

    override fun onNext(t: T) {

    }


}