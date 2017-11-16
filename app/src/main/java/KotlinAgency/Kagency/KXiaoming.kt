package KotlinAgency.Kagency

import KotlinAgency.XiaomingFather

/**
 * Created by weichyang on 2017/11/2.
 * 在 Derived 声明中，by 子句表示，将 b 保存在 Derived 的对象实例内部，
 * 而且编译器将会生成继承自 Base 接口的所有方法, 并将调用转发给 b。
 *
 * 也就是说，编译器会生成 payMoney（）方法，拿到Kxiaoming 对象后可以直接调用b中方法，也就是
 * KxiaomingFather中的方法
 *
 */
class KXiaoming(b: XiaomingFather) : XiaomingFather by b {

    fun buyToy(): KXiaoming {
        println("想要买买玩具")
        return this
    }
}