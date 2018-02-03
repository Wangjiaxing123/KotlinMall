package newtrekwang.com.usercenter.ui.activity

import android.os.Bundle

import kotlinx.android.synthetic.main.activity_register.*
import newtrekwang.com.baselibrary.ui.activity.BaseMvpActivity
import newtrekwang.com.usercenter.R
import newtrekwang.com.usercenter.injection.component.DaggerUserComponent
import newtrekwang.com.usercenter.injection.module.UserModule
import newtrekwang.com.usercenter.presenter.RegisterPresenter
import newtrekwang.com.usercenter.presenter.view.RegisterView

import org.jetbrains.anko.toast


class RegisterActivity : BaseMvpActivity<RegisterPresenter>(),RegisterView {


    override fun onRegisterResult(result: Boolean) {
        toast("注册成功！")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initInjection()

        mPresenter.mView = this
        mRegistBtn.setOnClickListener {
            //startActivity(intentFor<TestActivity>("id" to 4))
            mPresenter.register("","","")
        }
    }

    private fun initInjection() {

            DaggerUserComponent.builder().userModule(UserModule()).build().inject(this)
    }
}
