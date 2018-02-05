package newtrekwang.com.baselibrary.ui.activity

import android.os.Bundle
import newtrekwang.com.baselibrary.common.BaseApplication
import newtrekwang.com.baselibrary.injection.component.ActivityComponent
import newtrekwang.com.baselibrary.injection.component.DaggerActivityComponent
import newtrekwang.com.baselibrary.injection.module.ActivityModule
import newtrekwang.com.baselibrary.presenter.BasePresenter
import newtrekwang.com.baselibrary.presenter.view.BaseView
import javax.inject.Inject

/**
 * Created by dell on 2018/1/30.
 */
open class BaseMvpActivity<T: BasePresenter<*>> :BaseActivity(),BaseView {
    override fun onError() {

    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    @Inject
    lateinit var  mPresenter: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivityInjection()
    }

    lateinit var activityComponent: ActivityComponent

    private fun initActivityInjection() {
        activityComponent = DaggerActivityComponent.builder().appComponent((application as BaseApplication).appComponent)
                .activityModule(ActivityModule(this)).build()
    }
}