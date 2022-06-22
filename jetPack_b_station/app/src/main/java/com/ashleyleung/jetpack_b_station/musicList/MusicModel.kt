package com.ashleyleung.jetpack_b_station.musicList

import com.ashleyleung.jetpack_b_station.player.domain.Music

/**
 * @author lxy
 * @name
 * @class describe
 * @date 2022-06-22
 * Copyright (c)  Guangzhou GCI Science & Technology Co., Ltd.All rights reserved
 */
class MusicModel {
    val result: ArrayList<Music> = arrayListOf<Music>()

    fun loadMusicByPage(page: Int, size: Int, callback: onMusicLoadResult) {
        Thread {
            for (i in (0 until size)) {
                result.add(
                    Music(
                        "音乐名称 $i",
                        "音乐封面 $i",
                        "url==> $i"
                    )
                )
            }
            //数据请求完成
            //通知数据更新
            callback.onSuccess(result)
        }.start()

    }

    interface onMusicLoadResult {
        fun onSuccess(result: List<Music>)
        fun onError(msg: String, code: Int)
    }
}