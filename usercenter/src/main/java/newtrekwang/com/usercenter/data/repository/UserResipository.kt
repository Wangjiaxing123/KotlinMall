package newtrekwang.com.usercenter.data.repository


import newtrekwang.com.baselibrary.data.net.RetrofitFactory
import newtrekwang.com.baselibrary.data.protocal.BaseResp
import newtrekwang.com.usercenter.data.api.UserApi
import newtrekwang.com.usercenter.data.protocol.*
import retrofit2.http.Path
import rx.Observable
import javax.inject.Inject

/**
 * Created by WJX on 2018/2/3.
 * 数据仓库
 */
class   UserResipository @Inject constructor(){
    /**
     * 注册
     */
    fun register( virifyCode:String,phone:String,pwd:String): Observable<BaseResp<String>> {
     return   RetrofitFactory.instance.create(UserApi::class.java)
             .register(RegisterReq(mobile =phone,verifyCode = virifyCode,pwd = pwd ))
    }

    /**
     * 登录
     */
    fun login(mobile: String,pwd: String,pushId: String): Observable<BaseResp<UserInfo>>{
        return  RetrofitFactory.instance.create(UserApi::class.java)
                .login(loginReq = LoginReq(mobile=mobile,pwd = pwd,pushId = pushId))
    }

    /**
     * 忘记密码
     */
    fun forgetPwd(mobile: String,virifyCode: String): Observable<BaseResp<String>>{
        return RetrofitFactory.instance.create(UserApi::class.java)
                .forgetPwd(ForgetPwdReq(mobile,virifyCode))
    }

    /**
     * 重置密码
     */
    fun resetPwd(mobile: String,newPwd: String): Observable<BaseResp<String>>{
        return RetrofitFactory.instance.create(UserApi::class.java)
                .resetPwd(ResetPwdReq(mobile,newPwd))
    }

    /**
     * 编辑用户
     */
    fun editUser(userIcon: String,userName: String,gender: String,sign: String):Observable<BaseResp<UserInfo>>{
        return RetrofitFactory.instance.create(UserApi::class.java)
                .editUser(EditUserReq(userIcon,userName,gender,sign))
    }


}