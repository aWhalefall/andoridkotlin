package KotlinAgency.Property

import kotlin.reflect.KProperty

/**
 * Created by weichyang on 2017/11/2.
 */
class PropertyDemo {

    //属性委托指的是一个类的某个属性值不是在类中直接进行定义
    var p:String by Delegate() //而是将其托付给一个代理类，从而实现对该类的属性统一管理
    var custom:String by Delegate() //而是将其托付给一个代理类，从而实现对该类的属性统一管理
}

class Delegate{
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, 这里委托了 ${property.name} 属性"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$thisRef 的 ${property.name} 属性赋值为 $value")
    }
}

