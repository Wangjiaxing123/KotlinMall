package newtrekwang.com.usercenter.presenter

import android.content.Context
import com.trello.rxlifecycle.LifecycleProvider
import newtrekwang.com.baselibrary.ext.execute
import newtrekwang.com.baselibrary.presenter.BasePresenter
import newtrekwang.com.baselibrary.rx.BaseSubscriber
import newtrekwang.com.baselibrary.utils.NetWorkUtils
import newtrekwang.com.usercenter.data.protocol.UserInfo
import newtrekwang.com.usercenter.presenter.view.LoginView
import newtrekwang.com.usercenter.presenter.view.RegisterView
import newtrekwang.com.usercenter.service.UserService
import javax.inject.Inject

/**
 * Created by dell on 2018/1/31.
 */
class LoginPresenter @Inject constructor() :BasePresenter<LoginView>(){

    @Inject
    lateinit var userService: UserService

    @Inject
    lateinit var lifecycleProvider: LifecycleProvider<*>

    @Inject
    lateinit var context:Context


    fun login(phone: String,pwd: String, pushId: String){
        if(!canUseNetWork(context)) {
            return
        }
        mView.showLoading()
        userService.login(phone,pwd,pushId)
                .execute(object :BaseSubscriber<UserInfo>(mView){
                    override fun onNext(t: UserInfo) {
                        mView.onLoginResult(t)
                    }
                },lifecycleProvider)
    }
}