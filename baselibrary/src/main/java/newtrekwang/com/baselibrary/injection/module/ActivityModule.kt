package newtrekwang.com.baselibrary.injection.module

import android.app.Activity
import android.content.Context
import dagger.Module
import dagger.Provides
import newtrekwang.com.baselibrary.common.BaseApplication
import javax.inject.Singleton

/**
 * Created by Newterk on 2018/2/5.
 */

@Module
class ActivityModule(private  val activity: Activity) {

    @Provides
    fun providesActivity(): Activity = activity

}