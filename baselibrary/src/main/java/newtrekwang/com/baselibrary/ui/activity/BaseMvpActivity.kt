package newtrekwang.com.baselibrary.ui.activity

import newtrekwang.com.baselibrary.presenter.BasePresenter
import newtrekwang.com.baselibrary.presenter.view.BaseView

/**
 * Created by dell on 2018/1/30.
 */
class BaseMvpActivity<T: BasePresenter<*>> :BaseActivity(),BaseView {
    override fun onError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    lateinit var  mPresenter: T
}