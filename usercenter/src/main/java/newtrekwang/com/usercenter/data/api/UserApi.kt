package newtrekwang.com.usercenter.data.api


import newtrekwang.com.baselibrary.data.protocal.BaseResp
import newtrekwang.com.usercenter.data.protocol.*
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

    @POST("userCenter/forgetPwd")
    fun forgetPwd(@Body forgetPwdReq: ForgetPwdReq): Observable<BaseResp<String>>

    @POST("userCenter/resetPwd")
    fun resetPwd(@Body resetPwdReq: ResetPwdReq): Observable<BaseResp<String>>

    @POST("userCenter/editUser")
    fun editUser(@Body editUser: EditUserReq):Observable<BaseResp<UserInfo>>
}