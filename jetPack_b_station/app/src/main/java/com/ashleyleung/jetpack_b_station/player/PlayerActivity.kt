package com.ashleyleung.jetpack_b_station.player

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ashleyleung.jetpack_b_station.R
import kotlinx.android.synthetic.main.activity_player.*
import timber.log.Timber

/**
 * @author lxy
 * @name
 * @class describe
 * @date 2022-06-21
 *
 */
//class PlayerActivity : AppCompatActivity(), IPlayerCallback { // 数据驱动不需要callback
class PlayerActivity : AppCompatActivity() {

    //  改单例前
//    private val playerPresenter by lazy {
//        PlayerPresenter()
//    }
    private val playerPresenter by lazy {
        PlayerPresenter.instance
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)


        initListener()
        //      数据驱动不需要这些
//        playerPresenter.registerCallback(this)
        initDataListener()
    }

    /**
     * @name PlayerActivity
     * @describe 对数据进行监听
     * @author liangxy
     * @time 2022/6/22 9:48
     *
     */
    private fun initDataListener() {
        playerPresenter.currentMusic.addListener {
            //音乐内容发生变化,更新uI
            tvTitle.text = it?.name

            println("封面改了。。。${it?.cover}")
        }
        playerPresenter.currentPlayState.addListener {
            when (it) {
                PlayerPresenter.PlayState.PAUSED -> {
//                    btn_play_or_pause.text = "播放"
                    btn_play_or_pause.text = "正在暂停点击播放"
                    Timber.e(btn_play_or_pause.text.toString())
                }
                PlayerPresenter.PlayState.PLAYING -> {
//                    btn_play_or_pause.text = "暂停"
                    btn_play_or_pause.text = "正在播放点击暂停"
                    Timber.e(btn_play_or_pause.text.toString())
                }

            }
        }

    }


    //给控件设置点击事件
    private fun initListener() {
        btn_play_or_pause.setOnClickListener {
            //调用presenter层的播放或者暂停方法
            playerPresenter.doPlayOrPause()
        }
        btn_next.setOnClickListener {
            //调用presenter层的播放或者暂停方法
            playerPresenter.playNext()
        }
        btn_pre.setOnClickListener {
            //调用presenter层的播放或者暂停方法
            playerPresenter.playPre()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
//        playerPresenter.unregisterCallback(this)
    }

//    override fun onTitleChange(title: String) {
//          tvTitle?.text = title
//      }
//
//      override fun pnProgressChange(current: Int) {
//          TODO("Not yet implemented")
//      }
//
//      override fun onPlaying() {
//          //播放中--->显示暂停
//          btn_play_or_pause.text = "暂停"
//          tvTitle?.text = "暂停"
//      }
//
//      override fun onPlayerPause() {
//          //暂停-->显示播放
//          btn_play_or_pause.text = "播放"
//      }
//
//      override fun onCoverChange(cover: String) {
//          println("cover change")
//      }
}