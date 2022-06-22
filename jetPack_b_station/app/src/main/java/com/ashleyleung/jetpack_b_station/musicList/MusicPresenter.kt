package com.ashleyleung.jetpack_b_station.musicList

import com.ashleyleung.jetpack_b_station.player.DataListenContainer
import com.ashleyleung.jetpack_b_station.player.domain.Music
import timber.log.Timber

/**
 * @author lxy
 * @name
 * @class describe
 * @date 2022-06-22
 * Copyright (c)  Guangzhou GCI Science & Technology Co., Ltd.All rights reserved
 */
class MusicPresenter {
    enum class MusicLoadState {
        LOADING, EMPTY, SUCCESS, ERROR

    }

    private val musicModel by lazy { MusicModel() }

    val musicList = DataListenContainer<List<Music>>()

    val loadState = DataListenContainer<MusicLoadState>()

    private val page = 1
    private val size = 20
    fun getMusic() {
        loadState.value = MusicLoadState.LOADING


//从modeL层中获取音乐列表
        musicModel.loadMusicByPage(page, size, object : MusicModel.onMusicLoadResult {
            override fun onSuccess(result: List<Music>) {
                musicList.value = result
                loadState.value = if (result.isEmpty())
                    MusicLoadState.EMPTY
                else
                    MusicLoadState.SUCCESS
            }

            override fun onError(msg: String, code: Int) {
                loadState.value = MusicLoadState.ERROR
                Timber.e("error...$msg...$code")
            }

        })
    }
}