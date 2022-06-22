package com.ashleyleung.jetpack_b_station.mvp

import android.text.TextUtils
import com.ashleyleung.jetpack_b_station.UserModelMvp
import com.ashleyleung.jetpack_b_station.UserModelMvp.Companion.STATE_FAILURE
import com.ashleyleung.jetpack_b_station.UserModelMvp.Companion.STATE_LOADING
import com.ashleyleung.jetpack_b_station.UserModelMvp.Companion.STATE_SUCCESS
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author lxy
 * @name
 * @class describe
 * @date 2022-06-21
 *
 */
class LoginPresenter {
    private val userModel by lazy { UserModelMvp() }

    fun checkUserNameState(account: String, callback: OnCheckUserNameStateResultCallback) {
        userModel.checkUserState(account) {
            when (it) {
                0 -> {
                    callback.onExist()
                }
                1 -> {
                    callback.onNotExist()
                }
            }
        }
    }

    interface OnCheckUserNameStateResultCallback {
        fun onNotExist()
        fun onExist()
    }

    fun doLogin(account: String, pwd: String, callback: OnLoginStateChange) {
        //检查账号格式是否正确
        if (TextUtils.isEmpty(account)) {
            callback.onAccountFormatErr()

            return
        }
        //检查密码长度是否正确
        if (TextUtils.isEmpty(pwd)) {

            callback.onPwdFormatErr()
            return
        }



        userModel.doLogin(account, pwd) {
            when (it) {
                STATE_LOADING -> {
                    callback.onLoading()
                }
                STATE_SUCCESS -> {
                    callback.onSuccess()
                }
                STATE_FAILURE -> {
                    callback.onFailure()
                }
            }
        }
    }

    interface OnLoginStateChange {
        fun onAccountFormatErr()
        fun onPwdFormatErr()

        fun onLoading()
        fun onSuccess()
        fun onFailure()
    }
}