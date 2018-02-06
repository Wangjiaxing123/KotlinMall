package newtrekwang.com.usercenter.ui.activity

import android.os.Bundle

import kotlinx.android.synthetic.main.activity_register.*
import newtrekwang.com.baselibrary.ext.onClick
import newtrekwang.com.baselibrary.ui.activity.BaseMvpActivity
import newtrekwang.com.usercenter.R
import newtrekwang.com.usercenter.injection.component.DaggerUserComponent
import newtrekwang.com.usercenter.injection.module.UserModule

import newtrekwang.com.usercenter.presenter.RegisterPresenter
import newtrekwang.com.usercenter.presenter.view.RegisterView

import org.jetbrains.anko.toast


class RegisterActivity : BaseMvpActivity<RegisterPresenter>(),RegisterView {


    override fun onRegisterResult(result: String) {
        toast(result)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        mPresenter.mView = this
       mRegistBtn.onClick {
           mPresenter.register("","","")
       }
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

    }
}
