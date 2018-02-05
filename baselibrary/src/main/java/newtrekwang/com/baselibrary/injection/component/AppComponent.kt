package newtrekwang.com.baselibrary.injection.component

import android.content.Context
import dagger.Component
import newtrekwang.com.baselibrary.injection.module.AppModule
import javax.inject.Singleton

/**
 * Created by Newterk on 2018/2/5.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun context(): Context
}