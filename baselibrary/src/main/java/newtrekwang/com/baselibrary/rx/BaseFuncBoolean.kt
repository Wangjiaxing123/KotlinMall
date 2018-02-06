package newtrekwang.com.baselibrary.rx

import newtrekwang.com.baselibrary.data.protocal.BaseResp
import rx.Observable
import rx.functions.Func1

/**
 * Created by Newterk on 2018/2/6.
 * 基本转换
 */
class  BaseFuncBoolean: Func1<BaseResp<*>,Observable<Boolean>> {
    override fun call(t: BaseResp<*>): Observable<Boolean> {
      return  when(t.status){
            200 -> Observable.just(true)
            else -> Observable.error(BaseException(status = t.status,msg = t.message))
        }
    }
}