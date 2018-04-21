package com.newtrekwang.messagecenter.widgets

import android.content.Context
import android.widget.FrameLayout
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import android.widget.ViewFlipper
import com.newtrekwang.messagecenter.R
import kotlinx.android.synthetic.main.layout_news_flipper.view.*
import org.jetbrains.anko.dimen
import org.jetbrains.anko.find
import org.jetbrains.anko.px2sp

/**
 * Created by Newterk on 2018/4/21.
 */
class NewsFlipperView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : FrameLayout(context, attrs, defStyleAttr)   {
    private lateinit var mFlipperView : ViewFlipper
    init {
        val rootView = View.inflate(context, R.layout.layout_news_flipper, null)
        mFlipperView = rootView.find(R.id.mFlipperView)
        mFlipperView.setInAnimation(context, R.anim.news_bottom_in)
        mFlipperView.setOutAnimation(context, R.anim.news_bottom_out)
        addView(rootView)
    }


    /*
        构建公告
     */
    private fun buildNewsView(text: String): View {
        val textView = TextView(context)
        textView.text = text
        textView.textSize = px2sp(dimen(R.dimen.text_small_size))
        textView.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        return textView
    }

    /*
        设置公告数据
     */
    fun setData(data: Array<String>) {
        for (text in data) {
            mFlipperView.addView(buildNewsView(text))
        }
        mFlipperView.startFlipping()
    }
}