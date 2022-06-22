package com.ashleyleung.jetpack_b_station.player

/**
 * @author lxy
 * @name
 * @class describe
 * @date 2022-06-21
 *
 */
interface IPlayerCallback {
    fun onTitleChange(title: String)
    fun pnProgressChange ( current: Int)
    fun onPlaying()
    fun onPlayerPause()
    fun onCoverChange(cover: String)


}