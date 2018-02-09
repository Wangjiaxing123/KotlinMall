package newtrekwang.com.usercenter.ui.activity

import android.os.Bundle
import android.view.View

import kotlinx.android.synthetic.main.activity_register.*
import newtrekwang.com.baselibrary.common.AppManager
import newtrekwang.com.baselibrary.ext.eable
import newtrekwang.com.baselibrary.ext.onClick
import newtrekwang.com.baselibrary.ui.activity.BaseMvpActivity
import newtrekwang.com.usercenter.R
import newtrekwang.com.usercenter.injection.component.DaggerUserComponent
import newtrekwang.com.usercenter.injection.module.UserModule

import newtrekwang.com.usercenter.presenter.RegisterPresenter
import newtrekwang.com.usercenter.presenter.view.RegisterView

import org.jetbrains.anko.toast


class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView, View.OnClickListener {


    private var timeTemp: Long = 0

    override fun onRegisterResult(result: String) {
        toast(result)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initView()
    }

    /**
     * 初始化试图
     */
    private fun initView() {
        mRegisterBtn.eable(mMobileEt, { isButtonEable() })
        mRegisterBtn.eable(mVerifyCodeEt, { isButtonEable() })
        mRegisterBtn.eable(mPwdConfirmEt, { isButtonEable() })
        mRegisterBtn.eable(mPwdEt, { isButtonEable() })

        mVerifyCodeBtn.setOnClickListener(this)
        mRegisterBtn.setOnClickListener(this)
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
     * 点击两次退出应用
     */
    override fun onBackPressed() {
        val currentTime = System.currentTimeMillis()
        if ((currentTime - timeTemp) > 2000) {
            toast("再点击一次退出应用")
            timeTemp = currentTime
        } else {
            AppManager.instance.exitApplication(application)
        }
    }

    /**
     * 点击事件
     */
    override fun onClick(view: View) {
        when (view.id) {
            R.id.mVerifyCodeBtn -> {
                mVerifyCodeBtn.requestSendVerifyNumber()
                toast("发送验证码成功！")
            }
            R.id.mRegisterBtn -> {
                mPresenter.register(vertify = mVerifyCodeEt.text.toString(), mobile = mMobileEt.text.toString(), pwd = mPwdEt.text.toString())
            }
        }
    }

    /**
     * 判断四个输入框输入的字符串是否都不为空
     */
    private fun isButtonEable(): Boolean {
        return mMobileEt.text.toString().isNullOrEmpty().not() &&
                mVerifyCodeEt.text.toString().isNullOrEmpty().not() &&
                mPwdEt.text.toString().isNullOrEmpty().not() &&
                mPwdConfirmEt.text.toString().isNullOrEmpty().not()
    }
}
