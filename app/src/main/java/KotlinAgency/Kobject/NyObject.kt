package KotlinAgency.Kobject

/**
 * Created by weichyang on 2017/11/3.
 */
class NyObject {

    open class A(x: Int) {
        public open val y: Int = x
    }

    interface B {}

    val ab: A = object : A(1), B {
        override val y: Int = 15
    }

}

fun main(args: Array<String>) {

    //正常对象初始化
    val v: NyObject.A = NyObject.A(1)


    //采用对象表达式初始化
    val v1: NyObject.A = object : NyObject.A(1), NyObject.B {
        override val y: Int = 20
    }

    println(v1.y)

    //通过对象表达式可以直接约过类的定义得到一个对象
    val site =object {
        var name:String="菜鸟教程"
        var url:String="www.runoob.com"
    }

    println(site.name)
    println(site.url)

}

