package newtrekwang.com.usercenter.data.repository


import newtrekwang.com.baselibrary.data.net.RetrofitFactory
import newtrekwang.com.baselibrary.data.protocal.BaseResp
import newtrekwang.com.usercenter.data.api.UploadApi
import newtrekwang.com.usercenter.data.api.UserApi
import newtrekwang.com.usercenter.data.protocol.*
import retrofit2.http.Path
import rx.Observable
import javax.inject.Inject

/**
 * Created by WJX on 2018/2/3.
 * 数据仓库
 */
class UploadResipository @Inject constructor(){
    /**
     * 获取图片上传凭证
     */
    fun getUploadToken():Observable<BaseResp<String>>{
        return  RetrofitFactory.instance.create(UploadApi::class.java)
                .getUploadToken()
    }




}