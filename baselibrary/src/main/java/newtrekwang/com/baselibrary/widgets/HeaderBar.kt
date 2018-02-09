package newtrekwang.com.baselibrary.widgets

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import newtrekwang.com.baselibrary.R

import kotlinx.android.synthetic.main.layout_header_bar.view.*
/**
 * Created by Newterk on 2018/2/7.
 */
class HeaderBar @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var isShowHeader = true
    private var titleText: String? = null
    private var rightText: String? = null

    init {
        var attributeSet = context.obtainStyledAttributes(attrs,R.styleable.HeaderBar)
        isShowHeader = attributeSet.getBoolean(R.styleable.HeaderBar_isShowBack,true)
        titleText = attributeSet.getString(R.styleable.HeaderBar_centerTitleText)
        rightText = attributeSet.getString(R.styleable.HeaderBar_rightText)
        initView()
        attributeSet.recycle()
    }

    private fun initView() {
        View.inflate(context,R.layout.layout_header_bar,this)
        mLeftIv.visibility = if (isShowHeader) View.VISIBLE else View.GONE
        titleText?.let {
            mTitleTv.text = it
        }

        rightText?.let {
            mRightTv.text = it
            mRightTv.visibility = View.VISIBLE
        }

        mLeftIv.setOnClickListener {
            if (context is Activity){
                (context as Activity).finish()
            }
        }
    }

    fun getRightView(): TextView{
        return mRightTv
    }
}