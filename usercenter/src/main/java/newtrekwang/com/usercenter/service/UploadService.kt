package newtrekwang.com.usercenter.service

import rx.Observable

/**
 * Created by Newterk on 2018/2/20.
 */
interface UploadService {
    fun getUploadToken():Observable<String>
}