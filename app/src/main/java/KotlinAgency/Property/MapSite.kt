package KotlinAgency.Property

/**
 * Created by weichyang on 2017/11/2.
 * 把属性存储在映射中
 *
 * 如果使用 var 属性，需要把 Map 换成 MutableMap：
 */
class Site2(val site2map: MutableMap<String, Any?>) {
    val name: String by site2map
    val url: String by site2map
}

fun main(args: Array<String>) {

    //var 可以进行内部元素添加减少

    var varsite: MutableMap<String, Any?> =
            mutableMapOf(
                    "name" to "菜鸟教程",
                    "url" to "www.runoob.com")

    val site = Site2(varsite)

    println(site.name)
    println(site.url)

    varsite.put("name", "Google")
    varsite.put("url", "www.google.com")

    println(site.name)
    print(site.url)


}