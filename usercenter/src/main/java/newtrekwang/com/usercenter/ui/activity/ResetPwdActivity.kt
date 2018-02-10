package newtrekwang.com.usercenter.ui.activity

import android.os.Bundle
import android.view.View

import kotlinx.android.synthetic.main.activity_reset_pwd.*
import newtrekwang.com.baselibrary.ext.eable
import newtrekwang.com.baselibrary.ui.activity.BaseMvpActivity
import newtrekwang.com.usercenter.R
import newtrekwang.com.usercenter.injection.component.DaggerUserComponent
import newtrekwang.com.usercenter.injection.module.UserModule

import newtrekwang.com.usercenter.presenter.ResetPwdPresenter

import newtrekwang.com.usercenter.presenter.view.ResetPwdView
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.singleTop


import org.jetbrains.anko.toast


class ResetPwdActivity : BaseMvpActivity<ResetPwdPresenter>(), ResetPwdView, View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_pwd)

        initView()
    }

    /**
     * 初始化视图
     */
    private fun initView() {
        mConfirmBtn.eable(mPwdEt,{isButtonEable()})
        mConfirmBtn.eable(mPwdConfirmEt,{isButtonEable()})

        mConfirmBtn.setOnClickListener(this)
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
            R.id.mConfirmBtn -> {
                if(mPwdEt.text.toString() != mPwdConfirmEt.text.toString()){
                    toast("密码不一致！")
                    return
                }
             mPresenter.resetPwd(intent.getStringExtra("mobile"),mPwdEt.text.toString())
            }
        }
    }

    /**
     * 判断两个输入框输入的字符串是否都不为空
     */
    private fun isButtonEable(): Boolean {
        return mPwdEt.text.toString().isNullOrEmpty().not() &&
                mPwdConfirmEt.text.toString().isNullOrEmpty().not()
    }

    override fun onResetPwdResult(result: String) {
        toast(result)
        startActivity(intentFor<LoginActivity>().singleTop().clearTop())
    }
}
