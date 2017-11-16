package com.yhao.model.bean

/**
 * Author: weichyang
 * Date:   2017/11/1
 * Description:
 */


//{
//    "showapi_res_code": 0,
//    "showapi_res_error": "",
//    "showapi_res_body": {
//    "allNum": 933,
//    "allPages": 47,
//    "contentlist": [
//    {
//        "ct": "　2015-07-10 05:54:00.000",
//        "text": "　　男生拉着女生沮丧着脸说，再给我一次机会，求求你！说好不提分手的！<br />\r\n　　女生甩开男孩的手说：你TM现在在我心里就是个菩萨，除了拜拜我什么都不想做。",
//        "title": "你TM现在在我心里就是个菩萨"
//    }
//    ],
//    "currentPage": 1,
//    "maxResult": 20
//}
//}

data class JokeResult(val showapi_res_code: String,
                      val showapi_res_error: String,
                      val showapi_res_body: JokeBody)


data class JokeBody(val allNum: String,
                    val allPages: String,
                    val currentPage: String,
                    val maxResult: String,
                    val contentlist: List<Joke>)


data class Joke(val title: String,
                val text: String)