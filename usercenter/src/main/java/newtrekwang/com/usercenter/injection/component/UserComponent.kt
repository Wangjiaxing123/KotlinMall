package newtrekwang.com.usercenter.injection.component

import dagger.Component
import newtrekwang.com.baselibrary.injection.component.ActivityComponent
import newtrekwang.com.usercenter.injection.PerComponentScope
import newtrekwang.com.usercenter.injection.module.UserModule
import newtrekwang.com.usercenter.presenter.RegisterPresenter
import newtrekwang.com.usercenter.ui.activity.LoginActivity
import newtrekwang.com.usercenter.ui.activity.RegisterActivity

/**
 * Created by WJX on 2018/2/3.
 *
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),modules = arrayOf(UserModule::class))
interface UserComponent {
    fun  inject(activity:RegisterActivity)
    fun  inject(activity: LoginActivity)
}