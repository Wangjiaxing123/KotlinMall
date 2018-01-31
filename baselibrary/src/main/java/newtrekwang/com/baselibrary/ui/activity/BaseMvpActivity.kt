package newtrekwang.com.baselibrary.ui.activity

import newtrekwang.com.baselibrary.presenter.BasePresenter
import newtrekwang.com.baselibrary.presenter.view.BaseView

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

    lateinit var  mPresenter: T
}