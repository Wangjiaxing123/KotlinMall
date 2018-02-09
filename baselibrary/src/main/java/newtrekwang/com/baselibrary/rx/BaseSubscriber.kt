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

    override fun onError(e: Throwable) {
        baseView.hideLoading()
        if (e is BaseException){//自定义的异常
            baseView.onError(e.msg)
        }else{
            baseView.onError(e.message.toString())
        }
    }

    override fun onNext(t: T) {
    }



}