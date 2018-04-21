package newtrekwang.com.kotlinmall.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import newtrekwang.com.baselibrary.ui.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*
import newtrekwang.com.baselibrary.widgets.BannerImageLoader
import newtrekwang.com.kotlinmall.*

/**
 * Created by Newterk on 2018/4/21.
 */
class HomeFragment :BaseFragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_home,null)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initBanner()
        initNews()
    }

    private fun initNews() {
        mNewsFlipperView.setData(arrayOf("夏日炎炎，第一波福利还有30秒到达战场, 新用户立领1000元优惠券"))
    }

    private fun initBanner() {
        mHomeBanner.setImageLoader(BannerImageLoader())
        mHomeBanner.setImages(listOf(HOME_BANNER_ONE, HOME_BANNER_TWO, HOME_BANNER_THREE, HOME_BANNER_FOUR))
        mHomeBanner.setBannerAnimation(Transformer.Accordion)
        mHomeBanner.setDelayTime(2000)
        //设置指示器位置（当banner模式中有指示器时）
        mHomeBanner.setIndicatorGravity(BannerConfig.RIGHT)
        //banner设置方法全部调用完毕时最后调用
        mHomeBanner.start()
    }

    private fun initView() {

    }
}