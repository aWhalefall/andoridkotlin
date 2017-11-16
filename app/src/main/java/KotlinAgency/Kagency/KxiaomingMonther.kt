package KotlinAgency.Kagency

import KotlinAgency.XiaomingFather

/**
 * Created by weichyang on 2017/11/2
 */
class KxiaomingMonther(var payMoney: Int) : XiaomingFather {

    override fun payMoney() {
        print("给小明零花钱" + payMoney + "元")
    }
}