package newtrekwang.com.usercenter.injection.module

import dagger.Module
import dagger.Provides
import newtrekwang.com.usercenter.service.UserService
import newtrekwang.com.usercenter.service.impl.UserServiceImpl

/**
 * Created by WJX on 2018/2/3.
 *
 */
@Module
class UserModule {
    @Provides
    fun providesUserService (service: UserServiceImpl):UserService{
        return service
    }

}