package newtrekwang.com.usercenter.service

import rx.Observable
import rx.Observer

/**
 * Created by dell on 2018/2/3.
 */
interface UserService {
    fun register (mobile: String,vertigyCode: String,pwd: String):Observable<Boolean>
}