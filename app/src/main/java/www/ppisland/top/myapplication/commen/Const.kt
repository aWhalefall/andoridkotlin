package com.yhao.commen

/**
 * Created by yhao on 17-9-6.
 *
 */

class Const {

    companion object {

        private val yiyuan_appid = "49087"
        private val yiyuan_secret = "24e8d74602ff476e9b5299b7667c2a6b"
        private val yiyuanAuth = "&showapi_sign=$yiyuan_secret&showapi_appid=$yiyuan_appid"

        fun buildUrl(url: String): String {
            return "$url&$yiyuanAuth"
        }
    }
}