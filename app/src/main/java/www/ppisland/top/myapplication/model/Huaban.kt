package www.ppisland.top.myapplication.model

import com.google.gson.JsonObject

/**
 * Created by weichyang on 2017/10/31.
 */
data class HuabanResult(val showapi_res_code: String,
                        val showapi_res_error: String,
                        val showapi_res_body: JsonObject)

data class Huaban(val title: String,
                  val thumb: String,
                  val url: String)

