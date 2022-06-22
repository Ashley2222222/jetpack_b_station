package com.ashleyleung.jetpack_b_station

import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author lxy
 * @name
 * @class describe
 * @date 2022-06-20
 * Copyright (c)  Guangzhou GCI Science & Technology Co., Ltd.All rights reserved
 */
class LoginMvcActivity : AppCompatActivity(), UserModel.onLoginStateChange {

    private val userModel by lazy { UserModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initListener()
    }

    private fun initListener() {
        btn_login.setOnClickListener {
            toLogin()
        }
    }

    private fun toLogin() {
        //做登录的逻辑处理
        val account: String = accountInputBox.text.toString()
        val pwd: String = passwordInputBox.text.toString()

        //检查账号格式是否正确
        if (TextUtils.isEmpty(account)) {
            tvLoginTips.text = "plz input account"
            return
        }
        //检查密码长度是否正确
        if (TextUtils.isEmpty(pwd)) {
            tvLoginTips.text = "plz input pwd"
            return
        }


        // 4给密码加盐
        userModel.checkUserState(account) {
            when (it) {
                0 -> {
                    //不可用已注册
                    tvLoginTips.text = "不可用已注册"
                }
                1 -> {
                    //没注册，可用
                    //进行登录,此操作为异步
                    userModel.doLogin(this, account, pwd)
//                    btn_login.isEnabled = false
                }
            }
        }
        //禁止按钮可以点击,不可以点击了，防止重重复提交
        btn_login.isEnabled = false
        //5进行登录,异步
        userModel.doLogin(this, account, pwd)

    }


    override fun onLoading() {
        tvLoginTips.text = "Loading..."

    }

    override fun onLoadingSuccess() {
        btn_login.isEnabled = true
        btn_login.isClickable = true
        tvLoginTips.text = "success"

    }

    override fun onLoadingFailure() {
        btn_login.isEnabled = true
        btn_login.isClickable = true
        tvLoginTips.text = "Failure"
    }
}