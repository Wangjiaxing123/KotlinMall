package newtrekwang.com.usercenter.service

import newtrekwang.com.usercenter.data.protocol.UserInfo
import rx.Observable
import rx.Observer

/**
 * Created by dell on 2018/2/3.
 */
interface UserService {
    fun register (mobile: String,vertigyCode: String,pwd: String):Observable<Boolean>
    fun login (phone: String,pwd: String,pushId: String):Observable<UserInfo>
    fun forgetPwd(mobile: String,vertigyCode: String): Observable<Boolean>
    fun resetPwd(mobile: String,newPwd: String): Observable<Boolean>
}