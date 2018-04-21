package newtrekwang.com.kotlinmall.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import kotlinx.android.synthetic.main.activity_main.*
import newtrekwang.com.kotlinmall.R
import newtrekwang.com.kotlinmall.ui.fragment.HomeFragment
import java.util.*

class MainActivity : AppCompatActivity() {

    private var pressTime: Long = 0
//    栈管理
    private val mStack = Stack<Fragment>()
    //主界面Fragment
    private val mHomeFragment by lazy { HomeFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        mBottomNavBar.checkCartBadge(1)
        mBottomNavBar.checkMsgBadge(true)
//        initFragment()
//        initBottomNav()
//        changeFragment(0)
    }

    private fun initView(){
        val manager = supportFragmentManager.beginTransaction();
        manager.replace(R.id.mContainer,mHomeFragment)
        manager.commit()
    }

    private fun changeFragment(position: Int) {
        val manager = supportFragmentManager.beginTransaction()
        for (fragment in mStack){
            manager.hide(fragment)
        }
        manager.show(mStack[position])
        manager.commit()

    }

    private fun initBottomNav() {
        mBottomNavBar.setTabSelectedListener(object :BottomNavigationBar.OnTabSelectedListener{
            override fun onTabReselected(position: Int) {

            }

            override fun onTabUnselected(position: Int) {
            }

            override fun onTabSelected(position: Int) {
                changeFragment(position)
            }

        })
    }

    private fun initFragment() {
        val manager = supportFragmentManager.beginTransaction();
        manager.add(R.id.mContainer,mHomeFragment)
        manager.commit()

        mStack.add(mHomeFragment)
    }
}
