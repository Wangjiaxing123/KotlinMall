package newtrekwang.com.baselibrary.presenter

import newtrekwang.com.baselibrary.presenter.view.BaseView

/**
 * Created by dell on 2018/1/30.
 */
open class BasePresenter<T: BaseView> {
    lateinit var mView: T

}