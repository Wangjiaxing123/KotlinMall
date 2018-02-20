package newtrekwang.com.usercenter.injection.module

import dagger.Module
import dagger.Provides
import newtrekwang.com.usercenter.service.UploadService
import newtrekwang.com.usercenter.service.impl.UploadServiceImpl

/**
 * Created by Newterk on 2018/2/20.
 */
@Module
class UploadModule {
    @Provides
    fun providesUploadService (service: UploadServiceImpl): UploadService {
        return service
    }
}