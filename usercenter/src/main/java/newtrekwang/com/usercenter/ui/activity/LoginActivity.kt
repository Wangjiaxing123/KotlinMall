package newtrekwang.com.usercenter.ui.activity

import android.os.Bundle
import android.view.View

import kotlinx.android.synthetic.main.activity_login.*
import newtrekwang.com.baselibrary.common.AppManager
import newtrekwang.com.baselibrary.ext.eable
import newtrekwang.com.baselibrary.ui.activity.BaseMvpActivity
import newtrekwang.com.usercenter.R
import newtrekwang.com.usercenter.UserPrefsUtils
import newtrekwang.com.usercenter.data.protocol.UserInfo
import newtrekwang.com.usercenter.injection.component.DaggerUserComponent
import newtrekwang.com.usercenter.injection.module.UserModule
import newtrekwang.com.usercenter.presenter.LoginPresenter

import newtrekwang.com.usercenter.presenter.view.LoginView
import org.jetbrains.anko.startActivity


import org.jetbrains.anko.toast


class LoginActivity : BaseMvpActivity<LoginPresenter>(), LoginView, View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initView()
    }

    /**
     * 初始化视图
     */
    private fun initView() {
        mLoginBtn.eable(mMobileEt,{isButtonEable()})
        mLoginBtn.eable(mPwdEt,{isButtonEable()})

        mLoginBtn.setOnClickListener(this)
        mForgetPwdTv.setOnClickListener(this)
        mHeaderBar.getRightView().setOnClickListener(this)
    }

    /**
     * 依赖注入
     */
    override fun initInjection() {
        DaggerUserComponent.builder()
                .activityComponent(activityComponent)
                .userModule(UserModule())
                .build()
                .inject(this)

        mPresenter.mView = this

    }

    /**
     * 点击事件
     */
    override fun onClick(view: View) {
        when (view.id) {
            R.id.mLoginBtn -> {
                mPresenter.login(mMobileEt.text.toString(),mPwdEt.text.toString(),"")
            }
            R.id.mForgetPwdTv -> {
                startActivity<ForgetPwdActivity>()
            }
            R.id.mRightTv -> {
                startActivity<RegisterActivity>()
            }
        }
    }

    /**
     * 判断两个输入框输入的字符串是否都不为空
     */
    private fun isButtonEable(): Boolean {
        return mMobileEt.text.toString().isNullOrEmpty().not() &&
                mPwdEt.text.toString().isNullOrEmpty().not()
    }

    override fun onLoginResult(userInfo: UserInfo) {
        toast("登录成功！")
        UserPrefsUtils.putUserInfo(userInfo)
        startActivity<UserInfoActivity>()
    }
}
