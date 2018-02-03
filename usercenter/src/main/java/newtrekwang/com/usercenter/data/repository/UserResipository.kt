package newtrekwang.com.usercenter.data.repository


import newtrekwang.com.baselibrary.data.net.RetrofitFactory
import newtrekwang.com.baselibrary.data.protocal.BaseResp
import newtrekwang.com.usercenter.data.api.UserApi
import retrofit2.http.Path
import rx.Observable
import javax.inject.Inject

/**
 * Created by WJX on 2018/2/3.
 *
 */
class   UserResipository @Inject constructor(){
    fun register( name:String,phone:String,pwd:String): Observable<BaseResp<String>> {
     return   RetrofitFactory.instance.create(UserApi::class.java)
             .register(name,phone,pwd)
    }

}