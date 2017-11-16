package com.yhao.commen.download

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Author: weichyang
 * Date:   2017/11/15
 * Description: 重写响应体
 */
class ProgressInterceptor(private val progressListener: ProgressListener) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val originalResponse = chain.proceed(chain.request())

        //!! 不允许为 null
        return originalResponse.newBuilder().body(ProgressResponseBody(originalResponse.body()!!, progressListener)).build()
    }
}