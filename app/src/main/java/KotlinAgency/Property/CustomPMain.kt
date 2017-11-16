package KotlinAgency.Property

/**
 * Created by weichyang on 2017/11/2.
 *  属性委派有什么用处
 */

object CustomPMain {

    @JvmStatic
    fun main(args: Array<String>) {

        val e = PropertyDemo()
        println(e.custom)

        e.custom = "Runoob"   // 调用 setValue() 函数

        print(e.custom)

    }
}
