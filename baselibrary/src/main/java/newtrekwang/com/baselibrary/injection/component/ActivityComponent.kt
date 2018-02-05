package newtrekwang.com.baselibrary.injection.component

import android.app.Activity
import android.content.Context
import com.trello.rxlifecycle.LifecycleProvider
import dagger.Component
import newtrekwang.com.baselibrary.injection.ActivityScope
import newtrekwang.com.baselibrary.injection.module.ActivityModule
import newtrekwang.com.baselibrary.injection.module.AppModule
import newtrekwang.com.baselibrary.injection.module.LifeCycleComponentMudule
import javax.inject.Singleton

/**
 * Created by Newterk on 2018/2/5.
 */
@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class),modules = arrayOf(ActivityModule::class,LifeCycleComponentMudule::class))
interface ActivityComponent {
    fun lifecycle():LifecycleProvider<*>
    fun activity(): Activity
    fun context(): Context
}