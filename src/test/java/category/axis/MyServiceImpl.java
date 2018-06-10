package category.axis;

/**
 * Created by Lenovo on 2015/6/8.
 */
public class MyServiceImpl implements MyService {
    public String doSomething(String taskname) {
        System.out.println("MyServiceImpl is calling doSomething with " + taskname + "!");
        return taskname + "is finished!";
    }
}
