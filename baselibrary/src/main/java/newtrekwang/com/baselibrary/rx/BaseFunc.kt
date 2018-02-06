package newtrekwang.com.baselibrary.rx

import newtrekwang.com.baselibrary.data.protocal.BaseResp
import rx.Observable
import rx.functions.Func1

/**
 * Created by Newterk on 2018/2/6.
 */
class BaseFunc<T> : Func1<BaseResp<T>,Observable<T>> {
    override fun call(t: BaseResp<T>): Observable<T> {
     return   when (t.status){
            200 -> Observable.just(t.result)
            else -> Observable.error(BaseException(t.status,t.message))
        }
    }
}