package com.ashleyleung.jetpack_b_station.musicList

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ashleyleung.jetpack_b_station.R
import kotlinx.android.synthetic.main.activity_music.*
import kotlinx.android.synthetic.main.activity_player.*

/**
 * @author lxy
 * @name
 * @class describe
 * @date 2022-06-22
 * Copyright (c)  Guangzhou GCI Science & Technology Co., Ltd.All rights reserved
 */
class MusicActivity : AppCompatActivity() {

    private val musicPresenter by lazy {
        MusicPresenter()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music)
        initDataListener()
        initListener()

    }

    //监听数据变化
    private fun initDataListener() {
        musicPresenter.musicList.addListener {
            println(Thread.currentThread().name)
            println("musicList --->  ${it?.size}")
            var str = "加载到了-->  ${it?.size}条数据"
            tvMusicNum?.text =str
        }
        musicPresenter.loadState.addListener {
            println("加载状态 --->  $it")
        }
    }

    private fun initListener() {
        btn_get_music_list.setOnClickListener {
            musicPresenter.getMusic()
        }
    }
}