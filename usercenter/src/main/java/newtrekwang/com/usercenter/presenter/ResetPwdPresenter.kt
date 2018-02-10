package newtrekwang.com.usercenter.presenter

import android.content.Context
import com.trello.rxlifecycle.LifecycleProvider
import newtrekwang.com.baselibrary.ext.execute
import newtrekwang.com.baselibrary.presenter.BasePresenter
import newtrekwang.com.baselibrary.rx.BaseSubscriber
import newtrekwang.com.baselibrary.utils.NetWorkUtils
import newtrekwang.com.usercenter.data.protocol.UserInfo
import newtrekwang.com.usercenter.presenter.view.ForgetPwdView
import newtrekwang.com.usercenter.presenter.view.LoginView
import newtrekwang.com.usercenter.presenter.view.RegisterView
import newtrekwang.com.usercenter.presenter.view.ResetPwdView
import newtrekwang.com.usercenter.service.UserService
import javax.inject.Inject

/**
 * Created by dell on 2018/1/31.
 */
class ResetPwdPresenter @Inject constructor() :BasePresenter<ResetPwdView>(){

    @Inject
    lateinit var userService: UserService

    @Inject
    lateinit var lifecycleProvider: LifecycleProvider<*>

    @Inject
    lateinit var context:Context


    fun resetPwd(mobile: String,newPwd: String){
        if(!canUseNetWork(context)) {
            return
        }
        mView.showLoading()
        userService.resetPwd(mobile,newPwd)
                .execute(object :BaseSubscriber<Boolean>(mView){
                    override fun onNext(t: Boolean) {
                        if(t) mView.onResetPwdResult("重置密码成功！") else mView.onResetPwdResult("重置密码失败！")
                    }
                },lifecycleProvider)
    }
}