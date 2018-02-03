package newtrekwang.com.baselibrary.ext

import newtrekwang.com.baselibrary.rx.BaseSubscriber
import rx.Observable
import rx.Scheduler
import rx.Subscriber
import rx.schedulers.Schedulers

/**
 * Created by WJX on 2018/2/3.
 *
 */

/**
 * rx 扩展
 */
fun <T> Observable<T>.execute(subscriber: BaseSubscriber<T>){
  this.observeOn(rx.android.schedulers.AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(subscriber)
}