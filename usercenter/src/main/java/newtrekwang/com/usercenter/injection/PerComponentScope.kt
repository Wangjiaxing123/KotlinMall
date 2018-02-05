package newtrekwang.com.usercenter.injection

import java.lang.annotation.Documented
import java.lang.annotation.Retention
import javax.inject.Scope
import java.lang.annotation.RetentionPolicy.RUNTIME
/**
 * Created by Newterk on 2018/2/5.
 */
@Scope
@Documented
@Retention(RUNTIME)
annotation class PerComponentScope