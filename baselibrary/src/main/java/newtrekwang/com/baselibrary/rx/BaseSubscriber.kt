package newtrekwang.com.baselibrary.rx

import newtrekwang.com.baselibrary.presenter.view.BaseView
import rx.Subscriber

/**
 * Created by WJX on 2018/2/3.
 * 封装的基本观察者
 */
open class  BaseSubscriber<T> (val baseView: BaseView):Subscriber<T>(){
    override fun onCompleted() {
        baseView.hideLoading()
    }

    override fun onError(e: Throwable?) {
        baseView.hideLoading()

    }

    override fun onNext(t: T) {
    }



}