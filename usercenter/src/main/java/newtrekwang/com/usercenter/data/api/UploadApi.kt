package newtrekwang.com.usercenter.data.api


import newtrekwang.com.baselibrary.data.protocal.BaseResp
import newtrekwang.com.usercenter.data.protocol.*
import retrofit2.http.*
import rx.Observable

/**
 * Created by WJX on 2018/2/3.
 *
 */
interface UploadApi {

   @GET("common/getUploadToken")
   fun getUploadToken():Observable<BaseResp<String>>

}