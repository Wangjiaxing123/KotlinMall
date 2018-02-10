package newtrekwang.com.usercenter.presenter.view

import newtrekwang.com.baselibrary.presenter.view.BaseView
import newtrekwang.com.usercenter.data.protocol.UserInfo

/**
 * Created by dell on 2018/1/31.
 */
interface ResetPwdView : BaseView {
    fun onResetPwdResult(result: String)
}