package newtrekwang.com.baselibrary.injection.module

import com.trello.rxlifecycle.LifecycleProvider
import dagger.Module
import dagger.Provides

/**
 * Created by Newterk on 2018/2/5.
 */
@Module
class LifeCycleComponentMudule(private val lifecycle: LifecycleProvider<*>) {

    @Provides
    fun providesLifecycle():LifecycleProvider<*>{
        return lifecycle
    }
}