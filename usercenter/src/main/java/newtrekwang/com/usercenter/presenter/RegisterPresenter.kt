package newtrekwang.com.usercenter.presenter

import newtrekwang.com.baselibrary.presenter.BasePresenter
import newtrekwang.com.usercenter.presenter.view.RegisterView

/**
 * Created by dell on 2018/1/31.
 */
class RegisterPresenter :BasePresenter<RegisterView>(){
    fun register(mobile: String,vertify: String){
        //todo 业务逻辑
        mView.onRegisterResult(true)
    }
}