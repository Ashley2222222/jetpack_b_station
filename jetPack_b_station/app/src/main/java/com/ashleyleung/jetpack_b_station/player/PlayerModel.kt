package com.ashleyleung.jetpack_b_station.player

import com.ashleyleung.jetpack_b_station.player.domain.Music

/**
 * @author lxy
 * @name
 * @class describe
 * @date 2022-06-21
 *
 */
class PlayerModel {
    fun getMusicById(id: String): Music {
        return Music(
            "歌曲名： $id",
            "https://i.loli.net/2020/05/02/cDYxJdkVjnMetif.png?x-oss-process=image/resize,h_118",
            "https://www.sunofbeach.net"

        )
    }
}