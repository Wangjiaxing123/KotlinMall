package newtrekwang.com.baselibrary.widgets

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.view.Gravity
import android.widget.ImageView
import newtrekwang.com.baselibrary.R

/**
 * Created by Newterk on 2018/2/7.
 */
class ProgressDialog private constructor(context: Context,theme: Int):Dialog(context,theme) {
    companion object {
    private lateinit var mDialog: ProgressDialog
        private var animatDrawable: AnimationDrawable? = null

        fun create(context: Context):ProgressDialog {
            mDialog = ProgressDialog(context, R.style.LightProgressDialog)
            mDialog.setContentView(R.layout.progress_dialog)
            mDialog.setCancelable(true)
            mDialog.setCanceledOnTouchOutside(false)
            mDialog.window.attributes.gravity = Gravity.CENTER

            val lp = mDialog.window.attributes
            lp.dimAmount = 0.2f

            mDialog.window.attributes = lp

            val imageView  = mDialog.findViewById<ImageView>(R.id.iv_loading)
            animatDrawable = imageView.background as AnimationDrawable
            return mDialog
        }
    }

    /**
     * 显示并播放动画
     */
    fun showLoading(){
        super.show()
        animatDrawable?.start()
    }

    /**
     * 隐藏
     */
    fun hideLoading(){
        super.dismiss()
        animatDrawable?.stop()
    }
}