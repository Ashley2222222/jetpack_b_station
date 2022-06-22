package com.ashleyleung.jetpack_b_station

import android.os.Handler

/**
 * @author lxy
 * @name
 * @class describe
 * @date 2022-05-27
 *
 */
class SysApplication : BaseApplication(){

    companion object{
        val handler = Handler()
    }

    override fun onCreate(){
        super.onCreate()

    }

}