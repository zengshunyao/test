/**
 * Created by Lenovo on 2015/6/9.
 */
public class TestRef {

    public static void main(String[] args) {

        ValueObject vo1 = new ValueObject("A", 1);
        System.out.println("after vo1: " + vo1.getName()); //=A

        changeValue1(vo1);
        System.out.println("after changeValue1: " + vo1.getName());
        //=A1, changed

        changeValue2(vo1);
        System.out.println("after changeValue2: " + vo1.getName());
        //=A1, changeValue2内部的赋值不会影响这里。
    }


    /**
     * 使用vo1自身的函数对其内部数据进行改变是有效的，函数外可反映出来
     * <p/>
     * 这种object称为可变的(mutable)
     *
     * @param vo1
     */
    private static void changeValue1(ValueObject vo1) {
        vo1.setName("A1");
    }

    /**
     * 在函数内给vo1重新赋值不会改变函数外的原始值
     *
     * @param vo1
     */

    private static void changeValue2(ValueObject vo1) {
        vo1 = new ValueObject("B", 2);
        System.out.println("inside changeValue2: " + vo1.getName());
        //=B,赋值操作引起的结果变化仅在changeValue2内部有效
    }
}


class ValueObject {

    public ValueObject() {
    }

    public ValueObject(String name, int id) {
        this.name = name;
        this.id = id;
    }

    private String name;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}