package KotlinAgency.NormalAgency

/**
 * Created by weichyang on 2017/11/2.
 *
 */

object NormalMain {


    @JvmStatic
    fun main(args: Array<String>) {

        val user = User()
        user.name = "第一次赋值"
        user.name = "第二次赋值"
    }

}
