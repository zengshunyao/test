package other.interview.classicfromzhihu.package2;


/**
 * 派生类
 */
public class Derived extends Base {
    static {
        System.out.println("Static Block 1");
    }

    private static String staticValue = Log.initLog("Static Fiels");

    static {
        System.out.println("Static Block 2");
    }

    {
        System.out.println("Normal Block 1");
    }

    private String value = Log.initLog("Normal Field");

    {
        System.out.println("Normal Block 2");
    }

    Derived() {
        System.out.println("Derived Constructor");
    }
}
