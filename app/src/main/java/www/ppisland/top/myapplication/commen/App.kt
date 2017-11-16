package com.yhao.commen

import android.app.Application

/**
 * Created by yhao on 17-9-4.
 *
 */

class App : Application() {

    /**
     * Kotlin的class并不支持static变量,所以需要使用companion object来声明static变量,
     * 其实这个platformStatic变量也不是真正的static变量,而是一个伴生对象,
    这个伴生对象位于Message类中定义的一个叫做Companion的内部类中,如图:
     */
    companion object {

        //属性委托,当调用instace使用判断是否未null 否则会为null
        var instance: App by notNullSingleValue()
//        var instance: App by NotNullSingleValueVar()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}