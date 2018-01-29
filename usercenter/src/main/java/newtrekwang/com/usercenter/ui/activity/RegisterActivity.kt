package newtrekwang.com.usercenter.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register.*
import newtrekwang.com.usercenter.R


class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        mRegistBtn.setOnClickListener {
            Toast.makeText(applicationContext,"regist",Toast.LENGTH_SHORT).show()
        }
    }
}
