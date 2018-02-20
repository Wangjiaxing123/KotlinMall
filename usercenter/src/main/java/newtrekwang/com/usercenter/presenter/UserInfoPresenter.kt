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
import newtrekwang.com.usercenter.presenter.view.UserInfoView
import newtrekwang.com.usercenter.service.UploadService
import newtrekwang.com.usercenter.service.UserService
import javax.inject.Inject

/**
 * Created by dell on 2018/1/31.
 */
class UserInfoPresenter @Inject constructor() :BasePresenter<UserInfoView>(){

    @Inject
    lateinit var userService: UserService

    @Inject
    lateinit var uploadService: UploadService

    @Inject
    lateinit var lifecycleProvider: LifecycleProvider<*>

    @Inject
    lateinit var context:Context


    fun getUpLoadToken(){
        if(!canUseNetWork(context)){
            return
        }
        mView.showLoading()
        uploadService.getUploadToken()
                .execute(object : BaseSubscriber<String>(mView){
                    override fun onNext(t: String) {
                        mView.onGetUploadToken(t)
                    }
                },lifecycleProvider)
    }

    fun editUser(userIcon: String,userName: String,userGender: String,userSign: String){
        if(!canUseNetWork(context)){
            return
        }
        mView.showLoading()
        userService.editUser(userIcon,userName,userGender,userSign)
                .execute(object :BaseSubscriber<UserInfo>(mView){
                    override fun onNext(t: UserInfo) {
                        mView.onEditUserResult(t)
                    }
                },lifecycleProvider)
    }

}