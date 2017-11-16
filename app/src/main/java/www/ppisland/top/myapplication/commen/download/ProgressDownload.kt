package com.yhao.commen.download

import com.yhao.commen.App
import com.yhao.commen.util.MD5Util
import okhttp3.*
import okio.Okio
import java.io.File
import java.io.IOException

/**
 * Created by yhao on 17-9-7.
 *
 */

object ProgressDownload {

    private var progressListener: ProgressListener? = null


    /**
     * lazy() 是一个函数, 接受一个 Lambda 表达式作为参数, 返回一个 Lazy 实例的函数
     *
     */
    private val mClient: OkHttpClient by lazy {
        //首次调用执行
        OkHttpClient.Builder().addInterceptor(ProgressInterceptor(listener)).build()
    }

    interface Test{
        fun nohao();
    }



    /**
     * 采用对象表达式可以方便使用接口初始化,
     * 1.成员变量
     * 2.函数形参中
     */
    private val listener: ProgressListener = object : ProgressListener {
        override fun onProgress(readByte: Long, totalByte: Long, done: Boolean) {
            if (progressListener != null) {
                progressListener!!.onProgress(readByte, totalByte, done)
            }
        }

        override fun onSave(filePath: String) {}
    }

    fun downloadPhoto(url: String, progressListener: ProgressListener) {

        val existFilePath: String? = exist(url)
        if (existFilePath != null) {
            progressListener.onSave(existFilePath)
            return
        }
        this.progressListener = progressListener
        val request = Request.Builder()
                .url(url)
                .build()
        mClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                val file = File(App.instance.cacheDir,MD5Util.getHashKey(url))
                val sink = Okio.buffer(Okio.sink(file))
                val source = response.body()!!.source()
                sink.writeAll(source)
                sink.flush()
                progressListener.onSave(file.absolutePath)
            }
        })
    }

    fun exist(url: String): String? {
        val file = File(App.instance.cacheDir, MD5Util.getHashKey(url))
        return if (file.exists()) file.absolutePath else null
    }

}
