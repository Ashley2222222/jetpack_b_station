package com.ashleyleung.jetpack_b_station

import java.util.*

/**
 * @author lxy
 * @name
 * @class describe
 * @date 2022-06-20
 *
 */
class UserModel {
    private val api by lazy {
        API()
    }
    private val random = Random()

    /**
     * @name 登录
     * @describe
     * @author liangxy
     * @time 2022/6/20 15:41
     *
     */
    fun doLogin(callback: onLoginStateChange, account: String, pwd: String) {
        callback.onLoading()
        //开始调用登录api
//        api.login(account, pwd)
        //有结果
        val randomVal: Int = random.nextInt(2)
        if (randomVal == 0) {
            callback.onLoadingSuccess()
        } else {
            callback.onLoadingFailure()
        }
    }
    //0表示该账号已经注册//1表示该账号没有进行注册
    fun checkUserState(account:String,block: (Int) -> Unit) {
        block.invoke(random.nextInt(2))

    }


    interface onLoginStateChange {
        fun onLoading()
        fun onLoadingSuccess()
        fun onLoadingFailure()
    }
}