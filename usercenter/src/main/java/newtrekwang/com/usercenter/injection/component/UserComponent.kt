package newtrekwang.com.usercenter.injection.component

import dagger.Component
import newtrekwang.com.baselibrary.injection.component.ActivityComponent
import newtrekwang.com.usercenter.injection.PerComponentScope
import newtrekwang.com.usercenter.injection.module.UploadModule
import newtrekwang.com.usercenter.injection.module.UserModule
import newtrekwang.com.usercenter.ui.activity.*

/**
 * Created by WJX on 2018/2/3.
 *
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),modules = arrayOf(UserModule::class,UploadModule::class))
interface UserComponent {
    fun  inject(activity: RegisterActivity)
    fun  inject(activity: LoginActivity)
    fun  inject(activity: ForgetPwdActivity)
    fun  inject(activity: ResetPwdActivity)
    fun  inject(activity: UserInfoActivity)
}