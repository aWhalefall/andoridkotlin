package KotlinAgency.Property

/**
 * Created by weichyang on 2017/11/2.
 * 把属性存储在映射中
 *
 * 如果使用 var 属性，需要把 Map 换成 MutableMap：
 */
class Site(val map:Map<String,Any>) {
    val name:String by map
    val url: String by map
}

fun main(args:Array<String>){
    val site =Site(mapOf("name" to "菜鸟教程",
            "url" to "www.runoob.com"))

    println(site.name)
    print(site.url)

}