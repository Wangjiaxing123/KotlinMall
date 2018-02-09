package newtrekwang.com.baselibrary.presenter

import android.content.Context
import newtrekwang.com.baselibrary.presenter.view.BaseView
import newtrekwang.com.baselibrary.utils.NetWorkUtils

/**
 * Created by dell on 2018/1/30.
 */
open class BasePresenter<T: BaseView> {

    lateinit var mView: T
    /**
     * 检查网络是否可用
     */
    fun canUseNetWork(context: Context):Boolean{
        if(NetWorkUtils.isNetWorkAvailable(context)){
            return true
        }
        mView.onError("网络不可用！")
        return false
    }
}