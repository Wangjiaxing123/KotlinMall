package newtrekwang.com.usercenter.data.api


import newtrekwang.com.baselibrary.data.protocal.BaseResp
import newtrekwang.com.usercenter.data.protocol.LoginReq
import newtrekwang.com.usercenter.data.protocol.RegisterReq
import newtrekwang.com.usercenter.data.protocol.UserInfo
import retrofit2.http.*
import rx.Observable

/**
 * Created by WJX on 2018/2/3.
 *
 */
interface UserApi {

   @POST("userCenter/register")
    fun register(@Body registerReq: RegisterReq): Observable<BaseResp<String>>

    @POST("userCenter/login")
    fun login(@Body loginReq: LoginReq): Observable<BaseResp<UserInfo>>
}