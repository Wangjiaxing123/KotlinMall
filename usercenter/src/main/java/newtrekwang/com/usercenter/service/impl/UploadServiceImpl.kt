package newtrekwang.com.usercenter.service.impl

import newtrekwang.com.baselibrary.data.protocal.BaseResp
import newtrekwang.com.baselibrary.ext.convert
import newtrekwang.com.baselibrary.ext.convertBoolean
import newtrekwang.com.baselibrary.rx.BaseException
import newtrekwang.com.baselibrary.rx.BaseFuncBoolean
import newtrekwang.com.usercenter.data.protocol.UserInfo
import newtrekwang.com.usercenter.data.repository.UploadResipository
import newtrekwang.com.usercenter.data.repository.UserResipository
import newtrekwang.com.usercenter.service.UploadService
import newtrekwang.com.usercenter.service.UserService
import rx.Observable
import rx.functions.Func1
import javax.inject.Inject

/**
 * Created by WJX on 2018/2/3.
 * disc:
 */
class UploadServiceImpl @Inject constructor():UploadService {



    @Inject
    lateinit var repository: UploadResipository

    override fun getUploadToken(): Observable<String> {
        return repository.getUploadToken()
                .convert()
    }
}
