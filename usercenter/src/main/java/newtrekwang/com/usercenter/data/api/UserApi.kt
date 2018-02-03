package newtrekwang.com.usercenter.data.api


import newtrekwang.com.baselibrary.data.protocal.BaseResp
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable

/**
 * Created by WJX on 2018/2/3.
 *
 */
interface UserApi {

   @GET("regist")
    fun register(@Query("name") name:String, @Query("phone") phone:String, @Query("pwd")pwd:String): Observable<BaseResp<String>>
}