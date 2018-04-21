package newtrekwang.com.baselibrary.widgets

import android.content.Context
import android.widget.ImageView
import com.youth.banner.loader.ImageLoader
import newtrekwang.com.baselibrary.utils.GlideUtils

/**
 * Created by Newterk on 2018/2/20.
 * 图片加载器
 */
class BannerImageLoader :ImageLoader() {
    override fun displayImage(context: Context, path: Any, imageView: ImageView) {
        GlideUtils.loadUrlImage(context,path.toString(),imageView)
    }
}