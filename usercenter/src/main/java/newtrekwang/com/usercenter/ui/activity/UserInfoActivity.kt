package newtrekwang.com.usercenter.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
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
import newtrekwang.com.baselibrary.utils.AppPrefsUtils
import newtrekwang.com.baselibrary.utils.DateUtils
import newtrekwang.com.baselibrary.utils.GlideUtils
import newtrekwang.com.provider.ProviderConstant
import newtrekwang.com.usercenter.R
import newtrekwang.com.usercenter.UserPrefsUtils
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
class UserInfoActivity : BaseMvpActivity<UserInfoPresenter>(), UserInfoView, TakePhoto.TakeResultListener{


    private lateinit var mTakePhoto: TakePhoto

    private lateinit var mTempFile: File

    private val mUploadManager: UploadManager by lazy{UploadManager()}

    private var localFileUri:String? = null
    private var remoteFileUri:String? = null

    private var mUserIcon: String? = null
    private var mUserMobile: String? = null
    private var mUserName: String? = null
    private var mUserGender: String? = null
    private var mUserSign: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        mTakePhoto = TakePhotoImpl(this,this)
        mTakePhoto.onCreate(savedInstanceState)

        initView()
        initData()

    }

    private fun initData() {
        mUserIcon = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_ICON)
        mUserName = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_NAME)
        mUserGender = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_GENDER)
        mUserSign = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_SIGN)
        mUserMobile = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_MOBILE)
        if(mUserIcon != ""){
            remoteFileUri = mUserIcon
            GlideUtils.loadImage(this,mUserIcon!!,mUserIconIv)
        }
        mUserNameEt.setText(mUserName)
        mUserMobileTv.setText(mUserMobile)
        if(mUserGender == "0"){
            mGenderMaleRb.isChecked = true
        }else{
            mGenderFemaleRb.isChecked = true
        }
        mUserSignEt.setText(mUserSign)
    }

    /**
     * 初始化视图
     */
    private fun initView() {
        mHeaderBar.getRightView().onClick {
            mPresenter.editUser(remoteFileUri!!,mUserNameEt.text?.toString()?:"",if(mGenderMaleRb.isChecked) "0" else "1",mUserSignEt.text?.toString()?:"")
        }
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



    override fun takeSuccess(result: TResult?) {
            Log.d("Takephoto:",result?.image?.originalPath)
            Log.d("Takephoto:",result?.image?.compressPath)
            localFileUri = result?.image?.compressPath

             mPresenter.getUpLoadToken()
    }

    override fun takeCancel() {
    }

    override fun takeFail(result: TResult?, msg: String?) {
        Log.e("takePhoto",msg)
    }

    override fun onEditUserResult(userInfo: UserInfo) {
        toast("修改成功！")
        UserPrefsUtils.putUserInfo(userInfo)
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
        mUploadManager.put(localFileUri,null,token,object: UpCompletionHandler{
            override fun complete(key: String?, info: ResponseInfo?, response: JSONObject?) {
                Log.d("uploadInfo:",info.toString())
                remoteFileUri =BaseConstant.IMAGE_SERVER_ADDRESS+response?.get("hash")
                Log.d("upload:", remoteFileUri)
            GlideUtils.loadImage(this@UserInfoActivity, remoteFileUri!!,mUserIconIv)
            }

        },null)
    }

}
