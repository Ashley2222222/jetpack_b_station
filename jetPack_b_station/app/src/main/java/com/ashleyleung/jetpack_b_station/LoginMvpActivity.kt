package com.ashleyleung.jetpack_b_station

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.ashleyleung.jetpack_b_station.mvp.LoginPresenter
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author lxy
 * @name
 * @class describe
 * @date 2022-06-20
 *
 */
class LoginMvpActivity : AppCompatActivity(),
    LoginPresenter.OnCheckUserNameStateResultCallback, LoginPresenter.OnLoginStateChange {

    //    private val userModel by lazy { UserModel() } mvc改造为mvp 把这个移到LoginPresenter
    private val loginPresenter by lazy { LoginPresenter() }

    private var isUserNameAvailable = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initListener()
    }

    private fun initListener() {
        btn_login.setOnClickListener {
            toLogin()
        }
        //mvc改造为mvp,监听内容变化
        accountInputBox.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                //检查当前账号是否注册
                loginPresenter.checkUserNameState(s.toString(), this@LoginMvpActivity)
            }
        })

    }

    private fun toLogin() {
        //做登录的逻辑处理
        val account: String = accountInputBox.text.toString()
        val pwd: String = passwordInputBox.text.toString()


        if (!isUserNameAvailable) {
            //提示用户名已被注册
            return
        }

//异步耗时操作
        loginPresenter.doLogin(account, pwd, this)


    }
//?判空免得页面已销毁时崩溃
    override fun onAccountFormatErr() {
        tvLoginTips?.text = "plz input account"
    }

    override fun onPwdFormatErr() {
        tvLoginTips?.text = "plz input pwd"
    }


    override fun onLoading() {
        tvLoginTips?.text = "Loading..."

    }

    override fun onSuccess() {
        tvLoginTips?.text = "Success"
    }

    override fun onFailure() {
        tvLoginTips?.text = "Failure"
    }


    override fun onNotExist() {
        //用户名可用
        tvLoginTips?.text = "该用户名未注册"
        isUserNameAvailable = true
    }

    override fun onExist() {
        //用户名不可用
        tvLoginTips.text = "该用户名已经注册"
        isUserNameAvailable = false
    }
}