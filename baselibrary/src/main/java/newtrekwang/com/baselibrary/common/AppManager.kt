package newtrekwang.com.baselibrary.common

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import java.util.*

/**
 * Created by Newterk on 2018/2/6.
 */
class AppManager private constructor(){
//  activity管理栈
    private val activityStack: Stack<Activity> = Stack()

    companion object {
        val instance : AppManager by lazy { AppManager() }
    }

    /**
     * 入栈
     */
    fun addActivity(activity: Activity){
        activityStack.add(activity)
    }

    /**
     * 出栈
     */
    fun finishActivity(activity: Activity){
        activity.finish()
        activityStack.remove(activity)
    }

    /**
     * 当前activity,也就是栈的最后一位元素
     */
    fun currentActivity(): Activity{
        return activityStack.lastElement()
    }

    /**
     * 清理栈
     */
    fun finishAllActivity(){
        for(activity in activityStack){
            activity.finish()
        }
        activityStack.clear()
    }

    /**
     * 退出应用
     */
    @SuppressLint("MissingPermission")
    fun exitApplication(context: Context){
        finishAllActivity()
        val activityManager:ActivityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        activityManager.killBackgroundProcesses(context.packageName)
        System.exit(0)
    }
}