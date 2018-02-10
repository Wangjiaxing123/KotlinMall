package newtrekwang.com.usercenter.ui.activity

import android.os.Bundle
import android.view.View

import kotlinx.android.synthetic.main.activity_forget_pwd.*
import newtrekwang.com.baselibrary.ext.eable
import newtrekwang.com.baselibrary.ui.activity.BaseMvpActivity
import newtrekwang.com.usercenter.R
import newtrekwang.com.usercenter.data.protocol.UserInfo
import newtrekwang.com.usercenter.injection.component.DaggerUserComponent
import newtrekwang.com.usercenter.injection.module.UserModule
import newtrekwang.com.usercenter.presenter.ForgetPwdPresenter
import newtrekwang.com.usercenter.presenter.view.ForgetPwdView

import org.jetbrains.anko.startActivity


import org.jetbrains.anko.toast


class ForgetPwdActivity : BaseMvpActivity<ForgetPwdPresenter>(), ForgetPwdView, View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_pwd)

        initView()
    }

    /**
     * 初始化视图
     */
    private fun initView() {
        mNextBtn.eable(mMobileEt,{isButtonEable()})
        mNextBtn.eable(mVerifyCodeEt,{isButtonEable()})

        mNextBtn.setOnClickListener(this)
        mVerifyCodeBtn.setOnClickListener(this)
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
            R.id.mNextBtn -> {
             mPresenter.forgetPwd(mMobileEt.text.toString(),mVerifyCodeEt.text.toString())
            }
            R.id.mVerifyCodeBtn -> {
                mVerifyCodeBtn.requestSendVerifyNumber()
                toast("验证码发送成功！")
            }
        }
    }

    /**
     * 判断两个输入框输入的字符串是否都不为空
     */
    private fun isButtonEable(): Boolean {
        return mMobileEt.text.toString().isNullOrEmpty().not() &&
                mVerifyCodeEt.text.toString().isNullOrEmpty().not()
    }

    override fun onForgetPwdResult(result: String) {
        toast(result)
        startActivity<ResetPwdActivity>("mobile" to mMobileEt.text.toString())
    }
}
