package newtrekwang.com.baselibrary.ui.fragment

import android.os.Bundle
import newtrekwang.com.baselibrary.common.BaseApplication
import newtrekwang.com.baselibrary.injection.component.ActivityComponent
import newtrekwang.com.baselibrary.injection.component.DaggerActivityComponent
import newtrekwang.com.baselibrary.injection.module.ActivityModule
import newtrekwang.com.baselibrary.injection.module.LifeCycleComponentMudule
import newtrekwang.com.baselibrary.presenter.BasePresenter
import newtrekwang.com.baselibrary.presenter.view.BaseView
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject

/**
 * Created by dell on 2018/1/30.
 */
abstract class BaseMvpFragment<T: BasePresenter<*>> :BaseFragment(),BaseView {
    override fun onError(text: String) {

    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showToast(str: String) {
        toast(str)
    }

    @Inject
    lateinit var  mPresenter: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivityInjection()
        initInjection()
    }

    /**
     * 让子类记得实现注入
     */
    abstract fun initInjection()
// activity的注入器
    lateinit var activityComponent: ActivityComponent
// 初始化activity级别的注入器
    private fun initActivityInjection() {
        activityComponent = DaggerActivityComponent.builder().appComponent((activity.application as BaseApplication).appComponent)
                .activityModule(ActivityModule(activity))
                .lifeCycleComponentMudule(LifeCycleComponentMudule(this))
                .build()
    }
}