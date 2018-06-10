package script;

import javax.script.*;
import java.io.FileReader;
import java.net.URL;
import java.util.Scanner;

/**********************************************************************
 * &lt;p&gt;文件名：${FILE_NAME} &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/4/20 17:27
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class ScriptDemo2 {


    public static void main(String[] args) throws Exception {
        /*mimeType为传输的文件类型,如 application/javascript*/
        /*获取执行JavaScript的执行引擎*/
        final ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
        /*为文件注入全局变量*/
        final Bindings bindings = engine.createBindings();
        bindings.put("factor", 2);
        /*设置绑定参数的作用域*/
        engine.setBindings(bindings, ScriptContext.ENGINE_SCOPE);

        final Scanner scanner = new Scanner(System.in);
        /*开始等待用户输入->体现出不需要重复运行JVM就可以实现脚本文件的更换*/
        while (scanner.hasNextInt()) {
            System.out.println("请输入参数:");
            /*只有当用户输入的是整数型时才会被执行*/
            final int first = scanner.nextInt();
            if(first==-1){
                break;
            }
            final int second = scanner.nextInt();
            System.out.println("当前输入的参数为: " + first + "," + second);
            /*执行js文件代码*/
            URL url = Thread.currentThread().getContextClassLoader().getResource("");
            String path;
            if (url != null) {
                path = url.getPath();
                engine.eval(new FileReader(path + "/script_engine.js"));
                /*查看是否可以调用方法*/
                if (engine instanceof Invocable) {
                    Invocable in = (Invocable) engine;
                    Double result = (double) in.invokeFunction("formula", first, second);
                    System.out.println("输出结果为" + result);
                }
            }
        }
    }
}
