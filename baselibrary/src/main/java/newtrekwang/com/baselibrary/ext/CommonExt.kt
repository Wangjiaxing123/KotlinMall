package newtrekwang.com.baselibrary.ext

import android.view.View
import com.trello.rxlifecycle.LifecycleProvider
import com.trello.rxlifecycle.kotlin.bindToLifecycle
import newtrekwang.com.baselibrary.data.protocal.BaseResp
import newtrekwang.com.baselibrary.rx.BaseFunc
import newtrekwang.com.baselibrary.rx.BaseFuncBoolean
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
fun <T> Observable<T>.execute(subscriber: BaseSubscriber<T>,lifeCycleProvider: LifecycleProvider<*>){
  this.observeOn(rx.android.schedulers.AndroidSchedulers.mainThread())
          .compose(lifeCycleProvider.bindToLifecycle())
            .subscribeOn(Schedulers.io())
            .subscribe(subscriber)
}

/**
 * 将Observable<BaseResp<T>>变换为Observable<T>
 */
fun <T> Observable<BaseResp<T>>.convert():Observable<T>{
 return this.flatMap(BaseFunc())
}
/**
 * 将Observable<BaseResp<T>>变换为Observable<Boolean>
 */
fun <T> Observable<BaseResp<T>>.convertBoolean():Observable<Boolean>{
  return this.flatMap(BaseFuncBoolean())
}

// 接口方式
fun View.onClick(onClickListener: View.OnClickListener){
  this.setOnClickListener(onClickListener)
}
// lambda方式
fun View.onClick(method: () -> Unit){
  this.setOnClickListener { method() }
}