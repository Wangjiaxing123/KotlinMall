package newtrekwang.com.usercenter.presenter

import newtrekwang.com.baselibrary.ext.execute
import newtrekwang.com.baselibrary.presenter.BasePresenter
import newtrekwang.com.baselibrary.rx.BaseSubscriber
import newtrekwang.com.usercenter.presenter.view.RegisterView
import newtrekwang.com.usercenter.service.UserService
import newtrekwang.com.usercenter.service.impl.UserServiceImpl
import javax.inject.Inject

/**
 * Created by dell on 2018/1/31.
 */
class RegisterPresenter @Inject constructor() :BasePresenter<RegisterView>(){

    @Inject
    lateinit var userService: UserService

    fun register(mobile: String,vertify: String,pwd: String){

        userService.register(mobile,vertify,pwd)
                .execute(object :BaseSubscriber<Boolean>(){
                    override fun onNext(t: Boolean) {
                        if(t != null){
                            mView.onRegisterResult(t)
                        }
                    }
                })

    }
}