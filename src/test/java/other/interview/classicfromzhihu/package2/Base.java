package other.interview.classicfromzhihu.package2;

public class Base {
    static {
        System.out.println("Base Static Block 1");
    }

    private static String staticValue = Log.initLog("Base Static Fiels");

    static {
        System.out.println("Base Static Block 2");
    }

    {
        System.out.println("Base Normal Block 1");
    }

    private String value = Log.initLog("Base Normal Field");

    {
        System.out.println("Base Normal Block 2");
    }

    Base() {
        System.out.println("Base Constructor");
    }
}
