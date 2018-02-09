package newtrekwang.com.baselibrary.ui.activity

import android.os.Bundle
import android.widget.Toast
import newtrekwang.com.baselibrary.common.BaseApplication
import newtrekwang.com.baselibrary.injection.component.ActivityComponent
import newtrekwang.com.baselibrary.injection.component.DaggerActivityComponent
import newtrekwang.com.baselibrary.injection.module.ActivityModule
import newtrekwang.com.baselibrary.injection.module.LifeCycleComponentMudule
import newtrekwang.com.baselibrary.presenter.BasePresenter
import newtrekwang.com.baselibrary.presenter.view.BaseView
import newtrekwang.com.baselibrary.widgets.ProgressDialog
import org.jetbrains.anko.toast
import javax.inject.Inject

/**
 * Created by dell on 2018/1/30.
 */
abstract class BaseMvpActivity<T: BasePresenter<*>> :BaseActivity(),BaseView {
    override fun onError(text:String) {
        showToast(text)
    }

    override fun showLoading() {
        loadingDialog.showLoading()
    }

    override fun hideLoading() {
        loadingDialog.hideLoading()
    }

    override fun showToast(str: String) {
        toast(str)
    }

    @Inject
    lateinit var  mPresenter: T

    private lateinit var loadingDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivityInjection()
        initInjection()

        loadingDialog = ProgressDialog.create(this)
    }

    /**
     * 让子类记得实现注入
     */
    abstract fun initInjection()
// activity的注入器
    lateinit var activityComponent: ActivityComponent
// 初始化activity级别的注入器
    private fun initActivityInjection() {
        activityComponent = DaggerActivityComponent.builder().appComponent((application as BaseApplication).appComponent)
                .activityModule(ActivityModule(this))
                .lifeCycleComponentMudule(LifeCycleComponentMudule(this))
                .build()
    }
}