package newtrekwang.com.baselibrary.data.protocal

/**
 * Created by WJX on 2018/2/3.
 *
 */
class BaseResp<T>(val status: Int,val message: String,val result: T) {

}