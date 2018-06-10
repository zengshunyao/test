package category.designerpattern.singleton.serialize;

import java.io.*;

/**
 * *******************************************************************
 * &lt;p&gt;文件名：${file_name} &lt;/p&gt;
 * &lt;p&gt;文件描述：${todo}(描述该文件做什么)
 *
 * @author ${user}
 * @project_name：${project_name}
 * @date ${date} ${time}
 * @history
 * @department：政务事业部 Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 * All Rights Reserved.
 */
public class MyObject implements Serializable {

    //内部类方式
    private static class MyObjectHolder {
        //利用立即加载方式
        private static MyObject instance = new MyObject();
    }

    private MyObject() {
        super();
    }

    public static MyObject getInstance() {
        //此版本为内部类方式
        return MyObjectHolder.instance;
    }

    private Object readResolve() throws ObjectStreamException {
        System.out.println("调用了readResolve方法！");
        return MyObject.getInstance();
    }
}

class MyThread extends Thread {
    @Override
    synchronized public void run() {
        super.run();
        //System.out.println(MyObject.getInstance().hashCode());
        try {
            MyObject myObject = MyObject.getInstance();
            FileOutputStream fileOutputStream = new FileOutputStream(new File("MyObject.txt"));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(myObject);
            objectOutputStream.close();
            fileOutputStream.close();
            System.out.println("写："+myObject.hashCode());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileInputStream fileInputStream = new FileInputStream(new File("MyObject.txt"));
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object object = objectInputStream.readObject();
            MyObject myObject = object instanceof MyObject ? (MyObject) object : null;
            objectInputStream.close();
            fileInputStream.close();
            System.out.println(object != null ? "读："+myObject.hashCode() : "不存在！");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Run {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        //MyThread t2 = new MyThread();
        //MyThread t3 = new MyThread();
        t1.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("异常");
            }
        });
        t1.start();
        //t2.start();
        //t3.start();
    }
}
