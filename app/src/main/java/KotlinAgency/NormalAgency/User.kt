package KotlinAgency.NormalAgency

import kotlin.properties.Delegates

/**
 * Created by weichyang on 2017/11/2.
 * 实现属性变换监听器
 */
class User {

    var name: String by Delegates.observable("初始值"){
        prop,old,new ->
        println("被赋值的属性:$prop"+"旧值：$old->新知：$new")
    }

//    输出内容
//    被赋值的属性:property name (Kotlin reflection is not available)旧值：初始值->新知：第一次赋值
//    被赋值的属性:property name (Kotlin reflection is not available)旧值：第一次赋值->新知：第二次赋值
}