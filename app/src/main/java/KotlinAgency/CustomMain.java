package KotlinAgency;

import java.lang.reflect.Array;

/**
 * Created by weichyang on 2017/11/2.
 *
 */

public class CustomMain {

    public static void main(String args[]) {
        XiaomingFather xiaomingFather=new XiaomingMother(10);
        new Xiaoming(xiaomingFather).buyToy();
    }
}
