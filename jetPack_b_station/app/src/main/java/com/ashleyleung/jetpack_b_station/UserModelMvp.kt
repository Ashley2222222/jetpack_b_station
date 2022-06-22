package com.ashleyleung.jetpack_b_station

import java.util.*

/**
 * @author lxy
 * @name
 * @class describe
 * @date 2022-06-20
 * Copyright (c)  Guangzhou GCI Science & Technology Co., Ltd.All rights reserved
 */
class UserModelMvp {

    companion object {
        const val STATE_LOADING = 0
        const val STATE_SUCCESS = 1
        const val STATE_FAILURE = 2
    }

    private val api by lazy {
        API()
    }
    private val random = Random()

    /**
     * @name 登录
     * @describe
     * @author liangxy
     * @time 2022/6/20 15:41
     * Copyright (c)  Guangzhou GCI Science & Technology Co., Ltd.All rights reserved
     */
    fun doLogin(account: String, pwd: String, block: (Int) -> Unit) {
        block.invoke(STATE_LOADING)
        //开始调用登录api
//        api.login(account, pwd)
        //有结果
        //开始去调用登录的API1/有结果，此操作为耗时操作
        //向服务器提交数据，这个时候，会使用非主线程去操作。
        // 异步操作，通知UI的时候，要切换成主线程，否则报错
        // 因为UI只能在主线程更新.|
        val randomVal: Int = random.nextInt(2)
        if (randomVal == 0) {
            block.invoke(STATE_SUCCESS)
        } else {
            block.invoke(STATE_FAILURE)
        }
    }

    //0表示该账号已经注册//1表示该账号没有进行注册
    fun checkUserState(account: String, block: (Int) -> Unit) {

        block.invoke(random.nextInt(2))

    }

}