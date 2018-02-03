package newtrekwang.com.usercenter.service.impl

import newtrekwang.com.baselibrary.data.protocal.BaseResp
import newtrekwang.com.baselibrary.rx.BaseException
import newtrekwang.com.usercenter.data.repository.UserResipository
import newtrekwang.com.usercenter.service.UserService
import rx.Observable
import rx.functions.Func1
import javax.inject.Inject

/**
 * Created by WJX on 2018/2/3.
 * disc:
 */
class UserServiceImpl @Inject constructor():UserService {

    @Inject
    lateinit var repository: UserResipository

    override fun register(mobile: String, vertigyCode: String, ped: String):Observable<Boolean>{
       return  repository.register(mobile,vertigyCode,ped)
               .flatMap(object : Func1<BaseResp<String>, Observable<Boolean>>{
                   override fun call(t: BaseResp<String>): Observable<Boolean> {
                       if (t.status != 200){
                           return Observable.error(BaseException(status = t.status,msg = t.message))
                       }else{
                           return Observable.just(true)
                       }
                   }

               })
    }
}