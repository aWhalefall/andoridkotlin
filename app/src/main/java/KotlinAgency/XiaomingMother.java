package KotlinAgency;

/**
 * Created by weichyang on 2017/11/2.
 */

public class XiaomingMother implements XiaomingFather {

    public int toy = 0;//买玩具钱

    public XiaomingMother(int moeny) {
        this.toy = moeny;
    }

    @Override
    public void payMoney() {
        System.out.println("给小明零花钱" + toy + "元");
    }
}
