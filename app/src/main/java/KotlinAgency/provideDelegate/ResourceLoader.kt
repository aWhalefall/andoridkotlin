import kotlin.reflect.KProperty

/**
 * 就是给A和委托B之间插入一个中间件而已
 */

class dge<T>(t: T) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, 这里委托了 ${property.name} 属性"
    }
}

class ResourceLoader<T>(id: Int) {
    //可以扩展创建属性实现所委托对象的逻辑
    operator fun provideDelegate(
            thisRef: MyUI,
            prop: KProperty<*>): dge<T?> {
        checkProperty(thisRef, prop.name)
        // 创建委托
        var t: T? = null
        return dge(t)
    }

    private fun checkProperty(thisRef: MyUI, name: String) {
        println(name)
    }
}

fun <T> bindResource(id: Int): ResourceLoader<T> {
    return ResourceLoader<T>(id)
}

class MyUI {
    //被委托类.委托类,委托
    //将 image 和 text的 get，set属性 委托，委托类个getValue( )
    val image by ResourceLoader<Int>(1) //两个属性进行了委托、实现getValue()
    val text by ResourceLoader<Int>(2)
}

fun main(args: Array<String>) {
    val myui: MyUI = MyUI()
    println(myui.image)
}