package newtrekwang.com.usercenter.service.impl

import newtrekwang.com.baselibrary.data.protocal.BaseResp
import newtrekwang.com.baselibrary.ext.convert
import newtrekwang.com.baselibrary.ext.convertBoolean
import newtrekwang.com.baselibrary.rx.BaseException
import newtrekwang.com.baselibrary.rx.BaseFuncBoolean
import newtrekwang.com.usercenter.data.protocol.UserInfo
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

    override fun register(mobile: String, vertifyCode: String, pwd: String):Observable<Boolean>{
       return  repository.register(phone = mobile,virifyCode = vertifyCode,pwd = pwd)
               .convertBoolean()
    }

    override fun login(phone: String, pwd: String, pushId: String): Observable<UserInfo> {
     return  repository.login(mobile = phone,pwd = pwd,pushId = pushId)
             .convert()
    }

    override fun forgetPwd(mobile: String, vertigyCode: String): Observable<Boolean> {
        return repository.forgetPwd(mobile,vertigyCode)
                .convertBoolean()
    }

    override fun resetPwd(mobile: String, newPwd: String): Observable<Boolean> {
        return repository.resetPwd(mobile,newPwd)
                .convertBoolean()
    }

    override fun editUser(userIcon: String, userName: String, userGender: String, userSign: String): Observable<UserInfo> {
    return repository.editUser(userIcon,userName,userGender,userSign)
            .convert()
    }
}
