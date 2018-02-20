package newtrekwang.com.usercenter.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.jph.takephoto.app.TakePhoto
import com.jph.takephoto.app.TakePhotoImpl
import com.jph.takephoto.compress.CompressConfig
import com.jph.takephoto.model.TResult
import com.qiniu.android.http.ResponseInfo
import com.qiniu.android.storage.UpCompletionHandler
import com.qiniu.android.storage.UploadManager

import kotlinx.android.synthetic.main.activity_user_info.*
import newtrekwang.com.baselibrary.common.BaseConstant
import newtrekwang.com.baselibrary.ext.onClick
import newtrekwang.com.baselibrary.ui.activity.BaseMvpActivity
import newtrekwang.com.baselibrary.utils.DateUtils
import newtrekwang.com.baselibrary.utils.GlideUtils
import newtrekwang.com.usercenter.R
import newtrekwang.com.usercenter.data.protocol.UserInfo
import newtrekwang.com.usercenter.injection.component.DaggerUserComponent
import newtrekwang.com.usercenter.injection.module.UserModule
import newtrekwang.com.usercenter.presenter.UserInfoPresenter

import newtrekwang.com.usercenter.presenter.view.UserInfoView


import org.jetbrains.anko.toast
import org.json.JSONObject
import java.io.File

/**
 * 用户信息
 */
class UserInfoActivity : BaseMvpActivity<UserInfoPresenter>(), UserInfoView, View.OnClickListener , TakePhoto.TakeResultListener{


    private lateinit var mTakePhoto: TakePhoto

    private lateinit var mTempFile: File

    private val mUploadManager: UploadManager by lazy{UploadManager()}

    private var localFile:String? = null
    private var remoteFile:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        mTakePhoto = TakePhotoImpl(this,this)

        initView()

        mTakePhoto.onCreate(savedInstanceState)

    }

    /**
     * 初始化视图
     */
    private fun initView() {
        mHeaderBar.getRightView().setOnClickListener(this)
        mUserIconView.onClick {
            showAlertView()
        }
    }

    private fun showAlertView() {
        AlertView("选择图片","","取消",null, arrayOf("拍照","相册"),this,AlertView.Style.ActionSheet,object : OnItemClickListener{
            override fun onItemClick(o: Any?, position: Int) {

                mTakePhoto.onEnableCompress(CompressConfig.ofDefaultConfig(),false)

                when(position){
                    0 -> {
                        createTempFile()
                        mTakePhoto.onPickFromCapture(Uri.fromFile(mTempFile))
                    }
                    1 -> mTakePhoto.onPickFromGallery()
                }
            }
        }).show()

    }

    /**
     * 依赖注入
     */
    override fun initInjection() {
        DaggerUserComponent.builder()
                .activityComponent(activityComponent)
                .userModule(UserModule())
                .build()
                .inject(this)

        mPresenter.mView = this

    }

    /**
     * 点击事件
     */
    override fun onClick(view: View) {
        when (view.id) {

        }
    }


    override fun takeSuccess(result: TResult?) {
            Log.d("Takephoto:",result?.image?.originalPath)
            Log.d("Takephoto:",result?.image?.compressPath)
            localFile = result?.image?.compressPath

             mPresenter.getUpLoadToken()
    }

    override fun takeCancel() {
    }

    override fun takeFail(result: TResult?, msg: String?) {
        Log.e("takePhoto",msg)
    }

    override fun onUserInfoResult(userInfo: UserInfo) {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mTakePhoto.onActivityResult(requestCode,resultCode,data)
    }

    fun createTempFile(){
        val tempFileName = "${DateUtils.curTime}.png"
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
            this.mTempFile = File(Environment.getExternalStorageDirectory(),tempFileName)
            return
        }
        this.mTempFile = File(filesDir,tempFileName)
    }

    override fun onGetUploadToken(token: String) {
        mUploadManager.put(localFile,null,token,object: UpCompletionHandler{
            override fun complete(key: String?, info: ResponseInfo?, response: JSONObject?) {
                Log.d("uploadInfo:",info.toString())
                remoteFile =BaseConstant.IMAGE_SERVER_ADDRESS+response?.get("hash")
                Log.d("upload:",remoteFile)
            GlideUtils.loadImage(this@UserInfoActivity,remoteFile!!,mUserIconIv)
            }

        },null)
    }

}
