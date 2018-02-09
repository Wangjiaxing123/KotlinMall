package newtrekwang.com.baselibrary.presenter.view

/**
 * Created by dell on 2018/1/30.
 */
interface BaseView {
    fun showLoading()
    fun hideLoading()
    fun onError(text:String)
    fun showToast(str: String)
}