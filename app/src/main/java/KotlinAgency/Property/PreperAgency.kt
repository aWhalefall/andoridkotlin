package KotlinAgency.Property

import kotlin.reflect.KProperty

/**
 * Created by weichyang on 2017/11/2.
 */
class PreperAgency {
    val perAgency: String by preDelete()
}

class preDelete {
    operator fun getValue(preperAgency: PreperAgency, property: KProperty<*>): String {
        return "$preperAgency, 这里委托了 ${property.name} 属性"
    }
}

fun main(args: Array<String>) {
    val e = PreperAgency()
    print(e.perAgency)
}