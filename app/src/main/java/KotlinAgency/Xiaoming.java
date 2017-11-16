package KotlinAgency;

/**
 * Created by weichyang on 2017/11/2.
 */

public class Xiaoming {

    public XiaomingFather xiaomingFather; // ”傀儡“

    public Xiaoming(XiaomingFather xiaomingFather) {
        this.xiaomingFather = xiaomingFather;
    }

    public void buyToy() {
        System.out.println("想要买买玩具");
        xiaomingFather.payMoney();
    }
}
