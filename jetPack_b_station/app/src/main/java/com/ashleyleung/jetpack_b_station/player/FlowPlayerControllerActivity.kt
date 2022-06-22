package com.ashleyleung.jetpack_b_station.player

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ashleyleung.jetpack_b_station.R
import kotlinx.android.synthetic.main.activity_flow_player.*
import kotlinx.android.synthetic.main.activity_player.*

/**
 * @author lxy
 * @name
 * @class describe
 * @date 2022-06-21
 *
 */
class FlowPlayerControllerActivity : AppCompatActivity() {

    private val playerPresenter by lazy {
        PlayerPresenter.instance
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow_player)
//        playerPresenter.registerCallback(this)
        initListener()
        initDataListener()
    }

    private fun initDataListener() {
        playerPresenter.currentPlayState.addListener {
            btnPlayOrPause.text =
                if (it === PlayerPresenter.PlayState.PLAYING) {
                "暂停"
            } else {
                "播放"
            }
        }
    }

    private fun initListener() {
        btnPlayOrPause.setOnClickListener {
            playerPresenter.doPlayOrPause()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
//        playerPresenter.unregisterCallback(this)
    }

//    override fun onPlaying() {
//        btn_play_or_pause.text = "暂停"
//    }
//
//    override fun onPlayerPause() {
//        btn_play_or_pause.text = "播放"
//    }
//
//    override fun onTitleChange(title: String) {
//        TODO("Not yet implemented")
//    }
//
//    override fun pnProgressChange(current: Int) {
//        TODO("Not yet implemented")
//    }
//
//
//    override fun onCoverChange(cover: String) {
//        TODO("Not yet implemented")
//    }
}