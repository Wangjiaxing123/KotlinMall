package newtrekwang.com.usercenter.injection.component

import dagger.Component
import newtrekwang.com.usercenter.injection.module.UserModule
import newtrekwang.com.usercenter.presenter.RegisterPresenter
import newtrekwang.com.usercenter.ui.activity.RegisterActivity

/**
 * Created by WJX on 2018/2/3.
 *
 */
@Component(modules = arrayOf(UserModule::class))
interface UserComponent {

fun  inject(activity:RegisterActivity)

}