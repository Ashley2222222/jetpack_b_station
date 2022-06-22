package com.ashleyleung.jetpack_b_station.player

import android.os.Looper
import androidx.arch.core.executor.ArchTaskExecutor
import com.ashleyleung.jetpack_b_station.SysApplication
import timber.log.Timber

/**
 * @author lxy
 * @name
 * @class describe 数据容器
 *  可以监听数据的变化
 * @date 2022-06-22
 * Copyright (c)  Guangzhou GCI Science & Technology Co., Ltd.All rights reserved
 */
class DataListenContainer<T> {
    private val blocks = arrayListOf<(T?) -> Unit>()

    var value: T? = null
        //当数据变化的时候:就通知更新
        set(value: T?) {
            var str = "set 数量：" + blocks.size
            Timber.e(str)
            //判断当前线程是不是主线程
            //如要是，则直接执行，否则切换到主线程再执行  确保线程UI 安全
            if (Looper.getMainLooper().thread === Thread.currentThread()) {
                blocks.forEach {
                    it.invoke(value)
                }
            } else {
                SysApplication.handler.post {
                    blocks.forEach {
                        it.invoke(value)
                    }
                }
            }


        }


    fun addListener(block: (T?) -> Unit) {
        if (!blocks.contains(block)) {
            blocks.add(block)
        }
        var str = "数量：" + blocks.size
        Timber.e(str)
    }
}