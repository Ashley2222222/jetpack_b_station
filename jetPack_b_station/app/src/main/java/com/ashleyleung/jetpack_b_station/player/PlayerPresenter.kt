package com.ashleyleung.jetpack_b_station.player

import com.ashleyleung.jetpack_b_station.player.domain.Music
import timber.log.Timber

/**
 * @author lxy
 * @name
 * 播放
 * 暂停
 * 上一首
 * 下一首
 * --------
 *插播放的状态:
 *-通知UI改变成播放状态
 * -通知UI进度的变化
 *
 * 上一首，下一百
 *-通知uI歌曲标题变化
 *-通知UI歌出封面变化
 *
 * 暂停音乐
 *更新UI状态为暂停
 * --------------------------------------------6-22----------
 * 数据驱动：
 *
 *当前播放的歌曲
 * 当前播放的状态
 *
 *
 * @class describe
 * @date 2022-06-21
 *
 */
//class PlayerPresenter {
class PlayerPresenter private constructor() {
    //改为单例
    private val playerModel by lazy { PlayerModel() }

    private var currMusic: Music? = null


    private val player by lazy {
        MusicPlayer()
    }

    //    private  var currentMusic: Music? =  null   数据驱动就不这样写了
    var currentMusic = DataListenContainer<Music>()//去掉private 让数据可以监听到

    //            private var currentPlayState = PlayState.NONE
    var currentPlayState = DataListenContainer<PlayState>()

    companion object {
        val instance by lazy {
            PlayerPresenter()
        }
    }

    enum class PlayState {
        NONE, PLAYING, PAUSED, LOADING
    }

//    private val callbacksList = arrayListOf<IPlayerCallback>() //数据驱动不需要了
//
//
//    fun registerCallback(callback: IPlayerCallback) {
//        if (!callbacksList.contains(callback)) {
//            callbacksList.add(callback)
//        }
//
//    }
//
//    fun unregisterCallback(callback: IPlayerCallback) {
//        callbacksList.remove(callback)
//    }

    /**
     * @name PlayerPresenter
     * @describe 根据状态控制音乐播放还是暂停  上一首  下一首
     * @author liangxy
     * @time 2022/6/21 15:51
     *
     */
    var x: Int = 0

    fun doPlayOrPause() {
//        if (currMusic == null) {
//            //获取一首歌曲
//            currMusic = playerModel.getMusicById("xxx")
//        }

        if (currentMusic.value == null) {
            //获取一首歌曲
            currentMusic.value = playerModel.getMusicById("少女的祈祷" + x++)//lesson10
        }

//        player.play(currMusic)
        player.play(currentMusic.value)

        /*数据驱动不需要了
        dispatchTitleChange("当前播放的歌曲标题...")
        dispatchCoverChange("当前播放的歌曲封面...")*/

//        if (currentPlayState != PlayState.PLAYING) {
        if (currentPlayState.value == PlayState.PLAYING) {
            //开始播放音乐
//            dispatchPlayingState()
            currentPlayState.value = PlayState.PAUSED
        } else {
            //暂停
//            dispatchPauseState()
            currentPlayState.value = PlayState.PLAYING
        }


    }


////数据驱动不需要了
//    private fun dispatchPauseState() {
//        callbacksList.forEach {
//            it.onPlayerPause()
//            currentPlayState = PlayState.PAUSED
//
//        }
//    }
//
//    private fun dispatchPlayingState() {
//
//        callbacksList.forEach {
//            it.onPlaying()
//            currentPlayState = PlayState.PLAYING
//
//        }
//    }


    fun playNext() {
        //播放下一首
        //1、拿到下一首歌曲-- >变更UI，包括标题和封面
        currentMusic.value = playerModel.getMusicById("下一首：火鸟")

//  数据驱动不需要了
//        dispatchTitleChange("切换到下一首，标题变了")
//        dispatchCoverChange("切换到下一首，封面变了")
        //2、设置给播放器
        // 3、等待播放的回调通知
//        currentPlayState = PlayState.PLAYING
        currentPlayState.value = PlayState.PLAYING

    }
/*数据驱动不需要了
    private fun dispatchCoverChange(cover: String) {
        callbacksList.forEach {
            it.onCoverChange(cover)
        }
    }

    private fun dispatchTitleChange(title: String) {
        callbacksList.forEach {
            it.onTitleChange(title)
        }
    }*/

    fun playPre() {
        currentMusic.value = playerModel.getMusicById("上一首：白天鹅")
//     数据驱动不需要了
//        dispatchTitleChange("切换到上一首，标题变了")
//        dispatchCoverChange("切换到上一首，封面变了")
//        currentPlayState = PlayState.PLAYING
        currentPlayState.value = PlayState.PLAYING
    }


}